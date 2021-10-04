package com.example.venue.services;

import com.example.venue.models.MoreVenuesResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://venues.ewawe.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    public static BookingService getBookingService(){
        BookingService bookingService = getRetrofit().create(BookingService.class);
        return bookingService;
    }
    public static VenueInterface getInterface(){
        VenueInterface venueInterface = getRetrofit().create(VenueInterface.class);
        return venueInterface;
    }
    public static MoreVenuesService getMoreVenueService(){
        MoreVenuesService moreVenuesService = getRetrofit().create(MoreVenuesService.class);
        return moreVenuesService;
    }
    public static BookingVenueService getBookingVenueService(){
        BookingVenueService bookingVenueService = getRetrofit().create(BookingVenueService.class);
        return bookingVenueService;
    }
    public static SpaceDetailsService getSpaceDetailsService(){
        SpaceDetailsService spaceDetailsService = getRetrofit().create(SpaceDetailsService.class);
        return spaceDetailsService;
    }
}
