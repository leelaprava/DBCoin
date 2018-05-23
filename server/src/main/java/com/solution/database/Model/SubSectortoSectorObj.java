package com.solution.database.Model;

public class SubSectortoSectorObj {
    private int nc;
    private float fraction;
    private String  subSector;

    public SubSectortoSectorObj() {

    }

    public SubSectortoSectorObj(int nc, float fraction, String subSector) {
        this.nc = nc;
        this.fraction = fraction;
        this.subSector = subSector;
    }

    @Override
    public String toString() {
        return "SubSectortoSectorObj{" +
                "nc=" + nc +
                ", fraction=" + fraction +
                ", subSector='" + subSector + '\'' +
                '}';
    }

    public int getNc() {
        return nc;
    }

    public float getFraction() {
        return fraction;
    }

    public String getSubSector() {
        return subSector;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
    }

    public void setSubSector(String subSector) {
        this.subSector = subSector;
    }
}
