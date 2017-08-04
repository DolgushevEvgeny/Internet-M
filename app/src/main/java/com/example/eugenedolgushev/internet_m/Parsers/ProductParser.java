package com.example.eugenedolgushev.internet_m.Parsers;

import com.example.eugenedolgushev.internet_m.Model.Product;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductParser {

    public static ArrayList<Product> parseProducts(JSONObject result) {
        result = BasicParser.parse(result);
        ArrayList<Product> products = new ArrayList<>();
        if (result != null) {
            Gson gson = new Gson();
            try {
                JSONArray list = result.getJSONArray("data");
                for (int i = 0; i < list.length(); ++i) {
                    products.add(gson.fromJson(String.valueOf(list.getJSONObject(i)), Product.class));
                }
                return products;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
