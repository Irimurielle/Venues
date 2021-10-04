package com.example.venue.services;

import com.example.venue.models.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BookingService {

    @POST("api/v1/book/now/venue/")
    Call<BookingResponse> saveBooking(@Body BookingRequest bookingRequest);
}
