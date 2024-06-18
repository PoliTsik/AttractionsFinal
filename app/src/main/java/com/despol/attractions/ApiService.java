package com.despol.attractions;

import com.despol.attractions.AttractionsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface ApiService {
    @GET("attractions")
    Call<List<AttractionsModel>> getAttractions();

    @POST("saveAttraction")
    Call<Void> saveAttraction(@Body AttractionsModel attraction);
}
