/**
 * @Author 尹银川(yinyc@p2m.com.cn)
 * @date 2015-5-27 下午1:45:15
 * @Class SharedPreferenceUtil
 */
package com.example.houwei.hwshop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @Description SharedPreference工具类
 * @Class SharedPreferenceUtil
 */
public class SharedPreferenceUtil {

    private final static String SP_NAME = "HYB_SPF";
    private static SharedPreferenceUtil mSharedPreferenceUtil;
    private static SharedPreferences preferences;

    private SharedPreferenceUtil() {
    }

    public static SharedPreferenceUtil getSharedPreferenceUtil(Context context) {
        if (mSharedPreferenceUtil == null) {
            mSharedPreferenceUtil = new SharedPreferenceUtil();
        }
        if (preferences == null) {
            preferences = context.getSharedPreferences(SP_NAME,
                    Context.MODE_PRIVATE);
        }
        return mSharedPreferenceUtil;
    }

    public String getInfoFromShared(String key) {
        return preferences.getString(key, null);
    }

    public String getInfoFromShared(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public boolean setInfoToShared(String key, String value) {
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
        return true;
    }

    public boolean setInfoToShared(String key, boolean value) {
        Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
        return true;
    }

    public boolean getInfoFromShared(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void deleteInfoToShared(String key) {
        Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }
}
