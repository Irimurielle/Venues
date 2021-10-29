package com.example.venue.services;

import com.example.venue.models.CategoriesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("api/v1/search/category/{id}")
    Call<CategoriesList> getAllSearchedCategory(@Path("id") int id);
}
