package com.example.eugenedolgushev.internet_m;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AsyncTask {

    private AsyncHttpClient asyncHttpClient;
    private RequestParams requestParams;

    private Context context;
    private OnAsyncTaskCompleted asyncListener;

    public AsyncTask(Context context, OnAsyncTaskCompleted listener) {
        this.asyncHttpClient = new AsyncHttpClient();
        this.requestParams = new RequestParams();
        this.context = context;
        this.asyncListener = listener;
    }

    public void executeAsyncTask(String url, String queryParams) {
        this.requestParams.put("appKey", queryParams);
        this.asyncHttpClient.get(url, this.requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                asyncListener.taskCompleted(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
