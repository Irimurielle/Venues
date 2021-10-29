package com.example.venue.models;

public class CategoriesList {

    private Categories[] venues_category;
    private SpaceDetailsResponse[] space_details;

    public Categories[] getVenues() {
        return venues_category;
    }

    public void setVenues(Categories[] venues_category) {
        this.venues_category = venues_category;
    }

    public SpaceDetailsResponse[] getSpace_details() {
        return space_details;
    }

    public void setSpace_details(SpaceDetailsResponse[] space_details) {
        this.space_details = space_details;
    }
}
