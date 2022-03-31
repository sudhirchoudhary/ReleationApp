package com.example.releation.data;

import com.example.releation.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("solarCity.json")
    Call<Response> getData();
}
