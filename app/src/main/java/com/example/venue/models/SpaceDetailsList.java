package com.example.venue.models;

public class SpaceDetailsList {

    private SpaceDetailsResponse[] space_details;
    private SpaceGalleryResponse[] space_gallery;
    private SpaceSeatArrangeResponse[] space_seat_arrange;
    private SpaceFacilityResponse[] space_facility;

    public SpaceDetailsResponse[] getSpace_details() {
        return space_details;
    }

    public void setSpace_details(SpaceDetailsResponse[] space_details) {
        this.space_details = space_details;
    }

    public SpaceGalleryResponse[] getSpace_gallery() {
        return space_gallery;
    }

    public void setSpace_gallery(SpaceGalleryResponse[] space_gallery) {
        this.space_gallery = space_gallery;
    }

    public SpaceSeatArrangeResponse[] getSpace_seat_arrange() {
        return space_seat_arrange;
    }

    public void setSpace_seat_arrange(SpaceSeatArrangeResponse[] space_seat_arrange) {
        this.space_seat_arrange = space_seat_arrange;
    }

    public SpaceFacilityResponse[] getSpace_facility() {
        return space_facility;
    }

    public void setSpace_facility(SpaceFacilityResponse[] space_facility) {
        this.space_facility = space_facility;
    }
}
