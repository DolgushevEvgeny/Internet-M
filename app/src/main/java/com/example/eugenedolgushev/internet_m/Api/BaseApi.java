package com.example.eugenedolgushev.internet_m.Api;

import com.example.eugenedolgushev.internet_m.AsyncTask.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.BuildConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class BaseApi {

    private AsyncHttpClient asyncHttpClient;
    private static final String BASIC_URL = BuildConfig.BASIC_URL;

    public BaseApi() {
        this.asyncHttpClient = new AsyncHttpClient();
    }

//    public void get(String url) {
//        get(url, new RequestParams());
//    }

    protected void get(final String url, RequestParams queryParams, final OnAsyncTaskCompleted asyncListener) {
        queryParams = requestParams(queryParams, "appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");
        this.asyncHttpClient.get(BASIC_URL + url, queryParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("meta")) {
                        JSONObject meta = response.getJSONObject("meta");
                        if (meta.has("success")) {
                            boolean success = meta.getBoolean("success");
                            if (success) {
                                if (response.has("data")) {
                                    Object data = response.get("data");
                                    asyncListener.taskCompleted(data);
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                asyncListener.taskCompleted(errorResponse);
            }
        });
    }

    public void post(final String url, RequestParams queryParams, final OnAsyncTaskCompleted asyncListener) {
        queryParams = requestParams(queryParams, "appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");
        this.asyncHttpClient.post(BASIC_URL + url, queryParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("meta")) {
                        JSONObject meta = response.getJSONObject("meta");
                        if (meta.has("success")) {
                            boolean success = meta.getBoolean("success");
                            if (success) {
                                if (response.has("data")) {
                                    Object data = response.get("data");
                                    asyncListener.taskCompleted(data);
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    protected RequestParams requestParams(RequestParams params, final String key, final String value) { // TODO: 15.08.2017 rename to prepareParams(params)
        params.put(key, value);
        return params;
    }
}
