package com.example.eugenedolgushev.internet_m.Parsers;

import com.example.eugenedolgushev.internet_m.Model.Product;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductParser {

    public static ArrayList<Product> parseProducts(JSONArray list) {
        ArrayList<Product> products = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < list.length(); ++i) {
            JSONObject object = null;
            try {
                object = list.getJSONObject(i);
                Product parsedObject = gson.fromJson(String.valueOf(object), Product.class);
                products.add(parsedObject);
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
}
