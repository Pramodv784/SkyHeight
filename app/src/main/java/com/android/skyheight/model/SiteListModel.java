package com.android.skyheight.model;

import java.io.Serializable;

public class SiteListModel implements Serializable {
private int price;
private String plot_image;
private String key_id;
private String fav_status;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getPlot_image() {
        return plot_image;
    }

    public void setPlot_image(String plot_image) {
        this.plot_image = plot_image;
    }

    public String getSite_address() {
        return site_address;
    }

    public void setSite_address(String site_address) {
        this.site_address = site_address;
    }

    public String getPlot_size() {
        return plot_size;
    }

    public void setPlot_size(String plot_size) {
        this.plot_size = plot_size;
    }

    public String getPlot_owner() {
        return plot_owner;
    }

    public void setPlot_owner(String plot_owner) {
        this.plot_owner = plot_owner;
    }
    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }
    public String getFav_status() {
        return getFav_status();
    }

    public void setFav_status(String fav_status) {
        this.plot_owner = fav_status;
    }
    private String site_address;
private String plot_size;
private String plot_owner;

    public SiteListModel(String plot_image,int price, String site_address, String plot_size, String plot_owner,String fav_status) {
        this.price = price;
        this.site_address = site_address;
        this.plot_size = plot_size;
        this.plot_owner = plot_owner;
        this.plot_image =plot_image;
        this.fav_status=fav_status;
    }
}
