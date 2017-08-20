package com.example.eugenedolgushev.internet_m.Parsers;

import org.json.JSONException;
import org.json.JSONObject;

class BasicParser { // TODO: 15.08.2017 move Parses to Model 

    static JSONObject parse(JSONObject result) {
        try {
            if (result.has("meta")) {// TODO: 15.08.2017 move to base api
                JSONObject meta = result.getJSONObject("meta");
                if (meta.has("success")) {
                    boolean success = meta.getBoolean("success");
                    if (success) {
                        if (result.has("data")) {
                            return result;
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
