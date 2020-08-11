package com.android.skyheight.model;

public class SiteGridList {

    private String price;
    private String location;
    private String image;
    private String status;
    private boolean isbooked;




    public boolean getIsbooked() {
        return isbooked;
    }

    public void setIsbooked(boolean isbooked) {
        this.isbooked = isbooked;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SiteGridList(boolean isbooked,String price, String location, String image,String status) {
        this.isbooked = isbooked;
        this.price = price;
        this.location = location;
        this.image = image;
        this.status=status;

    }



}
