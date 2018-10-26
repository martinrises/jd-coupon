package com.odev.yhq_getter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public static final String HOST = "https://api.m.jd.com";


    private static OkHttpClient mClient;
    private static JdService sMJdService;

    static {
        getClient();
    }

    public static OkHttpClient getClient() {
        if (mClient == null) {
            synchronized (OkHttpClient.class) {
                if (mClient == null) {
                    OkHttpClient.Builder okhttpBuild = new OkHttpClient.Builder();
                    okhttpBuild.connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(false);
                    mClient = okhttpBuild.build();
                }
            }
        }
        return mClient;
    }

    public static JdService getOrderService() {
        if (sMJdService == null) {
            synchronized (JdService.class) {
                if (sMJdService == null) {
                    sMJdService = onCreate(JdService.class, HOST);
                }
            }
        }
        return sMJdService;
    }

    public static <T> T onCreate(Class<T> tclass, String url) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build();
        return build.create(tclass);
    }

}
