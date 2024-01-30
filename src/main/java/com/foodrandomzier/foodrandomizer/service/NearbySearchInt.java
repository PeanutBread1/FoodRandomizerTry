package com.foodrandomzier.foodrandomizer.service;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;

import java.io.IOException;
import java.util.List;

public interface NearbySearchInt {
    SearchParams prepareSearchParameters(LatLng latlng, int radius, int maxprice, String keyword);
    List<PlacesSearchResult> getAllResults(GeoApiContext context, SearchParams params) throws InterruptedException, IOException, ApiException;
    PlaceDetails randomize(List<PlaceDetails> results, int rating);
    List<PlaceDetails> fetchPlaceDetails(List<PlacesSearchResult> searchResults, GeoApiContext context);
    List<PlaceDetails> filterByPriceLevel(List<PlaceDetails> results, int minprice);
    NearbySearchRequest createSearchRequest(GeoApiContext context, SearchParams params);


    }

