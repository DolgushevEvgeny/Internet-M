package com.example.eugenedolgushev.internet_m.Api.CategoryApi.impl;

import android.content.Context;

import com.example.eugenedolgushev.internet_m.Api.BaseApi;
import com.example.eugenedolgushev.internet_m.Api.CategoryApi.CategoryApi;
import com.example.eugenedolgushev.internet_m.Api.CategoryApi.OnCategoryParsed;
import com.example.eugenedolgushev.internet_m.AsyncTask.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.Model.Category;
import com.example.eugenedolgushev.internet_m.Parsers.CategoryParser;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryApiImpl extends BaseApi implements CategoryApi, OnAsyncTaskCompleted {

    private static final String URL = "common/category/list";
    private Context context;
    private OnCategoryParsed onCategoryParsed;

    public CategoryApiImpl(final Context context, final OnCategoryParsed onCategoryParsed) {
        super();
        this.context = context;
        this.onCategoryParsed = onCategoryParsed;
        super.setAsyncListener(this);
    }

    @Override
    public void getCategories() {
        RequestParams params = new RequestParams();
        super.get(URL, params);
    }

    @Override
    public void taskCompleted(JSONObject result) {
        ArrayList<Category> categories = CategoryParser.parseCategories(result);
        if (categories != null) {
            onCategoryParsed.setCategories(categories);
        }
    }
}
