package com.solution.database.Model;


public class Company {


    private String tickerSymbol;
    private String name;
    private String sector;
    private String subsector;
    private String address;
    private String dateAdded;
    private int cik;

    public Company(){}
    public Company(String tickerSymbol, String name, String sector, String subsector, String address, String dateAdded, int cik) {
        this.tickerSymbol = tickerSymbol;
        this.name = name;
        this.sector = sector;
        this.subsector = subsector;
        this.address = address;
        this.dateAdded = dateAdded;
        this.cik = cik;
    }

    @Override
    public String toString() {
        return "Company{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", subsector='" + subsector + '\'' +
                ", address='" + address + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", cik=" + cik +
                '}';
    }



    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSubsector() {
        return subsector;
    }

    public void setSubsector(String subsector) {
        this.subsector = subsector;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getCik() {
        return cik;
    }

    public void setCik(int cik) {
        this.cik = cik;
    }
}
