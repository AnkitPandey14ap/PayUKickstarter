package com.example.krishna.payukickstarter.DataClasses;

/**
 * Created by krishna on 12/8/17.
 */


public class ProjectDetails {

    private String sno;
    private String amtPledged;
    private String blurb;
    private String by;
    private String country;
    private String currency;
    private String endTime;
    private String location;
    private String pecentageFunded;
    private String numBackers;
    private String state;
    private String title;
    private String type;
    private String url;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getAmtPledged() {
        return amtPledged;
    }

    public void setAmtPledged(String amtPledged) {
        this.amtPledged = amtPledged;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPecentageFunded() {
        return pecentageFunded;
    }

    public void setPecentageFunded(String pecentageFunded) {
        this.pecentageFunded = pecentageFunded;
    }

    public String getNumBackers() {
        return numBackers;
    }

    public void setNumBackers(String numBackers) {
        this.numBackers = numBackers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProjectDetails(String sno, String amtPledged, String blurb, String by, String country, String currency, String endTime, String location, String pecentageFunded, String numBackers, String state, String title, String type, String url) {
        this.sno = sno;
        this.amtPledged = amtPledged;
        this.blurb = blurb;
        this.by = by;
        this.country = country;
        this.currency = currency;
        this.endTime = endTime;
        this.location = location;
        this.pecentageFunded = pecentageFunded;
        this.numBackers = numBackers;
        this.state = state;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public ProjectDetails() {

    }
}
