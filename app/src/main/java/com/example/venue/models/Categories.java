package com.example.venue.models;

import java.io.Serializable;

public class Categories implements Serializable {

    private Integer id;
    /*private String space_profile;
    private String space_activity;
    private String space_location;
    private String space_description;
    private String space_ft;
    private String working_hours;
    private String cancellation_policy;*/
    private String space_category_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*public String getSpace_profile() {
        return space_profile;
    }

    public void setSpace_profile(String space_profile) {
        this.space_profile = space_profile;
    }

    public String getSpace_activity() {
        return space_activity;
    }

    public void setSpace_activity(String space_activity) {
        this.space_activity = space_activity;
    }

    public String getSpace_location() {
        return space_location;
    }

    public void setSpace_location(String space_location) {
        this.space_location = space_location;
    }

    public String getSpace_description() {
        return space_description;
    }

    public void setSpace_description(String space_description) {
        this.space_description = space_description;
    }

    public String getSpace_ft() {
        return space_ft;
    }

    public void setSpace_ft(String space_ft) {
        this.space_ft = space_ft;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    public String getCancellation_policy() {
        return cancellation_policy;
    }

    public void setCancellation_policy(String cancellation_policy) {
        this.cancellation_policy = cancellation_policy;
    }*/

    public String getSpace_category_name() {
        return space_category_name;
    }

    public void setSpace_category_name(String space_category_name) {
        this.space_category_name = space_category_name;
    }
}
