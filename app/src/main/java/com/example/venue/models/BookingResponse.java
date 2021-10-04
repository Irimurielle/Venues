package com.example.venue.models;

public class BookingResponse {

    private int venue_id;
    private String guest_name;
    private String depart_date;
    private String arrival_date;
    private String arrival_time;
    private String guest_email;
    private String guest_phone;
    private int how_many;
    private String special_request;

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(String depart_date) {
        this.depart_date = depart_date;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public int getHow_many() {
        return how_many;
    }

    public void setHow_many(int how_many) {
        this.how_many = how_many;
    }

    public String getSpecial_request() {
        return special_request;
    }

    public void setSpecial_request(String special_request) {
        this.special_request = special_request;
    }
}

