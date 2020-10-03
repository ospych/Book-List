package com.example.booksearchlist;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;

public class NetworkService {
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/";
    private static Retrofit mRetrofit;


    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new OkHttpProfilerInterceptor()).build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return mRetrofit;

    }
}
