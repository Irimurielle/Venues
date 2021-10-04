package com.example.venue.services;

import com.example.venue.models.SpaceDetails;
import com.example.venue.models.SpaceDetailsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpaceDetailsService {

    @GET("api/v1/space/details/{id}")
    Call<SpaceDetailsList> getAllSpaceDetails(@Path("id") int id);

    /*@GET("api/v1/search/category/{id}")
    Call<List<SpaceDetails>> getAllSearchedCategory(@Path("id") int id);*/
}
