package com.example.houwei.hwshop.utils;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;

public class PrintUtil {
	public static void printMsg(String str1, String str2) {
		Log.i("haha", str1 + ":" + str2);
	}

	public static void printMsgAndWrite(String str1, String str2) {
		// System.out.println(str1 + ":" + str2);
		// FileUtil.write2Log("", str1 + ":" + str2);
	}

//	public static void displayBriefMemory(Context context) {
//		// 应用程序最大可用内存
//		int maxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024 / 1024;
//		// 应用程序已获得内存
//		long totalMemory = ((int) Runtime.getRuntime().totalMemory()) / 1024 / 1024;
//		// 应用程序已获得内存中未使用内存
//		long freeMemory = ((int) Runtime.getRuntime().freeMemory()) / 1024;
//		Log.i("haha", "---> maxMemory=" + maxMemory + "M,totalMemory="
//				+ totalMemory + "M,freeMemory=" + (freeMemory / 1024)
//				+ "M,freeMemory=" + freeMemory + "K");
//
//	}

	public static  long getBitmapsize(Bitmap bitmap) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitmap.getByteCount();
		}
		// Pre HC-MR1
		return bitmap.getRowBytes() * bitmap.getHeight();

	}
}
