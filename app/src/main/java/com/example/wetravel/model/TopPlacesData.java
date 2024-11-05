package com.example.wetravel.model;

public class TopPlacesData {


    String placeName;
    String countryName;
    String price;

    Integer imageURL;

    public TopPlacesData(String placeName, String countryName, String price, Integer imageURL) {
        this.placeName = placeName;
        this.countryName = countryName;
        this.price = price;
        this.imageURL = imageURL;
    }

    public Integer getImageURL() {
        return imageURL;
    }

    public void setImageURL(Integer imageURL) {
        this.imageURL = imageURL;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
