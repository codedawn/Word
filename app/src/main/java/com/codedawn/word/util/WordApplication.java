package com.codedawn.word.util;

import android.app.Application;
import android.content.Context;

public class WordApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
