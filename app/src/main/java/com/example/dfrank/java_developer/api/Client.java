package com.example.dfrank.java_developer.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dfrank on 6/13/17.
 */

public class Client {
    public static String base_url = "https://api.github.com";
    Retrofit retrofit = null;

    public Retrofit getClient(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
