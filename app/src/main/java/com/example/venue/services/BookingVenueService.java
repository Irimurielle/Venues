package com.example.venue.services;

import com.example.venue.models.BookingVenueResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookingVenueService {

    @GET("api/v1//book/now/{id}")
    Call<List<BookingVenueResponse>> getAllBookedVenues(@Path("id") int id);
}
