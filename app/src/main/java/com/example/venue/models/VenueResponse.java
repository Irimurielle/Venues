package com.example.venue.models;

public class VenueResponse {

    private Integer id;
    private String property_name;
    private String contact_phone;
    private String profile_image;
    private String venue_rating;
    private String address;
    private String main_address;
    private String property_description;
    private String property_website;

    public Integer getId() {
        return id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getVenue_rating() {
        return venue_rating;
    }

    public String getAddress() {
        return address;
    }

    public String getMain_address() {
        return main_address;
    }

    public String getProperty_description() {
        return property_description;
    }

    public String getProperty_website() {
        return property_website;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setVenue_rating(String venue_rating) {
        this.venue_rating = venue_rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMain_address(String main_address) {
        this.main_address = main_address;
    }

    public void setProperty_description(String property_description) {
        this.property_description = property_description;
    }

    public void setProperty_website(String property_website) {
        this.property_website = property_website;
    }
}
