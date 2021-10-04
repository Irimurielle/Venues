package com.example.venue.models;

public class BookingVenueResponse {


    public static class VenueCategory {

        private int id;
        private String space_category_name;

        public int getId() {
            return id;
        }

        public String getSpace_category_name() {
            return space_category_name;
        }
    }

    public static class Venues {

        private int id;
        private String space_activity;
        private String space_profile;
        private String space_location;
        private String space_description;
        private String space_ft;
        private String working_hours;
        private String cancellation_policy;
        private String space_category_name;

        public int getId() {
            return id;
        }

        public String getSpace_activity() {
            return space_activity;
        }

        public String getSpace_profile() {
            return space_profile;
        }

        public String getSpace_location() {
            return space_location;
        }

        public String getSpace_description() {
            return space_description;
        }

        public String getSpace_ft() {
            return space_ft;
        }

        public String getWorking_hours() {
            return working_hours;
        }

        public String getCancellation_policy() {
            return cancellation_policy;
        }

        public String getSpace_category_name() {
            return space_category_name;
        }
    }
}
