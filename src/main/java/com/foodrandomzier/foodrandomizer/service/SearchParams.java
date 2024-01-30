package com.foodrandomzier.foodrandomizer.service;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PriceLevel;
public class SearchParams {
    public LatLng location;
    public int radius;
    public String keyword;
    public PriceLevel maxPrice;
    public PlaceType type;
    public String pageToken;

    public SearchParams(LatLng location, int radius, String keyword, PriceLevel maxPrice , PlaceType type) {
        this.location = location;
        this.radius = radius;
        this.keyword = keyword;
        this.maxPrice = maxPrice;

        this.type = type;
    }

}