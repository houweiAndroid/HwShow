package com.example.houwei.hwshop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Util {

	public static PackageInfo isAvilible(Context context, String packageName) {
		final PackageManager packageManager = context.getPackageManager();
		// 获取所有已安装程序的包信息
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);

		for (int i = 0; i < pinfo.size(); i++) {
			if (pinfo.get(i).packageName.equalsIgnoreCase(packageName)) {
				return pinfo.get(i);
			}
		}
		return null;
	}

//	public static String getMachineCode(Context context) {
//		return getPhoneCode(context);
//	}

	public static void printMsg(String str1, String str2) {
//		Log.i("haha", str1 + ":" + str2);
		PrintUtil.printMsg(str1, str2);
	}

	// 转化MD5

	public static String getMD5Code(String img_name) {

		StringBuffer buffer = new StringBuffer(img_name);

		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			StringBuffer result = new StringBuffer();
			try {
				for (byte b : md.digest(buffer.toString().getBytes("UTF-8"))) {
					result.append(Integer.toHexString((b & 0xf0) >>> 4));
					result.append(Integer.toHexString(b & 0x0f));
				}
			} catch (UnsupportedEncodingException e) {
				for (byte b : md.digest(buffer.toString().getBytes())) {
					result.append(Integer.toHexString((b & 0xf0) >>> 4));
					result.append(Integer.toHexString(b & 0x0f));
				}
			}

			return result.toString();
		} catch (java.security.NoSuchAlgorithmException ex) {

		}
		return null;
	}

	// ï¿½Ãµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ü¶ï¿½
	public static float getPhoneDensity(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		return dm.density;
	}

	// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½×ªï¿½ï¿½ÎªURL
	public static String getRequestURL(Bundle params) {
		if (params == null) {
			return "";
		}
		boolean first = true;
		StringBuffer buffer = new StringBuffer();
		for (String key : params.keySet()) {
			if (first) {
				first = false;
			} else {
				buffer.append("&");
			}
			try {
				buffer.append(URLEncoder.encode(key) + "="
						+ URLEncoder.encode(params.getString(key), "UTF-8"));
			} catch (Exception ex) {
				buffer.append(URLEncoder.encode(key) + "="
						+ URLEncoder.encode(params.getString(key)));
				ex.printStackTrace();
			}
		}

		return buffer.toString();
	}

	/**
	 * encode2
	 * 
	 * @param params
	 * @return
	 */
	public static String getRequestURLForEncode2(Bundle params) {
		if (params == null) {
			return "";
		}
		boolean first = true;
		StringBuffer buffer = new StringBuffer();
		for (String key : params.keySet()) {
			if (first) {
				first = false;
			} else {
				buffer.append("&");
			}
			try {
				buffer.append(URLEncoder.encode(key)
						+ "="
						+ URLEncoder.encode(URLEncoder.encode(
								params.getString(key), "UTF-8")));
			} catch (Exception ex) {
				buffer.append(URLEncoder.encode(key) + "="
						+ URLEncoder.encode(params.getString(key)));
				ex.printStackTrace();
			}
		}

		return buffer.toString();
	}

	public static String getRequestURL(Bundle params, String charset)
			throws UnsupportedEncodingException {
		if (params == null) {
			return "";
		}
		boolean first = true;
		StringBuffer buffer = new StringBuffer();
		for (String key : params.keySet()) {
			if (first) {
				first = false;
			} else {
				buffer.append("&");
			}

			buffer.append(URLEncoder.encode(key) + "="
					+ URLEncoder.encode(params.getString(key), charset));

		}

		return buffer.toString();
	}

	public static String getRequestURLGBK(Bundle params)
			throws UnsupportedEncodingException {
		if (params == null) {
			return "";
		}
		boolean first = true;
		StringBuffer buffer = new StringBuffer();
		for (String key : params.keySet()) {
			if (first) {
				first = false;
			} else {
				buffer.append("&");
			}

			buffer.append(URLEncoder.encode(key) + "="
					+ URLEncoder.encode(params.getString(key), "GBK"));

		}

		return buffer.toString();
	}

	public static void setListViewHeightBasedOnChildren(Context context,
			ListView listView) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		final int width = manager.getDefaultDisplay().getWidth();
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(MeasureSpec.AT_MOST + width, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	public static void setListViewHeightBasedOnChildren(Context context,
                                                        ListView listView, int width) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		width = width == 0 ? manager.getDefaultDisplay().getWidth() : width;
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(MeasureSpec.EXACTLY + width, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	public static Bitmap getImageFromFile(String filePath) {
		Bitmap bmp = null;
		if (filePath != null) {

			BitmapFactory.Options ops = new BitmapFactory.Options();
			ops.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, ops);
			ops.inPurgeable = true;
			ops.inInputShareable = true;

			try {
				BitmapFactory.Options.class.getField("inNativeAlloc")
						.setBoolean(ops, true);
			} catch (Exception ex) {
				// ex.printStackTrace();
			}
			PrintUtil.printMsg("bmpSize", ops.outHeight + "   " + ops.outWidth);
			ops.inJustDecodeBounds = false;
			try {
				bmp = BitmapFactory.decodeFile(filePath, ops);
			} catch (OutOfMemoryError ex) {
				System.gc();
			}
		}
		return bmp;
	}

	public static Bitmap getImageFromFileScale(String filePath, int width,
                                               int height) {
		Bitmap bmp = null;
		if (filePath != null) {

			BitmapFactory.Options ops = new BitmapFactory.Options();
			ops.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, ops);
			ops.inPurgeable = true;
			ops.inInputShareable = true;
			try {
				BitmapFactory.Options.class.getField("inNativeAlloc")
						.setBoolean(ops, true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			float scaleSize = Math.min((float) ops.outHeight / height,
					(float) ops.outWidth / width);
			scaleSize = Math.max(1f, scaleSize);
			// System.out.println("scaleSzie:"+scaleSize+"    "+ops.outHeight+"   "+height);

			ops.inSampleSize = (int) Math.ceil(scaleSize);
			ops.inJustDecodeBounds = false;
			try {
				bmp = BitmapFactory.decodeFile(filePath, ops);
			} catch (OutOfMemoryError ex) {
				System.gc();
			}
		}
		return bmp;
	}

	// Ô­Ê¼Í¼
	public static Bitmap getImageFromFileThumnail(String filePath) {
		Bitmap bmp = null;
		if (filePath != null) {

			BitmapFactory.Options ops = new BitmapFactory.Options();
			ops.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, ops);
			ops.inPurgeable = true;
			ops.inInputShareable = true;

			try {
				BitmapFactory.Options.class.getField("inNativeAlloc")
						.setBoolean(ops, true);
			} catch (Exception ex) {
				// ex.printStackTrace();
			}

			ops.inJustDecodeBounds = false;
			ops.inDither = false; // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			ops.inPreferredConfig = null; // ï¿½ï¿½Ñ½ï¿½ï¿½ï¿½

			try {
				bmp = BitmapFactory.decodeFile(filePath, ops);
			} catch (OutOfMemoryError ex) {
				System.gc();
			}
		}
		return bmp;
	}

	public static Bitmap getImageFromFileScaleThumnail(String filePath,
                                                       int width) {
		Bitmap bmp = null;
		if (filePath != null) {

			BitmapFactory.Options ops = new BitmapFactory.Options();
			ops.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, ops);

			ops.outWidth = width;
			ops.outHeight = ops.outHeight * width / ops.outWidth;
			ops.inSampleSize = ops.outWidth / width;
			// ops.inSampleSize= (ops.outWidth/width+ops.outHeight/height)/2;
			if (ops.inSampleSize < 1) {
				ops.inSampleSize = 1;
			}

			ops.inPurgeable = true;
			ops.inInputShareable = true;

			try {
				BitmapFactory.Options.class.getField("inNativeAlloc")
						.setBoolean(ops, true);
			} catch (Exception ex) {
				// ex.printStackTrace();
			}

			ops.inJustDecodeBounds = false;
			ops.inDither = false; // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			// ops.inPreferredConfig=null; //ï¿½ï¿½Ñ½ï¿½ï¿½ï¿½
			ops.inPreferredConfig = Bitmap.Config.RGB_565;

			try {
				bmp = BitmapFactory.decodeFile(filePath, ops);
			} catch (OutOfMemoryError ex) {
				System.gc();
			}
		}
		return bmp;
	}

	public static File createNewFile(String fileStr) throws IOException {
		File file = new File(fileStr);
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().getParentFile().exists()) {
				file.getParentFile().getParentFile().mkdir();
			}
			file.getParentFile().mkdir();

		}
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		return file;

	}

//	public static void wakeUpAndUnlock(Context context){
//        KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
//        //解锁
//        kl.disableKeyguard();
//        //获取电源管理器对象
//        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
//        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");
//        //点亮屏幕
//        wl.acquire();
//        //释放
//        wl.release();
//    }

	public static int distance(double lat, double lng, double lat2, double lng2) {
		double theta = lng - lng2;
		double dist = Math.sin(deg2rad(lat)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		dist = dist * 1000;
		return (int) dist;
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public static String changeTextFormat(String str) {
		char a[] = str.toCharArray();
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 12288) {
				a[i] = 32;
				continue;
			}
			if (a[i] > 65280 && a[i] < 65375) {
				a[i] = (char) (a[i] - 65248);
			}
		}
		return new String(a);
	}

	public static int getImageOrientation(String filePath) {
		int degree = 0;
		ExifInterface exif = null;

		try {
			exif = new ExifInterface(filePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (null == exif) {
			return 0;
		}
		int type = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
		if (type != -1) {
			switch (type) {
			case ExifInterface.ORIENTATION_ROTATE_90: {
				degree = 90;
				break;
			}
			case ExifInterface.ORIENTATION_ROTATE_180: {
				degree = 180;
				break;
			}
			case ExifInterface.ORIENTATION_ROTATE_270: {
				degree = 270;
				break;
			}
			}
		}
		return degree;
	}

//	public static String getDefaultFilePath(Context context, String fileName) {
//		StringBuffer buffer = new StringBuffer();
//		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//			buffer.append(Constant.APP_PATH + "default");
//			File path = new File(buffer.toString());
//			if (!path.exists()) {
//				path.mkdir();
//			}
//			buffer.append("/" + fileName);
//		} else {
//			buffer.append(context.getFilesDir() + "/" + fileName);
//		}
//		return buffer.toString();
//	}
//
//	public static Bitmap getRotateImg(Bitmap bmp, int rotate) {
//		Bitmap bm = null;
//		if (bmp == null || bmp.isRecycled()) {
//			return bm;
//		}
//		final Matrix m = new Matrix();
//		m.postRotate(rotate);
//		// PrintUtil.printUtil("info:", rotate + "    " + bmp.getWidth() + "   "
//		// + bmp.getHeight());
//
//		bm = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), m,
//				false);
//
//		// if (rotate == 90 || rotate == 270) {
//		// bm = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
//		// bmp.getHeight(), m, false);
//		// } else {
//		//
//		// // bm = Bitmap.createBitmap(bmp, 0, 0,
//		// // bmp.getWidth()*bmp.getWidth()/bmp.getHeight(),
//		// // bmp.getWidth(), m, false);
//		//
//		// bm = big(
//		// Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
//		// bmp.getHeight(), m, false),
//		// bmp.getWidth() * bmp.getWidth() / bmp.getHeight(),
//		// bmp.getWidth());
//		//
//		// // Bitmap rbm = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
//		// // bmp.getHeight(), m, false);
//		// //
//		// //
//		// // Matrix m2 = new Matrix();
//		// // m2.postScale(1.5f,1.5f);
//		// // bm = Bitmap.createBitmap(rbm, 0, 0, rbm.getWidth(),
//		// // rbm.getHeight(), m2, false);
//		// // rbm.recycle();
//		//
//		// }
//		if (bm != bmp) {
//			bmp.recycle();
//		}
//		return bm;
//	}
//
//	public static Bitmap big(Bitmap b, float x, float y) {
//		int w = b.getWidth();
//		int h = b.getHeight();
//		float sx = (float) x / w;// è¦å¼ºå¶è½¬æ¢ï¼ä¸è½¬æ¢æçå¨è¿æ»æ¯æ­»æã
//		float sy = (float) y / h;
//		Matrix matrix = new Matrix();
//		matrix.postScale(sx, sy); // é¿åå®½æ¾å¤§ç¼©å°çæ¯ä¾
//		Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w, h, matrix, true);
//		b.recycle();
//		return resizeBmp;
//	}
//
//	public static Bitmap createReflect(Bitmap bmp) {
//
//		final int mWidth = bmp.getWidth();
//		final int mHeight = bmp.getHeight();
//		final Matrix m = new Matrix();
//		final int ImageGap = 5;
//
//		m.postScale(1, -1);
//
//		Bitmap reflectBmp = Bitmap.createBitmap(bmp, 0, mHeight * 2 / 3,
//				mWidth, mHeight / 3, m, false);
//
//		Bitmap newBmp = Bitmap.createBitmap(mWidth,
//				mHeight + reflectBmp.getHeight() + ImageGap, Config.ARGB_8888);
//		Canvas canvas = new Canvas(newBmp);
//
//		canvas.drawBitmap(bmp, 0, 0, null);
//
//		Paint bg = new Paint();
//		bg.setAntiAlias(true);
//		bg.setColor(Color.BLACK);
//		Shader shader = new LinearGradient(0, bmp.getHeight(), 0,
//				newBmp.getHeight(), 0x60ffffff, 0x00ffffff, TileMode.REPEAT);
//		bg.setShader(shader);
//		bg.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
//
//		canvas.drawBitmap(reflectBmp, 0, mHeight + ImageGap, null);
//		canvas.drawRect(0, mHeight + ImageGap, mWidth, newBmp.getHeight(), bg);
//		bmp.recycle();
//		return newBmp;
//
//	}
//
//	public static ImgSize getImageFromFileForSize(String filePath) {
//		ImgSize imgSize = null;
//		Bitmap bmp = null;
//		if (filePath != null) {
//			imgSize = new ImgSize();
//			BitmapFactory.Options ops = new BitmapFactory.Options();
//			ops.inJustDecodeBounds = true;
//			BitmapFactory.decodeFile(filePath, ops);
//			// Util.printMsg("imgSize", ops.outHeight+"   "+ops.outWidth);
//			imgSize.setImgHeight(ops.outHeight);
//			imgSize.setImgWidth(ops.outWidth);
//			ops.inPurgeable = true;
//			ops.inInputShareable = true;
//
//			try {
//				BitmapFactory.Options.class.getField("inNativeAlloc")
//						.setBoolean(ops, true);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//
//			ops.inJustDecodeBounds = false;
//			try {
//				bmp = BitmapFactory.decodeFile(filePath, ops);
//				imgSize.setBmp(bmp);
//
//			} catch (OutOfMemoryError ex) {
//				System.gc();
//			}
//		}
//		return imgSize;
//	}

	// public static Bitmap createHalfofBmp(Bitmap bmp) {
	//
	// final int mWidth = bmp.getWidth();
	// final int mHeight = bmp.getHeight();
	// final float size = (float) 153.3f * AppData.phone_density
	// / bmp.getWidth();
	// final Matrix m = new Matrix();
	//
	// m.postRotate(-90);
	// // m.postScale(size, size);
	// System.out.println("size:" + size + "   " + (size * mWidth));
	// Bitmap reflectBmp = Bitmap.createBitmap(bmp, 0, 0, mWidth, mHeight, m,
	// false);
	//
	// Bitmap newBmp = Bitmap.createBitmap((int) (size * mHeight),
	// (int) (size * mWidth), Config.ARGB_8888);
	// Canvas canvas = new Canvas(newBmp);
	//
	// // canvas.drawBitmap(reflectBmp, mWidth/2, mHeight/2, null);
	// canvas.drawBitmap(reflectBmp,
	// new Rect(0, mHeight / 2, mWidth, mHeight), new Rect(0,
	// (int) (size * mWidth) / 2, (int) (size * mHeight),
	// (int) (size * mWidth)), null);
	//
	// return newBmp;
	//
	// }

	public static String getDistance(int dist) {
		return String.format("%.2fkm", (float) dist / 1000);
	}

	public static byte[] decode(String s) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(s, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}

		byte[] decodedBytes = bos.toByteArray();
		try {
			bos.close();
			bos = null;
		} catch (IOException ex) {
			System.err.println("Error while decoding BASE64: " + ex.toString());
		}
		return decodedBytes;
	}

	private static int decode(char c) {
		if (c >= 'A' && c <= 'Z')
			return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z')
			return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9')
			return ((int) c) - 48 + 26 + 26;
		else
			switch (c) {
			case '+':
				return 62;
			case '/':
				return 63;
			case '=':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
			}
	}

	private static void decode(String s, OutputStream os) throws IOException {
		int i = 0;
		int len = s.length();
		while (true) {
			while (i < len && s.charAt(i) <= ' ')
				i++;
			if (i == len)
				break;
			int tri = (decode(s.charAt(i)) << 18)
					+ (decode(s.charAt(i + 1)) << 12)
					+ (decode(s.charAt(i + 2)) << 6)
					+ (decode(s.charAt(i + 3)));
			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '=')
				break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '=')
				break;
			os.write(tri & 255);
			i += 4;
		}
	}

//	public static int getVerCode(Context context) {
//		int code = -1;
//		try {
//			code = context.getPackageManager().getPackageInfo(
//					context.getPackageName(), 1).versionCode;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return code;
//	}

//	public static String getVerName(Context context) {
//		String name = "";
//		try {
//			name = context.getPackageManager().getPackageInfo(
//					context.getPackageName(), 1).versionName;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return name;
//	}

	// public static String getValueKey(Context context) {
	// final Resources r = context.getResources();
	// final StringBuffer buffer = new StringBuffer();
	// buffer.append(r.getString(R.string.af_aa)
	// + r.getString(R.string.ae_aa)
	// + (AppData.SYS_TIMESTAMP == 0 ? System.currentTimeMillis() / 1000
	// : getSystemTime()) + r.getString(R.string.ak_aa)
	// + r.getString(R.string.ag_aa));
	// return getMD5Code(buffer.toString());
	// }
	//
	// public static String getSystemTime() {
	// final StringBuffer buffer = new StringBuffer();
	// buffer.append(AppData.SYS_TIMESTAMP
	// + (System.currentTimeMillis() / 1000 - AppData.GET_SYS_TIME));
	// return buffer.toString();
	// }
	//
	// public static String getSignAndTimestamp(Context context) {
	// final StringBuffer buffer = new StringBuffer();
	// final Bundle params = new Bundle();
	// params.putString("appkey",
	// context.getResources().getString(R.string.af_aa)
	// + context.getResources().getString(R.string.ae_aa));
	// params.putString("timestamp",
	// (AppData.SYS_TIMESTAMP == 0 ? System.currentTimeMillis() / 1000
	// : Util.getSystemTime()) + "");
	// params.putString("sign", Util.getValueKey(context));
	// buffer.append(getRequestURL(params));
	// return buffer.toString();
	// }
	//
	// public static String getUserValueKey(Context context) {
	// final Resources r = context.getResources();
	// final StringBuffer buffer = new StringBuffer();
	// buffer.append(r.getString(R.string.af_aa)
	// + r.getString(R.string.ae_aa)
	// + r.getString(R.string.ak_aa)
	// + r.getString(R.string.ag_aa)
	// + (AppData.USER_SYS_TIMESTAMP == 0 ? System.currentTimeMillis() / 1000
	// : getUserSystemTime()));
	// return getMD5Code(buffer.toString());
	// }
	//
	// public static String getUserSystemTime() {
	// final StringBuffer buffer = new StringBuffer();
	// buffer.append(AppData.USER_SYS_TIMESTAMP
	// + (System.currentTimeMillis() / 1000 - AppData.GET_USER_SYS_TIME));
	// return buffer.toString();
	// }

	//
	// public static String getUserKey(Context context){
	// return context.getString(R.string.user_key);
	// }

	public static int getMonthOfDay(int year, int month) {
		int day = -1;

		switch (month) {
		case 1: {
			day = 31;
			break;
		}
		case 2: {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		}
		case 3: {
			day = 31;
			break;
		}
		case 4: {
			day = 30;
			break;
		}
		case 5: {
			day = 31;
			break;
		}
		case 6: {
			day = 30;
			break;
		}
		case 7: {
			day = 31;
			break;
		}
		case 8: {
			day = 31;
			break;
		}
		case 9: {
			day = 30;
			break;
		}
		case 10: {
			day = 31;
			break;
		}
		case 11: {
			day = 30;
			break;
		}
		case 12: {
			day = 31;
			break;
		}
		}

		return day;
	}

	public static String getParkTime(String t1, String t2) {
		if (t1 == null || t1.length() == 0 || t2 == null || t2.length() == 0) {
			return "";
		}

		String t = "";
		String t1all[] = t1.split(" ");
		String t2all[] = t2.split(" ");

		if (t1all.length < 2 || t2all.length < 2) {
			return t;
		}

		String t1data[] = t1all[0].split("-");
		String t2data[] = t2all[0].split("-");
		String t1data2[] = t1all[1].split(":");
		String t2data2[] = t2all[1].split(":");
		if (t1data.length < 3 || t2data.length < 3 || t1data2.length < 3
				|| t2data2.length < 3) {
			return t;
		}

		int year1 = Integer.parseInt(t1data[0]);
		int month1 = Integer.parseInt(t1data[1]);
		int day1 = Integer.parseInt(t1data[2]);
		int hour1 = Integer.parseInt(t1data2[0]);
		int minute1 = Integer.parseInt(t1data2[1]);
		int sec1 = Integer.parseInt(t1data2[2]);

		int year2 = Integer.parseInt(t2data[0]);
		int month2 = Integer.parseInt(t2data[1]);
		int day2 = Integer.parseInt(t2data[2]);
		int hour2 = Integer.parseInt(t2data2[0]);
		int minute2 = Integer.parseInt(t2data2[1]);
		int sec2 = Integer.parseInt(t2data2[2]);

		int hourT = 0;
		int minuteT = 0;

		if (minute1 >= minute2) {

			if (hour1 < hour2) {

			} else {
				hourT = hour1 - hour2;
				minuteT = minute1 - minute2;
			}

		} else {
			if (hour1 <= hour2) {

			} else {
				minuteT = minute1 + 60 - minute2;
				hourT = hour1 - hour2 - 1;

			}

		}

		t = hourT + "Ð¡Ê±" + minuteT + "ï¿½ï¿½";

		return t;
	}

	public static BitmapDrawable getBdForRes(Resources res, int id) {

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		opts.inPreferredConfig = Config.ARGB_8888;
		Bitmap bmp = null;

		try {
			bmp = BitmapFactory.decodeResource(res, id, opts);
			// InputStream ip = res.openRawResource(id);
			// bmp=BitmapFactory.decodeStream(ip,null, opts);
		} catch (OutOfMemoryError ex) {
			System.gc();
		}
		if (bmp != null) {
			return new BitmapDrawable(res, bmp);
		} else {
			return null;
		}

	}

	public static BitmapDrawable getInputStreamForRes(Resources res, int id,
                                                      int width, int height) {

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		opts.inPreferredConfig = Config.ARGB_8888;
		Bitmap bmp = null;

		try {
			// bmp=BitmapFactory.decodeResource(res, id, opts);
			InputStream ip = res.openRawResource(id);
			bmp = BitmapFactory.decodeStream(ip, null, opts);
		} catch (OutOfMemoryError ex) {
			System.gc();
		}
		if (bmp != null) {

			final int mWidth = bmp.getWidth();
			final int mHeight = bmp.getHeight();
			final Matrix m = new Matrix();

			Bitmap reflectBmp = Bitmap.createBitmap(bmp, 0, 0, mWidth, mHeight,
					m, false);

			Bitmap newBmp = Bitmap
					.createBitmap(width, height, Config.ARGB_8888);
			Canvas canvas = new Canvas(newBmp);

			canvas.drawBitmap(reflectBmp, new Rect(0, 0, mWidth, mHeight),
					new Rect(0, 0, width, height), null);
			bmp.recycle();

			return new BitmapDrawable(res, newBmp);
		} else {
			return null;
		}

	}

//	public static void clearImgRes(String str) {
//		if (AsyncImageLoader.imageCache2.containsKey(str)
//				&& AsyncImageLoader.imageCache2.get(str).get() != null
//				&& !AsyncImageLoader.imageCache2.get(str).get().isRecycled()) {
//			AsyncImageLoader.imageCache2.get(str).get().recycle();
//			AsyncImageLoader.imageCache2.get(str).clear();
//			AsyncImageLoader.imageCache2.remove(str);
//		}
//	}

	// public static String checkImgUrl(String url) {
	// if (url.indexOf("http") == -1) {
	// url = Constant.MAIN_URL + url;
	// }
	// return url;
	// }

	public static String getThumnailBitmapFile(Activity context,
                                               String protype[], String id) {
		String path = null;
		String selection = MediaStore.Images.Thumbnails.IMAGE_ID + " = ?";
		String[] selectionArgs = new String[] { id };
		Cursor cursor2 = context.getContentResolver().query(
				MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, protype,
				selection, selectionArgs, null);
		if (cursor2.getCount() > 0) {
			cursor2.moveToNext();
			path = cursor2.getString(cursor2
					.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
		}
		cursor2.close();

		return path;

	}

	public static String getThumnailVideoFile(Activity context,
                                              String[] protype, String id) {
		String path = null;
		String selection = MediaStore.Video.Thumbnails.VIDEO_ID + " = ?";
		String[] selectionArgs = new String[] { id };
		Cursor cursor2 = context.getContentResolver().query(
				MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, protype,
				selection, selectionArgs, null);
		if (cursor2.moveToFirst()) {
			path = cursor2.getString(cursor2
					.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));
		}

		cursor2.close();

		return path;

	}

	public static String getThumnailFileForData(Activity context, String data) {
		String path = null;
		String id = null;
		String selection = MediaStore.Images.Media.DATA + " = ?";
		String[] selectionArgs = new String[] { data };
		Cursor cursor2 = context.getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, selection,
				selectionArgs, null);
		if (cursor2.getCount() > 0) {
			cursor2.moveToNext();
			id = cursor2.getString(cursor2
					.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
			cursor2.close();
		} else {
			return path;
		}

		selection = MediaStore.Images.Thumbnails.IMAGE_ID + " = ?";
		selectionArgs = new String[] { id };
		cursor2 = context.getContentResolver().query(
				MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, null,
				selection, selectionArgs, null);
		if (cursor2.getCount() > 0) {
			cursor2.moveToNext();
			path = cursor2.getString(cursor2
					.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
		}
		cursor2.close();

		return path;

	}

	public static Bitmap getVideoThumbnail(String videoPath, int width,
                                           int height, int kind) {
		Bitmap bitmap = null;
		// è·åè§é¢çç¼©ç¥å¾
		bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);

		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	public static boolean getNetActive(Context context) {
		boolean res = false;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null) {
			res = true;
		}

		return res;

	}

	public static void closeKeyboard(Activity context) {
		View view = context.getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputManager = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	// å³é­è¾å¥é®çä¿çåæ 
	public static void closeInputMethod(Activity activity, EditText ed) {
		if (android.os.Build.VERSION.SDK_INT <= 10) {// 4.0ä»¥ä¸ danielinbiti
			ed.setInputType(InputType.TYPE_NULL);
		} else {
			activity.getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			boolean ifgoon = true;

			try {
				// Class<EditText> cls = EditText.class;
				// Method setShowSoftInputOnFocus;
				// setShowSoftInputOnFocus =
				// cls.getMethod("setShowSoftInputOnFocus",
				// boolean.class);
				Method setSoftInputShownOnFocus = ed.getClass().getMethod(
						"setSoftInputShownOnFocus", boolean.class);
				setSoftInputShownOnFocus.setAccessible(true);
				setSoftInputShownOnFocus.invoke(ed, false);
				ifgoon = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (ifgoon) {
				try {
					Class<EditText> cls = EditText.class;
					Method setShowSoftInputOnFocus;
					setShowSoftInputOnFocus = cls.getMethod(
							"setShowSoftInputOnFocus", boolean.class);
					setShowSoftInputOnFocus.setAccessible(true);
					setShowSoftInputOnFocus.invoke(ed, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static float getTabletTxtSize(Context context, float persent) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return (int) (wm.getDefaultDisplay().getHeight() * persent)
				/ dm.density;
	}

	public static int getTabletHeightSize(Context context, float persent) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		final DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return (int) (wm.getDefaultDisplay().getHeight() * persent);
	}

	public static int getTabletWidthSize(Context context, float persent) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		final DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return (int) (wm.getDefaultDisplay().getWidth() * persent);
	}

	public static int getTabletFitSize(Context context, float widthPersent,
                                       float heightPersent) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		final DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		final int widthSize = (int) (wm.getDefaultDisplay().getWidth() * widthPersent);
		final int heightSize = (int) (wm.getDefaultDisplay().getHeight() * heightPersent);
		return widthSize > heightSize ? heightSize : widthSize;
	}

	public static float getTabletFitTxtSize(Context context,
			float widthPersent, float heightPersent) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		final DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		final int widthSize = (int) (wm.getDefaultDisplay().getWidth() * widthPersent);
		final int heightSize = (int) (wm.getDefaultDisplay().getHeight() * heightPersent);
		return (widthSize > heightSize ? heightSize : widthSize) / dm.density;
	}

	public static String changeBitChar(String str) {
		StringBuffer buffer = new StringBuffer("");

		for (int i = 0; i < str.length(); i++) {
			char c = (char) str.charAt(i);
			if (c > 96) {
				c -= 32;
			}
			buffer.append(c);
		}

		return buffer.toString();
	}

	public static void reorderItems(List list, int oldPosition, int newPosition) {
		if (oldPosition == newPosition) {
			return;
		}
		Object temp = list.get(oldPosition);
		if (oldPosition < newPosition) {
			for (int i = oldPosition; i < newPosition; i++) {
				Collections.swap(list, i, i + 1);
			}
		} else if (oldPosition > newPosition) {
			for (int i = oldPosition; i > newPosition; i--) {
				Collections.swap(list, i, i - 1);
			}
		}

		list.set(newPosition, temp);
	}

	public static Bitmap ResizeBitmapRecyle(Bitmap bitmap, int newWidth) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float temp = ((float) height) / ((float) width);
		int newHeight = (int) ((newWidth) * temp);
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix(); // resize the bit map
		matrix.postScale(scaleWidth, scaleHeight); // matrix.postRotate(45);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
//		bitmap.recycle();
		return resizedBitmap;

	}

//	public static Bitmap creatBarcode(Context context, String contents,
//                                      int desiredWidth, int desiredHeight, boolean displayCode) {
//		Bitmap ruseltBitmap = null;
//
//		int marginW = 0;
//
//		BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
//
//		if (displayCode) {
//			Bitmap barcodeBitmap = encodeAsBitmap(contents, barcodeFormat,
//					desiredWidth, desiredHeight);
//			Bitmap codeBitmap = creatCodeBitmap(contents, desiredWidth + 2
//					* marginW, desiredHeight, context);
//			ruseltBitmap = mixtureBitmap(barcodeBitmap, codeBitmap, new PointF(
//					0, desiredHeight));
//		} else {
//			ruseltBitmap = encodeAsBitmap(contents, barcodeFormat,
//					desiredWidth, desiredHeight);
//		}
//
//		return ruseltBitmap;
//	}
//	public static Bitmap creatBarcode(Context context, String contents,
//                                      int desiredWidth, int desiredHeight, boolean displayCode, boolean code39) {
//		Bitmap ruseltBitmap = null;
//
//		int marginW = 0;
//
//		BarcodeFormat barcodeFormat = BarcodeFormat.CODE_39;
//
//		if (displayCode) {
//			Bitmap barcodeBitmap = encodeAsBitmap(contents, barcodeFormat,
//					desiredWidth, desiredHeight);
//			Bitmap codeBitmap = creatCodeBitmap(contents, desiredWidth + 2
//					* marginW, desiredHeight, context);
//			ruseltBitmap = mixtureBitmap(barcodeBitmap, codeBitmap, new PointF(
//					0, desiredHeight));
//		} else {
//			ruseltBitmap = encodeAsBitmap(contents, barcodeFormat,
//					desiredWidth, desiredHeight);
//		}
//
//		return ruseltBitmap;
//	}

//	protected static Bitmap encodeAsBitmap(String contents,
//                                           BarcodeFormat format, int desiredWidth, int desiredHeight) {
//		final int WHITE = 0xFFFFFFFF;
//		final int BLACK = 0xFF000000;
//
//		MultiFormatWriter writer = new MultiFormatWriter();
//		BitMatrix result = null;
//		try {
//
//			Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType,Object>();
//			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//			hints.put(EncodeHintType.MARGIN, 0);
//			result = writer.encode(contents, format, desiredWidth,
//					desiredHeight,hints);
//		} catch (WriterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		int width = result.getWidth();
//		int height = result.getHeight();
//		int[] pixels = new int[width * height];
//		// All are 0, or black, by default
//		for (int y = 0; y < height; y++) {
//			int offset = y * width;
//			for (int x = 0; x < width; x++) {
//				pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
//			}
//		}
//
//		Bitmap bitmap = Bitmap.createBitmap(width, height,
//				Bitmap.Config.ARGB_8888);
//		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//		return bitmap;
//	}
//
//	protected static Bitmap creatCodeBitmap(String contents, int width,
//                                            int height, Context context) {
//		TextView tv = new TextView(context);
//		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//		tv.setLayoutParams(layoutParams);
//		tv.setText(contents);
//		// tv.setHeight(height);
//		tv.setGravity(Gravity.CENTER_HORIZONTAL);
//		tv.setWidth(width);
//		tv.setDrawingCacheEnabled(true);
//		tv.setTextColor(Color.BLACK);
//
//		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 24);
//		tv.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
//				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//		tv.layout(0, 0, tv.getMeasuredWidth(), tv.getMeasuredHeight());
//
//		tv.buildDrawingCache();
//		Bitmap bitmapCode = tv.getDrawingCache();
//		return bitmapCode;
//	}
//
//	protected static Bitmap mixtureBitmap(Bitmap first, Bitmap second,
//                                          PointF fromPoint) {
//		if (first == null || second == null || fromPoint == null) {
//			return null;
//		}
//		int marginW = 0;
//		Bitmap newBitmap = Bitmap.createBitmap(first.getWidth() > second
//				.getWidth() ? first.getWidth() : second.getWidth() + marginW,
//				first.getHeight() + second.getHeight(), Config.ARGB_4444);
//		Canvas cv = new Canvas(newBitmap);
//		cv.drawBitmap(first, marginW, 0, null);
//		cv.drawBitmap(second, fromPoint.x, fromPoint.y, null);
//		cv.save(Canvas.ALL_SAVE_FLAG);
//		cv.restore();
//
//		return newBitmap;
//	}
//
//	public static Bitmap mixtureBitmapForPrint(Bitmap first, Bitmap second,
//                                               PointF fromPoint, int perSize) {
//		if (first == null || second == null || fromPoint == null) {
//			return null;
//		}
//		int marginW = 0;
//		Bitmap newBitmap = Bitmap.createBitmap(first.getWidth() > second
//				.getWidth() ? first.getWidth() : second.getWidth() + marginW,
//				first.getHeight() + second.getHeight() - perSize,
//				Config.RGB_565);
//		Canvas cv = new Canvas(newBitmap);
//		cv.drawColor(Color.WHITE);
//		cv.drawBitmap(first, marginW, 0, null);
//		cv.drawBitmap(second, fromPoint.x, fromPoint.y, null);
//		cv.save(Canvas.ALL_SAVE_FLAG);
//		cv.restore();
//		first.recycle();
//		second.recycle();
//
//		return newBitmap;
//	}
//
//	public static Bitmap convertToBlackWhite(Bitmap bmp) {
//		int width = bmp.getWidth(); // 获取位图的宽
//		int height = bmp.getHeight(); // 获取位图的高
//
//		int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组
//
//		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
//		// int alpha = 0xFF << 24;
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < width; j++) {
//				int grey = pixels[width * i + j];
//
//				// // 分离三原色
//				// int red = ((grey & 0x00FF0000) >> 16);
//				// int green = ((grey & 0x0000FF00) >> 8);
//				// int blue = (grey & 0x000000FF);
//				//
//				// // 转化成灰度像素
//				// grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
//				// grey = alpha | (grey << 16) | (grey << 8) | grey;
//
//				if (isPrint(grey)) {
//					pixels[width * i + j] = Color.BLACK;
//				} else {
//					pixels[width * i + j] = Color.WHITE;
//				}
//
//			}
//		}
//		// 新建图片
//		Bitmap newBmp = Bitmap.createBitmap(width, height, Config.RGB_565);
//		// 设置图片数据
//		newBmp.setPixels(pixels, 0, width, 0, 0, width, height);
//
//		bmp.recycle();
//		return newBmp;
//	}

	public static boolean isPrint(int pixelColor) {
		boolean print = false;
		if (Color.alpha(pixelColor) < 100) {
			return print;
		}
		int newSize = pixelColor & 0x00ffffff;
		int x = (0x00ff0000 & pixelColor) >> 16;
		int y = (0x0000ff00 & pixelColor) >> 8;
		int z = 0x000000ff & pixelColor;

		if (pixelColor == 0 || newSize == 16777215) {
			return false;
		}
		if (x > 171 && x == y && y == z && x == z) {
			return false;
		}

		print = true;

		return print;
	}

	public static int getVersionCode(Context context) {
		int code = -1;
		try {
			code = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return code;
	}

	public static boolean checkPermission(Context context, String permissionName){

		PackageManager pm = context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permissionName,context.getPackageName()));
        if (permission) {
            return true;
        }else {
        	return false;
        }
	}

	public static boolean checkDeviceHasNavigationBar(Context context) {
		boolean has = false;
		Resources res = context.getResources();
		int resourceid = res.getIdentifier("config_showNavigationBar", "bool",
				"android");
		if (resourceid > 0) {
			has = res.getBoolean(resourceid);
		}

		return has;
	}

	public static int getNavigationBarHeight(Context context) {
		int height = 0;
		Resources res = context.getResources();
		int resourceid = res.getIdentifier("navigation_bar_height", "dimen",
				"android");
		if (resourceid > 0 && checkDeviceHasNavigationBar(context)) {
			height = res.getDimensionPixelSize(resourceid);
		}
		return height;
	}

	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	public static String getVersionName(Context context) {
		String name = "";
		try {
			name = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return name;
	}

	public static int getScreenRealHeight(Context context) {

		int dpi = 0;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
        Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			dpi = dm.heightPixels;
		} catch (Exception e) {
			wm.getDefaultDisplay().getMetrics(dm);
			dpi = dm.heightPixels;
			e.printStackTrace();
		}
		return dpi;

	}

	public static int getScreeHeightWithNoBar(Context context) {

		int dpi = 0;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
        Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			dpi = dm.heightPixels;
		} catch (Exception e) {
			wm.getDefaultDisplay().getMetrics(dm);
			dpi = dm.heightPixels;
			e.printStackTrace();
		}

		dpi = dpi > wm.getDefaultDisplay().getHeight() ? wm.getDefaultDisplay()
				.getHeight() : dpi - Util.getNavigationBarHeight(context);

		dpi -= getStatusBarHeight(context);

		return dpi;

	}

	public static boolean isZh(Context context) {
//		PrintUtil.printMsg("language", context.getResources()
//				.getConfiguration().locale.getLanguage());
		if (context.getResources().getConfiguration().locale.getLanguage()
				.endsWith("zh")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isKo(Context context) {
		if (context.getResources().getConfiguration().locale.getLanguage()
				.endsWith("ko")) {
			return true;
		} else {
			return false;
		}
	}

	public static String doOneday(String today, long num) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date d = new Date(f.parse(today).getTime() + 24 * 3600 * 1000 * num);
			return f.format(d);

		} catch (Exception ex) {
			return "输入格式错误";
		}

	}

	/**
	 * 日期转化为时间戳
	 *
	 * @param date_str
	 * @param format
	 * @return
	 */
	public static long date2NumTimeStamp(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date_str).getTime() / 1000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 时间戳转日期
	 * @param timeStamp
	 * @return
	 */
	public static String timstampTrans2Date(long timeStamp) {
		Date date = new Date(timeStamp);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}

	public static String timstampTrans2DateNoHour(long timeStamp) {
		Date date = new Date(timeStamp);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}

	/**
	 * 调起第三方应用
	 *
	 * @param activity
	 * @param pckName
	 *            包名
	 * @param launchName
	 *            启动页名（全称）
	 * @param token
	 *            令牌
	 * @throws Exception
	 *             调用此方法需捕获异常，不同界面不同处理方式
	 */
//	public static void startLauncherApp(Activity activity, String pckName,
//                                        String launchName, String token) throws Exception {
//		Intent intent = new Intent(Intent.ACTION_MAIN);
//		intent.addCategory(Intent.CATEGORY_LAUNCHER);
//		ComponentName componentName = new ComponentName(pckName, launchName);
//		intent.putExtra(PlugParams.TOKEN, token);
//		intent.setComponent(componentName);
//		activity.startActivity(intent);
//	}

	/**
	 * 以流的方式加载图片
	 *
	 * @param path
	 *            图片存储路径
	 * @return
	 */
	public static Bitmap getBitmapByPath(String path) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		File file = new File(path);
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(fs, null, opt);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			System.gc();
		}
		try {
			if (fs != null)
				fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (fs != null)
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return bitmap;
	}
	
	
		public static File upZipFile(File zipFile, File descFile) {
		OutputStream out = null;
		InputStream in = null;
		ZipFile zf = null;
		try {
			File desDir = new File(descFile.getAbsolutePath());
			if (!desDir.exists()) {
				desDir.mkdirs();
			}
			File files[] = descFile.listFiles();
			if (files != null) {
				for (File f : files) {
					f.delete();
				}
			}

			zf = new ZipFile(zipFile);
			for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
				ZipEntry entry = ((ZipEntry) entries.nextElement());
				in = zf.getInputStream(entry);
				String str = descFile.getAbsolutePath() + File.separator + entry.getName();
				str = new String(str.getBytes("8859_1"), "GB2312");
				File desFile = new File(str);
				if (!desFile.exists()) {
					File fileParentDir = desFile.getParentFile();
					if (!fileParentDir.exists()) {
						fileParentDir.mkdirs();
					}
					desFile.createNewFile();
				}
				out = new FileOutputStream(desFile);
				byte buffer[] = new byte[1024];
				int realLength;
				while ((realLength = in.read(buffer)) > 0) {
					out.write(buffer, 0, realLength);
				}
				in.close();
				out.close();
				zf.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (zf != null)
					zf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (descFile != null) {
			File files[] = descFile.listFiles();
			if (files != null) {
				for (File f : files) {
					return f;
				}
			}
		}
		return null;

	}
	
	/**
	 * 检查字符串是否为空
	 * 
	 * @param string
	 * @return 空:true
	 */
	public static boolean isEmpty(String string) {
		if (string != null && string.length() > 0) {
			return false;
		}
		return true;
	}

//	public static String getPhoneCode(Context context) {
//		if (context == null) {
//			return "";
//		}
//		if (UserData.isVistorMode(context)) {
//			return Constant.YANSHI_TOKEN;
//		}
//
//		TelephonyManager tm = (TelephonyManager) context
//				.getSystemService(Context.TELEPHONY_SERVICE);
//
//		String mac = BluetoothAdapter.getDefaultAdapter().getAddress();
//
//		if (mac == null || mac.length() == 0) {
//			if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
//				BluetoothAdapter.getDefaultAdapter().enable();
//			}
//			mac = BluetoothAdapter.getDefaultAdapter().getAddress();
//		}
//
//		String display = android.os.Build.DISPLAY;
//		if (display == null) {
//			display = "";
//		}
//		String board = android.os.Build.BOARD;
//		if (board == null) {
//			board = "";
//		}
//		String model = android.os.Build.MODEL;
//		if (model == null) {
//			model = "";
//		}
//		String cpu_abi = android.os.Build.CPU_ABI;
//		if (cpu_abi == null) {
//			cpu_abi = "";
//		}
//		String brand = android.os.Build.BRAND;
//		if (brand == null) {
//			brand = "";
//		}
//		String serial = android.os.Build.SERIAL;
//		if (serial == null) {
//			serial = "";
//		}
//
//		if (mac == null) {
//			mac = "";
//		}
//
//		// 品牌 CPU 型号 屏幕 主板 imei 蓝牙mac 序列号
//		// return getMD5Code("15" + brand + cpu_abi + model + display + board
//		// + imei + mac + serial + "v1.0");
//		return getMD5Code(
//				"15" + brand + cpu_abi + model + board + mac + serial + "v1.0")
//				.toUpperCase();
//
//	}
}
