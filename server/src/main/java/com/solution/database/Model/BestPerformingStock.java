package com.solution.database.Model;

public class BestPerformingStock {
    private String name;
    private float startprice;
    private float endprice;
    private float roi;

    public BestPerformingStock() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getStartprice() {
        return startprice;
    }

    public void setStartprice(float startprice) {
        this.startprice = startprice;
    }

    public float getEndprice() {
        return endprice;
    }

    public void setEndprice(float endprice) {
        this.endprice = endprice;
    }

    public float getRoi() {
        return roi;
    }

    public void setRoi(float roi) {
        this.roi = roi;
    }

    @Override
    public String toString() {
        return "BestPerformingStock{" +
                "name='" + name + '\'' +
                ", startprice=" + startprice +
                ", endprice=" + endprice +
                ", roi=" + roi +
                '}';
    }
}
