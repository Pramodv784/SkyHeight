package com.android.skyheight.model;

public class SiteModel {
    String site_name;
    String site_area;
    String site_location;
    String site_description;
    String site_image;
    String site_file;
    public SiteModel(String site_name, String site_area, String site_location, String site_description, String site_image, String site_file) {
        this.site_name = site_name;
        this.site_area = site_area;
        this.site_location = site_location;
        this.site_description = site_description;
        this.site_image = site_image;
        this.site_file = site_file;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSite_area() {
        return site_area;
    }

    public void setSite_area(String site_area) {
        this.site_area = site_area;
    }

    public String getSite_location() {
        return site_location;
    }

    public void setSite_location(String site_location) {
        this.site_location = site_location;
    }

    public String getSite_description() {
        return site_description;
    }

    public void setSite_description(String site_description) {
        this.site_description = site_description;
    }

    public String getSite_image() {
        return site_image;
    }

    public void setSite_image(String site_image) {
        this.site_image = site_image;
    }

    public String getSite_file() {
        return site_file;
    }

    public void setSite_file(String site_file) {
        this.site_file = site_file;
    }
}
