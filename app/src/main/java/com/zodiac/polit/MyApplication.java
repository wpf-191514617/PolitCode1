package com.zodiac.polit;

import android.app.Application;
import android.content.Context;

import com.minilive.library.BaseApplication;

/**
 * Created by john on 2018/9/29.
 */

public class MyApplication extends BaseApplication {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }

}
