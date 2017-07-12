package com.example.dfrank.java_developer.api;

import com.example.dfrank.java_developer.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dfrank on 6/13/17.
 */

public interface Service {
    @GET("/search/users?q=language:java+location:lagos")
    Call <ItemResponse> getItems();
}
