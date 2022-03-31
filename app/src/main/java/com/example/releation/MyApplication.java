package com.example.releation;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class MyApplication extends Application {
    private static Application applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        fetchData();
    }

    public static Application getApplication() {
        if(applicationContext == null) {
            new MyApplication();
        }
        return applicationContext;
    }

    public static LiveData<Boolean> fetchData() {
        return Util.dataFetched();
    }
}
