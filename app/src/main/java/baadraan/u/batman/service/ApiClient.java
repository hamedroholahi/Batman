package baadraan.u.batman.service;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private Retrofit retrofit;
    private static ApiClient instance = null;


    public static String BASE_URL = "http://www.omdbapi.com/";

    public static ApiClient getInstance(){
        if (instance == null)
            instance = new ApiClient();
        return instance;
    }

    private ApiClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(getOKHttp())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    private OkHttpClient getOKHttp(){
        return new OkHttpClient.Builder().connectTimeout(60 , TimeUnit.SECONDS).build();
    }
}
