package com.codedawn.word.util;

import android.util.Log;

public class LogUtil {
    private static final int VERBOSE =1;
    private static final int DEBUG =2;
    private static final int INFO =3;
    private static final int WARNING =4;
    private static final int ERROR =5;
    private static final int NOTHING =6;

    private static int level = 2;

    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }
    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag, msg);
        }
    }
    public static void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag, msg);
        }
    }
    public static void w(String tag, String msg) {
        if (level <= WARNING) {
            Log.w(tag, msg);
        }
    }
    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.e(tag, msg);
        }
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        LogUtil.level = level;
    }
}
