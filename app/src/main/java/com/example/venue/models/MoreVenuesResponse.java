package com.example.venue.models;

import java.io.Serializable;

public class MoreVenuesResponse implements Serializable {

    private Integer id;

    private String venue_name;

    private String space_category_name;

    private String profile_image;

    private String venue_rating;

    public Integer getId() {
        return id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public String getSpace_category_name() {
        return space_category_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getVenue_rating() {
        return venue_rating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public void setSpace_category_name(String space_category_name) {
        this.space_category_name = space_category_name;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setVenue_rating(String venue_rating) {
        this.venue_rating = venue_rating;
    }

}
