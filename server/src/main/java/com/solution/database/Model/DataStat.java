package com.solution.database.Model;

public class DataStat {
    private String name;
    private int tuples;

    public DataStat() {
    }

    public DataStat(String name, int tuples) {
        this.name = name;
        this.tuples = tuples;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTuples() {
        return tuples;
    }

    public void setTuples(int tuples) {
        this.tuples = tuples;
    }

    @Override
    public String toString() {
        return "DataStat{" +
                "name='" + name + '\'' +
                ", tuples=" + tuples +
                '}';
    }
}
