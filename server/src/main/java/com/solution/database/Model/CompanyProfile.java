package com.solution.database.Model;

public class CompanyProfile {
    private String name;
    private String ticketSymbol;
    private float lastPrice;
    private float change;
    private float priceChange;
    private int volTraded;
    private float weekHigh;
    private String highDate;
    private float weekLow;
    private String lowDate;
    private float gProfit;
    private float netIncome;
    private float totAsset;
    private float totLiabilities;
    private float cashRatio;
    private float goodwill;
    private float totRevenue;
    private float eps;

    public CompanyProfile() {
    }

    public CompanyProfile(String name, String ticketSymbol, float lastPrice, float change, float priceChange, int volTraded, float weekHigh, String highDate, float weekLow, String lowDate, float gProfit, float netIncome, float totAsset, float totLiabilities, float cashRatio, float goodwill, float totRevenue, float eps) {
        this.name = name;
        this.ticketSymbol = ticketSymbol;
        this.lastPrice = lastPrice;
        this.change = change;
        this.priceChange = priceChange;
        this.volTraded = volTraded;
        this.weekHigh = weekHigh;
        this.highDate = highDate;
        this.weekLow = weekLow;
        this.lowDate = lowDate;
        this.gProfit = gProfit;
        this.netIncome = netIncome;
        this.totAsset = totAsset;
        this.totLiabilities = totLiabilities;
        this.cashRatio = cashRatio;
        this.goodwill = goodwill;
        this.totRevenue = totRevenue;
        this.eps = eps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketSymbol() {
        return ticketSymbol;
    }

    public void setTicketSymbol(String ticketSymbol) {
        this.ticketSymbol = ticketSymbol;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public float getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(float priceChange) {
        this.priceChange = priceChange;
    }

    public int getVolTraded() {
        return volTraded;
    }

    public void setVolTraded(int volTraded) {
        this.volTraded = volTraded;
    }

    public float getWeekHigh() {
        return weekHigh;
    }

    public void setWeekHigh(float weekHigh) {
        this.weekHigh = weekHigh;
    }

    public String getHighDate() {
        return highDate;
    }

    public void setHighDate(String highDate) {
        this.highDate = highDate;
    }

    public float getWeekLow() {
        return weekLow;
    }

    public void setWeekLow(float weekLow) {
        this.weekLow = weekLow;
    }

    public String getLowDate() {
        return lowDate;
    }

    public void setLowDate(String lowDate) {
        this.lowDate = lowDate;
    }

    public float getgProfit() {
        return gProfit;
    }

    public void setgProfit(float gProfit) {
        this.gProfit = gProfit;
    }

    public float getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(float netIncome) {
        this.netIncome = netIncome;
    }

    public float getTotAsset() {
        return totAsset;
    }

    public void setTotAsset(float totAsset) {
        this.totAsset = totAsset;
    }

    public float getTotLiabilities() {
        return totLiabilities;
    }

    public void setTotLiabilities(float totLiabilities) {
        this.totLiabilities = totLiabilities;
    }

    public float getCashRatio() {
        return cashRatio;
    }

    public void setCashRatio(float cashRatio) {
        this.cashRatio = cashRatio;
    }

    public float getGoodwill() {
        return goodwill;
    }

    public void setGoodwill(float goodwill) {
        this.goodwill = goodwill;
    }

    public float getTotRevenue() {
        return totRevenue;
    }

    public void setTotRevenue(float totRevenue) {
        this.totRevenue = totRevenue;
    }

    public float getEps() {
        return eps;
    }

    public void setEps(float eps) {
        this.eps = eps;
    }

    @Override
    public String toString() {
        return "CompanyProfile{" +
                "name='" + name + '\'' +
                ", ticketSymbol='" + ticketSymbol + '\'' +
                ", lastPrice=" + lastPrice +
                ", change=" + change +
                ", priceChange=" + priceChange +
                ", volTraded=" + volTraded +
                ", weekHigh=" + weekHigh +
                ", highDate='" + highDate + '\'' +
                ", weekLow=" + weekLow +
                ", lowDate='" + lowDate + '\'' +
                ", gProfit=" + gProfit +
                ", netIncome=" + netIncome +
                ", totAsset=" + totAsset +
                ", totLiabilities=" + totLiabilities +
                ", cashRatio=" + cashRatio +
                ", goodwill=" + goodwill +
                ", totRevenue=" + totRevenue +
                ", eps=" + eps +
                '}';
    }
}
