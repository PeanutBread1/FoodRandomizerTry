package com.foodrandomzier.foodrandomizer.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

@Service
public class geocodespring {
    private String apiKey = "";
    private String placesAPI = "";
    public LatLng geoCode(String input) {
        GeoApiContext context2 = new GeoApiContext.Builder().apiKey(apiKey).build();
        GeocodingApiRequest geocodeRequest = GeocodingApi.geocode(context2, input);
        GeocodingResult geocodeResult[] = geocodeRequest.awaitIgnoreError();
        if (geocodeResult != null && geocodeResult.length > 0) {
            double latitude = geocodeResult[0].geometry.location.lat;
            double longitude = geocodeResult[0].geometry.location.lng;
            return new LatLng(latitude, longitude);
        }
        else{
            return null;
        }

    }
    public String geoCodeId(String input) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(placesAPI).build();
        GeoApiContext context2 = new GeoApiContext.Builder().apiKey(apiKey).build();
        GeocodingApiRequest geocodeRequest = GeocodingApi.geocode(context2, input);
        GeocodingResult geocodeResult[] = geocodeRequest.awaitIgnoreError();
        if (geocodeResult != null && geocodeResult.length > 0) {

            return geocodeResult[0].placeId;
        } else {
            return null;
        }
    }
}
