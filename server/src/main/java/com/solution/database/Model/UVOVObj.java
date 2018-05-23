package com.solution.database.Model;

public class UVOVObj {
    private String name;
    private float vRatio;
    private int year;

    public UVOVObj() {
    }

    public UVOVObj(String name, float vRatio, int year) {
        this.name = name;
        this.vRatio = vRatio;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getvRatio() {
        return vRatio;
    }

    public void setvRatio(float vRatio) {
        this.vRatio = vRatio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "UVOVObj{" +
                "name='" + name + '\'' +
                ", vRatio=" + vRatio +
                ", year=" + year +
                '}';
    }
}
