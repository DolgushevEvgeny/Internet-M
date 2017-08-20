package com.example.eugenedolgushev.internet_m.Parsers;

import com.example.eugenedolgushev.internet_m.Model.Category;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryParser {

    public static ArrayList<Category> parseCategories(JSONArray list) {
        ArrayList<Category> categories = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < list.length(); ++i) {
            JSONObject object = null;
            try {
                object = list.getJSONObject(i);
                Category parsedObject = gson.fromJson(String.valueOf(object), Category.class);
                categories.add(parsedObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }
}