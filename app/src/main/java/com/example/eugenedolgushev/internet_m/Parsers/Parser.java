package com.example.eugenedolgushev.internet_m.Parsers;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {

    public static <T> ArrayList<T> parseObjects(JSONArray list, Class<T> type) {
        ArrayList<T> parsedObjects = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < list.length(); ++i) {
            JSONObject object = null;
            try {
                object = list.getJSONObject(i);
                T parsedObject = gson.fromJson(String.valueOf(object), type);
                parsedObjects.add(parsedObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return parsedObjects;
    }
}
