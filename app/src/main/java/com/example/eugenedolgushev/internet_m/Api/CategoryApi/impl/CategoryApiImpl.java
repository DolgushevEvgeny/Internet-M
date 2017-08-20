package com.example.eugenedolgushev.internet_m.Api.CategoryApi.impl;

import android.content.Context;

import com.example.eugenedolgushev.internet_m.Api.BaseApi;
import com.example.eugenedolgushev.internet_m.Api.CategoryApi.CategoryApiListener;
import com.example.eugenedolgushev.internet_m.AsyncTask.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.Model.Category;
import com.example.eugenedolgushev.internet_m.Parsers.CategoryParser;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryApiImpl extends BaseApi {

    private static final String URL = "common/category/list";
    private Context context;

    public CategoryApiImpl(final Context context) {
        super();
        this.context = context;
    }

    public void getCategories(RequestParams params, final CategoryApiListener listener) {
        super.get(URL, params, new OnAsyncTaskCompleted() {
            @Override
            public void taskCompleted(Object data) {
                JSONObject object = (JSONObject)data;
                JSONArray categoriesArray = null;
                try {
                    categoriesArray = object.getJSONArray("categories");
                    ArrayList<Category> categories = CategoryParser.parseCategories(categoriesArray);
                    if (categories != null) {
                        listener.onCategoriesLoaded(categories);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
