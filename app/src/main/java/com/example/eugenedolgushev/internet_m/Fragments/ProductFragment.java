package com.example.eugenedolgushev.internet_m.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eugenedolgushev.internet_m.Api.ProductApi.OnProductParsed;
import com.example.eugenedolgushev.internet_m.Api.ProductApi.impl.ProductApiImpl;
import com.example.eugenedolgushev.internet_m.CustomOnItemClickListener;
import com.example.eugenedolgushev.internet_m.ListAdapters.ProductAdapter;
import com.example.eugenedolgushev.internet_m.Model.Product;
import com.example.eugenedolgushev.internet_m.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductFragment extends Fragment implements OnProductParsed {

    private RecyclerView productsRV = null;
    private ProductAdapter productAdapter;
    private ArrayList<Product> products = new ArrayList<>();
    private static final String testValue = "{\"meta\":{\"success\":true,\"error\":\"\"},\"data\":[{\"productId\":274,\"title\":\"ASUS GeForce GTX 1060\",\"productDescription\":\"ASUS GeForce GTX 1060 1569Mhz PCI-E 3.0 3072Mb 8008Mhz 192 bit DVI 2xHDMI HDCP\",\"price\":30990,\"rating\":0,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/bRnciBmVNQNP462kARLZ6Ypo8R56AVre.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/bRnciBmVNQNP462kARLZ6Ypo8R56AVre.jpg\"]},{\"productId\":411,\"title\":\"MSI GeForce GTX 1060\",\"productDescription\":\"MSI GeForce GTX 1060 1594Mhz PCI-E 3.0 6144Mb 8100Mhz 192 bit DVI HDMI HDCP\\r\\n\\r\\nКОРОТКО О ТОВАРЕ :\\r\\nвидеокарта NVIDIA GeForce GTX 1060\\r\\n6144 Мб видеопамяти GDDR5\\r\\nчастота ядра/памяти: 1594/8100 МГц\\r\\nразъемы DVI, HDMI, DisplayPort x3\\r\\nподдержка DirectX 12, OpenGL 4.5\\r\\nработа с 4 мониторами\",\"price\":131,\"rating\":4,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xjq6kJsC2sm5yoHWayrkYjZxed6oDRmR.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xjq6kJsC2sm5yoHWayrkYjZxed6oDRmR.jpg\"]},{\"productId\":418,\"title\":\"GIGABYTE GeForce GTX 1060\",\"productDescription\":\"GIGABYTE GeForce GTX 1060 1582Mhz PCI-E 3.0 6144Mb 8008Mhz 192 bit 2xDVI HDMI HDCP\\r\\n\\r\\nКОРОТКО О ТОВАРЕ :\\r\\nвидеокарта NVIDIA GeForce GTX 1060\\r\\n6144 Мб видеопамяти GDDR5\\r\\nчастота ядра/памяти: 1582/8008 МГц\\r\\nразъемы DVI x2, HDMI, DisplayPort\\r\\nподдержка DirectX 12, OpenGL 4.5\\r\\nработа с 4 мониторами\",\"price\":37990,\"rating\":null,\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/hyvAzknJ-qbLIo1RAn6mWkDYEXQU78Xs.jpg\",\"images\":[\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/hyvAzknJ-qbLIo1RAn6mWkDYEXQU78Xs.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/gRIsTPXmSuKHJwfs0ArsX5Gda0_tJINk.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/xeDOksVyccrCWZSFfxJm5tX7MWp1HiZp.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/WSETT9WVydpyNTmW1HbAOBtRsdQRmaS4.jpg\",\"http://onlinestore.whitetigersoft.ru/uploads/product-icons/dQ_xjgdOD0oxM_f5bKs0vX80vAPxBc2l.jpg\"]}]}\n";
    private Context context;
    private ProductApiImpl productApi;

    public ProductFragment() {}

    public void setContext(final Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Продукты");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Object obj = bundle.get("categoryTitle");
            if (obj != null) {
                String title = (String) obj;
                getActivity().setTitle(title);
            }
            obj = bundle.get("categoryId");
            if (obj != null) {
                Integer categoryId = (Integer) obj;
            }
        }

        productApi = new ProductApiImpl(context, this);

        productsRV = (RecyclerView) getActivity().findViewById(R.id.product_recycler_view);
        productsRV.setLayoutManager(new LinearLayoutManager(context));
        productAdapter = new ProductAdapter(products, new CustomOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        productsRV.setAdapter(productAdapter);
//        processResult();
        if (bundle != null) {
            productApi.getProducts(bundle);
        }
        productApi.getProducts();
    }

    private void processResult() {
        Gson gson = new Gson();
        try {
            JSONObject result = new JSONObject(testValue);
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

        productAdapter.setList(products);
        productsRV.setAdapter(productAdapter);
    }

    @Override
    public void setProducts(ArrayList<Product> products) {
        productAdapter.setList(products);
        productsRV.setAdapter(productAdapter);
    }
}
