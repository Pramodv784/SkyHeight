package com.android.skyheight.model;

public class FavItem {
    private int price;
    private String plot_image;
    private String key_id;

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }
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

    private String site_address;
    private String plot_size;
    private String plot_owner;

    public FavItem(String key_id,String plot_image,int price, String site_address, String plot_size, String plot_owner) {
        this.price = price;
        this.site_address = site_address;
        this.plot_size = plot_size;
        this.plot_owner = plot_owner;
        this.plot_image =plot_image;
        this.key_id=key_id;
    }

}
