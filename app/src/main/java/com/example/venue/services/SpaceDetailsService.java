package com.example.venue.services;

import com.example.venue.models.CategoriesList;
import com.example.venue.models.SpaceDetailsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpaceDetailsService {

    @GET("api/v1/space/details/{id}")
    Call<SpaceDetailsList> getAllSpaceDetails(@Path("id") int id);
}
