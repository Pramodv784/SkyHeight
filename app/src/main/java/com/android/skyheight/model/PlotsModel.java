package com.android.skyheight.model;

public class PlotsModel {
    public String no_plots;
    public String id;

    public PlotsModel(String no_plots, String id) {
        this.no_plots = no_plots;
        this.id = id;
    }

    public String getNo_plots() {
        return no_plots;
    }

    public void setNo_plots(String no_plots) {
        this.no_plots = no_plots;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
