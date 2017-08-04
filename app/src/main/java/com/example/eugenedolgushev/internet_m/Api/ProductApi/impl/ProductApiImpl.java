package com.example.eugenedolgushev.internet_m.Api.ProductApi.impl;

import android.content.Context;
import android.os.Bundle;

import com.example.eugenedolgushev.internet_m.Api.BaseApi;
import com.example.eugenedolgushev.internet_m.Api.ProductApi.OnProductParsed;
import com.example.eugenedolgushev.internet_m.Api.ProductApi.ProductApi;
import com.example.eugenedolgushev.internet_m.AsyncTask.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.Model.Product;
import com.example.eugenedolgushev.internet_m.Parsers.ProductParser;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductApiImpl extends BaseApi implements ProductApi, OnAsyncTaskCompleted {

    private static final String URL = "common/product/list";
    private Context context;
    private OnProductParsed onProductParsed;
    private static final String testValue = "{\"meta\":{\"success\":true,\"error\":\"\"},\"data\":[{\"productId\":274,\"title\":\"ASUS GeForce GTX 1060\",\"productDescription\":\"ASUS GeForce GTX 1060 1569Mhz PCI-E 3.0 3072Mb 8008Mhz 192 bit DVI 2xHDMI HDCP\",\"price\":30990,\"rating\":0,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/bRnciBmVNQNP462kARLZ6Ypo8R56AVre.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/bRnciBmVNQNP462kARLZ6Ypo8R56AVre.jpg\"]},{\"productId\":411,\"title\":\"MSI GeForce GTX 1060\",\"productDescription\":\"MSI GeForce GTX 1060 1594Mhz PCI-E 3.0 6144Mb 8100Mhz 192 bit DVI HDMI HDCP\\r\\n\\r\\nКОРОТКО О ТОВАРЕ :\\r\\nвидеокарта NVIDIA GeForce GTX 1060\\r\\n6144 Мб видеопамяти GDDR5\\r\\nчастота ядра/памяти: 1594/8100 МГц\\r\\nразъемы DVI, HDMI, DisplayPort x3\\r\\nподдержка DirectX 12, OpenGL 4.5\\r\\nработа с 4 мониторами\",\"price\":131,\"rating\":4,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xjq6kJsC2sm5yoHWayrkYjZxed6oDRmR.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xjq6kJsC2sm5yoHWayrkYjZxed6oDRmR.jpg\"]},{\"productId\":418,\"title\":\"GIGABYTE GeForce GTX 1060\",\"productDescription\":\"GIGABYTE GeForce GTX 1060 1582Mhz PCI-E 3.0 6144Mb 8008Mhz 192 bit 2xDVI HDMI HDCP\\r\\n\\r\\nКОРОТКО О ТОВАРЕ :\\r\\nвидеокарта NVIDIA GeForce GTX 1060\\r\\n6144 Мб видеопамяти GDDR5\\r\\nчастота ядра/памяти: 1582/8008 МГц\\r\\nразъемы DVI x2, HDMI, DisplayPort\\r\\nподдержка DirectX 12, OpenGL 4.5\\r\\nработа с 4 мониторами\",\"price\":37990,\"rating\":null,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/hyvAzknJ-qbLIo1RAn6mWkDYEXQU78Xs.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/hyvAzknJ-qbLIo1RAn6mWkDYEXQU78Xs.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/gRIsTPXmSuKHJwfs0ArsX5Gda0_tJINk.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xeDOksVyccrCWZSFfxJm5tX7MWp1HiZp.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/WSETT9WVydpyNTmW1HbAOBtRsdQRmaS4.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/dQ_xjgdOD0oxM_f5bKs0vX80vAPxBc2l.jpg\"]}]}\n";

    public ProductApiImpl(final Context context, final OnProductParsed onProductParsed) {
        super();
        this.context = context;
        this.onProductParsed = onProductParsed;
        super.setAsyncListener(this);
    }

    @Override
    public void getProducts(Bundle bundle) {
        RequestParams params = new RequestParams();
        params = requestParams(params, "categoryId", String.valueOf(bundle.getInt("categoryId")));
        super.get(URL, params);
    }

    @Override
    public void getProducts() {
        RequestParams params = new RequestParams();
        super.get(URL, params);
    }

    @Override
    public void taskCompleted(JSONObject result) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(testValue);
            ArrayList<Product> products = ProductParser.parseProducts(jsonObject);
            if (products != null) {
                onProductParsed.setProducts(products);
            }
        } catch (JSONException e) {

        }
    }
}
