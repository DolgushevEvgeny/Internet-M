package com.example.eugenedolgushev.internet_m.Api;

import com.example.eugenedolgushev.internet_m.AsyncTask.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.BuildConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class BaseApi {

    private AsyncHttpClient asyncHttpClient;
    private OnAsyncTaskCompleted asyncListener;
    private static final String BASIC_URL = BuildConfig.BASIC_URL;

    public BaseApi() {
        this.asyncHttpClient = new AsyncHttpClient();
    }

    public void setAsyncListener(OnAsyncTaskCompleted listener) {
        this.asyncListener = listener;
    }

    public void get(String url) {
        get(url, new RequestParams());
    }

    public void get(String url, RequestParams queryParams) {
        queryParams = requestParams(queryParams, "appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");
        this.asyncHttpClient.get(BASIC_URL + url, queryParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                asyncListener.taskCompleted(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                asyncListener.taskCompleted(errorResponse);
            }
        });
    }

    public void post(String url, RequestParams queryParams) {
        this.asyncHttpClient.post(BASIC_URL + url, queryParams, new JsonHttpResponseHandler() {
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

    protected RequestParams requestParams(RequestParams params, final String key, final String value) {
        params.put(key, value);
        return params;
    }
}
