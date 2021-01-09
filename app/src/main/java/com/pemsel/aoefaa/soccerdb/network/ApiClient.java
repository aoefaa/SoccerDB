package com.pemsel.aoefaa.soccerdb.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create(new Gson()))
                    .build();
        }
        return retrofit;
    }
}
