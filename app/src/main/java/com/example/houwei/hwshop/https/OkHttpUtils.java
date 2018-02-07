package com.example.houwei.hwshop.https;

import android.text.TextUtils;


import com.example.houwei.hwshop.utils.LogUtils;
import com.example.houwei.hwshop.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class OkHttpUtils {

	private static OkHttpClient mOkHttpClient=null;
	private static MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
	private static PostMode mode = PostMode.FORM;
	public static String userAgent  = "Android";
	public static OkHttpClient getOkHttpClient(){
		return mOkHttpClient;
	}
	public static void setPostMode(PostMode postMode){
		mode = postMode;
	}
	public static OkHttpClient getInstance() {
		if (mOkHttpClient == null) {
			synchronized (OkHttpUtils.class) {
				if (mOkHttpClient == null)
					mOkHttpClient = new OkHttpClient();
			}
		}
		return mOkHttpClient;
	}

	public static void init(String userAgent){
		OkHttpUtils.userAgent = userAgent;
	}

	public static void get(String url, final ICallback callback) {
		Request request = new Request.Builder().removeHeader("User-Agent").addHeader("User-Agent", userAgent).url(url).build();
		LogUtils.i("NetWorkUtils", "------请求URL = " + url);
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				callback.onError(e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String result = response.body().string();
					LogUtils.i("NetWorkUtils", "------后台返回 = " + result);
					callback.onSuccess(result);
				} else {
					callback.onError(response.message());
				}
			}



		});
	}

	public static void post(String url, Map<String, String> params,
                            final ICallback callback) {
		JSONObject obj = map2JSON(params);
		LogUtils.i("NetWorkUtils", "------请求URL = " + url);
		LogUtils.i("NetWorkUtils", "------请求参数 JSONObject = " + obj.toString());
		RequestBody requestBody = null;
		if(mode == PostMode.FORM){
			FormBody.Builder builder = new FormBody.Builder();
			for (Map.Entry<String, String> entry: params.entrySet()){
				if(TextUtils.isEmpty(entry.getValue())){
					builder.add(entry.getKey(), "");
				}else{
					builder.add(entry.getKey(), entry.getValue());
				}
			}
			requestBody = builder.build();
		}else{
			requestBody = RequestBody.create(JSON_TYPE, obj.toString());
		}

		Request request = new Request.Builder().removeHeader("User-Agent").addHeader("User-Agent", userAgent).url(url).post(requestBody).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				callback.onError(e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					String result = response.body().string();
					LogUtils.i("NetWorkUtils", "------后台返回 = " + result);
					callback.onSuccess(result);
				} else {
					callback.onError(response.message());
				}
			}


		});
	}

	public static void upload(String url, String fileKey, File file, Map<String, String> map,
                              final IUploadCallback callback) {
		LogUtils.i("NetWorkUtils", "------请求URL = " + url);
		Request request = buildMultipartFormRequest(url, new String[] { fileKey }, new File[] { file },
				map, callback);
		mOkHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				callback.onError(e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				LogUtils.i("NetWorkUtils", "-----是否成功 = " + response.isSuccessful());
				if (response.isSuccessful()) {
					final String result = response.body().string();
					LogUtils.i("NetWorkUtils", "------后台返回 = " + result);
					callback.onSuccess(result);
				} else {
					callback.onError(response.message());
				}
			}


		});
	}

	public static void upload(String url, String fileKey[], File file[], Map<String, String> map,
                              final IUploadCallback callback) {
		LogUtils.i("NetWorkUtils", "------请求URL = " + url);
		Request request = buildMultipartFormRequest(url, fileKey, file,
				map, callback);
		mOkHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				callback.onError(e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				LogUtils.i("NetWorkUtils", "-----是否成功 = " + response.isSuccessful());
				if (response.isSuccessful()) {
					final String result = response.body().string();
					LogUtils.i("NetWorkUtils", "------后台返回 = " + result);
					callback.onSuccess(result);
				} else {
					callback.onError(response.message());
				}
			}




		});
	}

	private static Request buildMultipartFormRequest(String url, String[] fileKeys, File[] files, Map<String, String> map, IUploadCallback callback) {
		if (map == null)
			map = new HashMap<>();
		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		for (int i = 0; i <files.length; i++) {
			File file = files[i];
			LogUtils.i("NetWorkUtils", "------param = " + fileKeys[i] + " = " + file.getAbsolutePath());
			String fileName = file.getName();
			MediaType fileType = MediaType.parse(guessMimeType(fileName));
			if(callback != null){
				builder.addFormDataPart(fileKeys[i], fileName, createCustomRequestBody(i, fileType, file, callback));
			}else{
				builder.addFormDataPart(fileKeys[i], fileName, RequestBody.create(fileType, file));
			}
		}

		for (Map.Entry<String, String> entry : map.entrySet()) {
			LogUtils.i("NetWorkUtils", "------param = " + entry.getKey() + " = " + entry.getValue());
			builder.addFormDataPart(entry.getKey(), entry.getValue());
		}
		RequestBody requestBody = builder.build();
		return new Request.Builder().removeHeader("User-Agent").addHeader("User-Agent", userAgent).url(url).post(requestBody).build();
	}

	private static String guessMimeType(String path) {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentTypeFor = fileNameMap.getContentTypeFor(path);
		if (contentTypeFor == null) {
			contentTypeFor = "application/octet-stream";
		}
		return contentTypeFor;
	}

	public static RequestBody createCustomRequestBody(final int index, final MediaType contentType, final File file, final IUploadCallback listener) {
		return new RequestBody() {
			@Override
            public MediaType contentType() {
				return contentType;
			}
			@Override
            public long contentLength() {
				return file.length();
			}
			@Override
            public void writeTo(BufferedSink sink) throws IOException {
				try {
					Source source = Okio.source(file);
					Buffer buf = new Buffer();
					Long surplusBytes = contentLength();
					for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
						sink.write(buf, readCount);
						listener.onProgress(index, contentLength(), surplusBytes -= readCount, surplusBytes == 0);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	public interface IDownloadCallback {

		public void onSuccess(String result);

		public void onProgress(long totalBytes, long downloadBytes, int progress);

		public void onError(String result);
	}

	public interface IUploadCallback {

		public void onSuccess(String result);

		public void onProgress(int index, long totalBytes, long surplusBytes, boolean done);

		public void onError(String result);
	}

	public interface ICallback {

		public void onSuccess(String result);

		public void onError(String result);
	}

	private static JSONObject map2JSON(Map<String, String> params) {
		if (params == null)
			return new JSONObject();
		JSONObject res = new JSONObject();
		Set<Map.Entry<String, String>> entries = params.entrySet();
		try {
			for (Map.Entry<String, String> entry : entries) {
				res.put(entry.getKey(), entry.getValue());
			}
		} catch (JSONException e) {
		}
		return res;
	}

	public static void setCertificates(List<String> certList){
		if (certList == null){
			return;
		}
		InputStream[] certificates = new InputStream[certList.size()];
		for (int i  =0; i < certList.size(); i++){
			String certItem = "-----BEGIN CERTIFICATE-----\n"
					+ certList.get(i) + "\n"
					+ "-----END CERTIFICATE-----";
			certificates[i] = StringUtils.stringToStream(certItem);
		}
		mOkHttpClient.newBuilder().sslSocketFactory(getSslSocketFactory(certificates, null, null),null).hostnameVerifier(new UnSafeHostnameVerifier());

	}
    
    public static void setCertificates(InputStream[] certificates, InputStream bksFile, String password)
    {
		mOkHttpClient.newBuilder().sslSocketFactory(getSslSocketFactory(certificates, bksFile, password)).hostnameVerifier(new UnSafeHostnameVerifier());

    }

    private static SSLSocketFactory getSslSocketFactory(InputStream[] certificates, InputStream bksFile, String password)
    {
        try
        {
            TrustManager[] trustManagers = prepareTrustManager(certificates);
            KeyManager[] keyManagers = prepareKeyManager(bksFile, password);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = null;
            if (trustManagers != null)
            {
                trustManager = new MyTrustManager(chooseTrustManager(trustManagers));
            } else
            {
                trustManager = new UnSafeTrustManager();
            }
            sslContext.init(keyManagers, new TrustManager[]{trustManager},null);
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e)
        {
            throw new AssertionError(e);
        } catch (KeyManagementException e)
        {
            throw new AssertionError(e);
        } catch (KeyStoreException e)
        {
            throw new AssertionError(e);
        }
    }

    private static class UnSafeHostnameVerifier implements HostnameVerifier
    {
        @Override
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }

    private static class UnSafeTrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException
        {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException
        {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[]{};
        }
    }

    private static TrustManager[] prepareTrustManager(InputStream... certificates)
    {
        if (certificates == null || certificates.length <= 0) return null;
        try
        {

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try
                {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e)
                {
                }
            }
            TrustManagerFactory trustManagerFactory = null;

            trustManagerFactory = TrustManagerFactory.
                    getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            return trustManagers;
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (CertificateException e)
        {
            e.printStackTrace();
        } catch (KeyStoreException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    private static KeyManager[] prepareKeyManager(InputStream bksFile, String password)
    {
        try
        {
            if (bksFile == null || password == null) return null;

            KeyStore clientKeyStore = KeyStore.getInstance("BKS");
            clientKeyStore.load(bksFile, password.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, password.toCharArray());
            return keyManagerFactory.getKeyManagers();

        } catch (KeyStoreException e)
        {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e)
        {
            e.printStackTrace();
        } catch (CertificateException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagers)
    {
        for (TrustManager trustManager : trustManagers)
        {
            if (trustManager instanceof X509TrustManager)
            {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }


    private static class MyTrustManager implements X509TrustManager
    {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public MyTrustManager(X509TrustManager localTrustManager) throws NoSuchAlgorithmException, KeyStoreException
        {
            TrustManagerFactory var4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            var4.init((KeyStore) null);
            defaultTrustManager = chooseTrustManager(var4.getTrustManagers());
            this.localTrustManager = localTrustManager;
        }


        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
            try
            {
                defaultTrustManager.checkServerTrusted(chain, authType);
            } catch (CertificateException ce)
            {
                localTrustManager.checkServerTrusted(chain, authType);
            }
        }


        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[0];
        }
    }


	/**
	 * 下载文件
	 * @param url
	 * @param destFileDir
	 * @param callback
     * @param tag
     */
	public static void download(final String url, final String destFileDir, final IDownloadCallback callback, Object tag)
	{
		Request request = new Request.Builder()
				.url(url)
				.tag(tag)
				.build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback()
		{

			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				callback.onError(e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {

				InputStream is = null;
				byte[] buf = new byte[2048];
				int len = 0;
				FileOutputStream fos = null;
				try
				{
					is = response.body().byteStream();
					long total = response.body().contentLength();
					File dir = new File(destFileDir);
					if (!dir.exists())
					{
						dir.mkdirs();
					}
					File file = new File(dir, getFileName(url));
					fos = new FileOutputStream(file);
					long sum = 0;
					while ((len = is.read(buf)) != -1)
					{
						fos.write(buf, 0, len);
						sum += len;
						int progress = (int) (sum * 1.0f / total * 100);
						callback.onProgress(total, sum, progress);
					}
					fos.flush();
					//如果下载文件成功，第一个参数为文件的绝对路径
					callback.onSuccess(file.getAbsolutePath());
				} catch (IOException e){
					e.printStackTrace();
					callback.onError(e.getMessage());
				} finally
				{
					try
					{
						if (is != null) is.close();
					} catch (IOException e)
					{
					}
					try
					{
						if (fos != null) fos.close();
					} catch (IOException e)
					{
					}
				}
			}




		});
	}


	/**
	 * 下载文件
	 * @param url
	 * @param destFileDir
	 * @param callback
	 */
	public static void download(final String url, final String destFileDir, final IDownloadCallback callback)
	{
		download(url, destFileDir, callback, null);
	}

	private static String getFileName(String path)
	{
		int separatorIndex = path.lastIndexOf("/");
		return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
	}

	public enum PostMode{
		FORM, JSON
	}
}
