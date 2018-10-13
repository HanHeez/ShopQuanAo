package com.gtv.hanhee.shopquanao._Retrofit;

public class APIUtils {
    public static final String Base_url = "http://192.168.0.158:8080/webquara/";

    public static LayDuLieuTuUrl getData() {
        return RetrofitClient.getClient(Base_url).create(LayDuLieuTuUrl.class);
    }
}
