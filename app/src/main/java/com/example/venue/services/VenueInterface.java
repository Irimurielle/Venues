package com.example.venue.services;

import com.example.venue.models.VenueList;
import com.example.venue.models.VenueResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VenueInterface {

    @GET("api/v1/landing/")
    Call<VenueList> getVenueResponses();
}
