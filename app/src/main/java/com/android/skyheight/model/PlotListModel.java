package com.android.skyheight.model;

public class PlotListModel {

    private String plot_number;
    private String description;
    private String size;
    private String status;

    public PlotListModel(String plot_number, String description, String size, String status) {
        this.plot_number = plot_number;
        this.description = description;
        this.size = size;
        this.status = status;
    }

    public String getPlot_number() {
        return plot_number;
    }

    public void setPlot_number(String plot_number) {
        this.plot_number = plot_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
