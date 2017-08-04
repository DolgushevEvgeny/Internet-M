package com.example.eugenedolgushev.internet_m.Parsers;

import com.example.eugenedolgushev.internet_m.Model.Category;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryParser {

    public static ArrayList<Category> parseCategories(JSONObject result) {
        result = BasicParser.parse(result);
        ArrayList<Category> categories = new ArrayList<>();
        if (result != null) {
            Gson gson = new Gson();
            try {
                JSONObject data = result.getJSONObject("data");
                if (data.has("categories")) {
                    JSONArray list = data.getJSONArray("categories");
                    for (int i = 0; i < list.length(); ++i) {
                        categories.add(gson.fromJson(String.valueOf(list.getJSONObject(i)), Category.class));
                    }
                    return categories;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
