package com.lqb.android.shopping.app;

import android.app.Application;
import android.content.Context;

public class MyAppliction extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    //获取全局上下文
    public static Context getContext() {
        return context;
    }
}
