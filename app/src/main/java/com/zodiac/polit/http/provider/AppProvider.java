package com.zodiac.polit.http.provider;

import android.content.Context;

import com.minilive.library.http.callback.OnJsonCallBack;
import com.zodiac.polit.Constant;
import com.zodiac.polit.http.BaseProvider;

public class AppProvider extends BaseProvider {

    public static void checkUpdate(Context context, OnJsonCallBack onJsonCallBack) {
        post(context, Constant.BASEURL + "/airforce/rest/cms/app/getLastedAppInfo", onJsonCallBack);
    }

}
