package com.example.venue.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VenueList {

    private VenueResponse[] venues;

    public VenueResponse[] getVenues() {
        return venues;
    }

    public void setVenues(VenueResponse[] venues) {
        this.venues = venues;
    }
}
