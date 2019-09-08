package com.minilive.library.network.callback;

import com.minilive.library.util.Trace;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        String res = response.body().string();
        return res;
    }
}
