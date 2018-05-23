package com.solution.database.Model;

public class ROIObj {
    private String company;
    private float startprice;
    private float endprice;
    private float roi;

    public ROIObj() {
    }

    public ROIObj(String company, float startprice, float endprice, float roi) {
        this.company = company;
        this.startprice = startprice;
        this.endprice = endprice;
        this.roi = roi;
    }

    @Override
    public String toString() {
        return "ROIObj{" +
                "company='" + company + '\'' +
                ", startprice=" + startprice +
                ", endprice=" + endprice +
                ", roi=" + roi +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
}
