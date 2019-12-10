package com.easyswitch.serbianbookers;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WebApiManager {

    private static WebApiManager webApiManager;

    private Context appContext;
    private WebApi webApi;
    private OkHttpClient client;

    private WebApiManager(Context appContext) {
        this.appContext = appContext;
    }

    public static WebApiManager get(Context context) {
        if (webApiManager == null) {
            webApiManager = new WebApiManager(context.getApplicationContext());
        }
        return webApiManager;
    }

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public WebApi getWebApi() {
        if (webApi == null) {
            webApi = new Retrofit.Builder()
                    .baseUrl(WebApi.BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
                    .create(WebApi.class);
        }
        return webApi;
    }

    OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(WebApi.TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(WebApi.TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder request = chain.request().newBuilder();
//                            request.header("Content-Type", "application/json");
                            return chain.proceed(request.build());
                        }
                    })
                    .build();
        }
        return client;
    }
}
