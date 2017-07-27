package com.example.eugenedolgushev.internet_m.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.eugenedolgushev.internet_m.AsyncTask.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.AsyncTask.impl.AsyncTask;
import com.example.eugenedolgushev.internet_m.CustomOnItemClickListener;
import com.example.eugenedolgushev.internet_m.ListAdapters.ProductAdapter;
import com.example.eugenedolgushev.internet_m.Model.Product;
import com.example.eugenedolgushev.internet_m.R;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements OnAsyncTaskCompleted{

    private static final String URL = "http://onlinestore.whitetigersoft.ru/api/common/product/list";
    private RecyclerView productsRV = null;
    private ProductAdapter productAdapter;
    private List<Product> products = new ArrayList<>();
    private static final String testValue = "{\"meta\":{\"success\":true,\"error\":\"\"},\"data\":[{\"productId\":274,\"title\":\"ASUS GeForce GTX 1060\",\"productDescription\":\"ASUS GeForce GTX 1060 1569Mhz PCI-E 3.0 3072Mb 8008Mhz 192 bit DVI 2xHDMI HDCP\",\"price\":30990,\"rating\":0,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/bRnciBmVNQNP462kARLZ6Ypo8R56AVre.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/bRnciBmVNQNP462kARLZ6Ypo8R56AVre.jpg\"]},{\"productId\":411,\"title\":\"MSI GeForce GTX 1060\",\"productDescription\":\"MSI GeForce GTX 1060 1594Mhz PCI-E 3.0 6144Mb 8100Mhz 192 bit DVI HDMI HDCP\\r\\n\\r\\nКОРОТКО О ТОВАРЕ :\\r\\nвидеокарта NVIDIA GeForce GTX 1060\\r\\n6144 Мб видеопамяти GDDR5\\r\\nчастота ядра/памяти: 1594/8100 МГц\\r\\nразъемы DVI, HDMI, DisplayPort x3\\r\\nподдержка DirectX 12, OpenGL 4.5\\r\\nработа с 4 мониторами\",\"price\":131,\"rating\":4,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xjq6kJsC2sm5yoHWayrkYjZxed6oDRmR.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xjq6kJsC2sm5yoHWayrkYjZxed6oDRmR.jpg\"]},{\"productId\":418,\"title\":\"GIGABYTE GeForce GTX 1060\",\"productDescription\":\"GIGABYTE GeForce GTX 1060 1582Mhz PCI-E 3.0 6144Mb 8008Mhz 192 bit 2xDVI HDMI HDCP\\r\\n\\r\\nКОРОТКО О ТОВАРЕ :\\r\\nвидеокарта NVIDIA GeForce GTX 1060\\r\\n6144 Мб видеопамяти GDDR5\\r\\nчастота ядра/памяти: 1582/8008 МГц\\r\\nразъемы DVI x2, HDMI, DisplayPort\\r\\nподдержка DirectX 12, OpenGL 4.5\\r\\nработа с 4 мониторами\",\"price\":37990,\"rating\":null,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/hyvAzknJ-qbLIo1RAn6mWkDYEXQU78Xs.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/hyvAzknJ-qbLIo1RAn6mWkDYEXQU78Xs.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/gRIsTPXmSuKHJwfs0ArsX5Gda0_tJINk.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xeDOksVyccrCWZSFfxJm5tX7MWp1HiZp.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/WSETT9WVydpyNTmW1HbAOBtRsdQRmaS4.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/dQ_xjgdOD0oxM_f5bKs0vX80vAPxBc2l.jpg\"]}]}\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("categoryTitle");
        setTitle(title);
        Integer categoryId = bundle.getInt("categoryId");

        productsRV = (RecyclerView) findViewById(R.id.product_recycler_view);
        productsRV.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter((ArrayList) products, new CustomOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        productsRV.setAdapter(productAdapter);

        AsyncTask getProducts = new AsyncTask(this, this);
        RequestParams params = new RequestParams();
        params = requestParams(params, "appKey", "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");
        params = requestParams(params, "categoryId", categoryId.toString());
        getProducts.executeAsyncTask(URL, params);
        //processResult();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void processResult() {
        try {
            JSONObject result = new JSONObject(testValue);
            JSONArray list;
            if (result.has("meta")) {
                JSONObject meta = result.getJSONObject("meta");
                if (meta.has("success")) {
                    boolean success = meta.getBoolean("success");
                    if (success) {
                        if (result.has("data")) {
                            list = result.getJSONArray("data");
                            for (int i = 0; i < list.length(); ++i) {
                                Product product = new Product();
                                JSONObject recordJsonObj = list.getJSONObject(i);
                                if (recordJsonObj.has("productId")) {
                                    product.setProductId(recordJsonObj.getInt("productId"));
                                }
                                if (recordJsonObj.has("title")) {
                                    product.setTitle(recordJsonObj.getString("title"));
                                }
                                if (recordJsonObj.has("productDescription")) {
                                    product.setDescription(recordJsonObj.getString("productDescription"));
                                }
                                if (recordJsonObj.has("price")) {
                                    product.setPrice(recordJsonObj.getInt("price"));
                                }
                                if (recordJsonObj.has("rating")) {
                                    Object obj = recordJsonObj.get("rating");
                                    if (obj.equals("null")) {
                                        product.setRating(recordJsonObj.getInt("rating"));
                                    }
                                }
                                if (recordJsonObj.has("imageUrl")) {
                                    product.setImageUrl(recordJsonObj.getString("imageUrl"));
                                }
                                if (recordJsonObj.has("images")) {
                                    JSONArray arr = recordJsonObj.getJSONArray("images");
                                    ArrayList<String> t = new ArrayList<>();
                                    for (int j = 0; j < arr.length(); ++j) {
                                        t.add(arr.getString(j));
                                    }
                                    product.setImages(t);
                                }

                                products.add(product);
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {

        }

        productAdapter.setList((ArrayList) products);
        productsRV.setAdapter(productAdapter);
    }

    @Override
    public void taskCompleted(JSONObject result) {
        Gson gson = new Gson();
        try {
            if (result.has("meta")) {
                JSONObject meta = result.getJSONObject("meta");
                if (meta.has("success")) {
                    boolean success = meta.getBoolean("success");
                    if (success) {
                        if (result.has("data")) {
                            JSONArray list = result.getJSONArray("data");
                            for (int i = 0; i < list.length(); ++i) {
                                products.add(gson.fromJson(String.valueOf(list.getJSONObject(i)), Product.class));
                            }
                        }
                    }
                }
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }

        productAdapter.setList((ArrayList) products);
        productsRV.setAdapter(productAdapter);
    }

    private RequestParams requestParams(RequestParams params, final String key, final String value) {
        params.put(key, value);
        return params;
    }
}
