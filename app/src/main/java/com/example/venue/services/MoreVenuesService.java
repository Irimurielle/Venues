package com.example.venue.services;

import com.example.venue.models.MoreVenuesList;
import com.example.venue.models.MoreVenuesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoreVenuesService {

    @GET("api/v1/venues")
    Call<MoreVenuesList> getAllMoreVenues();
}
