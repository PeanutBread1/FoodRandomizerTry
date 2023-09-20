package com.foodrandomzier.foodrandomizer.service;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NearbySearch {
    public static class SearchParams {
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

    public static SearchParams prepareSearchParameters(LatLng latlng, int radius , int maxprice, String keyword) {
        PriceLevelSelector priceLevelSelector = new PriceLevelSelector();
        PriceLevel maxPriceLevel = priceLevelSelector.choosePriceLevel(maxprice);

        return new SearchParams(latlng, radius, keyword, maxPriceLevel, PlaceType.RESTAURANT);
    }



    public static List<PlacesSearchResult> getAllResults(GeoApiContext context, SearchParams params) throws InterruptedException, IOException, ApiException {
        List<PlacesSearchResult> allResults = new ArrayList<>();

        PlacesSearchResponse response = createSearchRequest(context, params).await();

        if (response.results != null) {
            allResults.addAll(Arrays.asList(response.results));
        }

        while (response.nextPageToken != null && !response.nextPageToken.isEmpty()) {
            Thread.sleep(2000);

            params.pageToken = response.nextPageToken;
            response = createSearchRequest(context, params).await();

            if (response.results != null) {
                allResults.addAll(Arrays.asList(response.results));
            }
        }

        return allResults;
    }
    public static PlaceDetails randomize(List<PlaceDetails> results, int rating){
        if(results.isEmpty()){
            return null;
        }
        results.removeIf(result -> result.rating < rating);
        Random random = new Random();
        PlaceDetails randomResult = results.get(random.nextInt(results.size()));
        return randomResult;
    }

    public static List<PlaceDetails> fetchPlaceDetails(List<PlacesSearchResult> searchResults, GeoApiContext context) {
        List<PlaceDetails> placeDetailsList = new ArrayList<>();

        for (PlacesSearchResult searchResult : searchResults) {
            try {
                PlaceDetailsRequest request = PlacesApi.placeDetails(context, searchResult.placeId);

                request.fields(Arrays.stream(PlaceDetailsRequest.FieldMask.values())
                        .filter(x -> x != PlaceDetailsRequest.FieldMask.SECONDARY_OPENING_HOURS)
                        .toArray(PlaceDetailsRequest.FieldMask[]::new));

                PlaceDetails placeDetails = request.await();
                placeDetailsList.add(placeDetails);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception, if needed
            }

//            return null;
        }

        return placeDetailsList;
    }

    public static List<PlaceDetails> filterByPriceLevel(List<PlaceDetails> results, int minprice) {
        List<PlaceDetails> filteredResults = new ArrayList<>();
        PriceLevelSelector priceLevelSelector = new PriceLevelSelector();
        PriceLevel minpricelevel = priceLevelSelector.choosePriceLevel(minprice);

        for (PlaceDetails result : results) {
                if (result.priceLevel != null && result.priceLevel.compareTo(minpricelevel) >= 0) {
                filteredResults.add(result);
            }
        }

        return filteredResults;
    }

    public static NearbySearchRequest createSearchRequest(GeoApiContext context, SearchParams params) {
        NearbySearchRequest search = PlacesApi.nearbySearchQuery(context, params.location);
        search.radius(params.radius);
        if (params.keyword != null) {
            search.keyword(params.keyword);
        }
        search.maxPrice(params.maxPrice);

        search.type(params.type);
        if (params.pageToken != null) {
            search.pageToken(params.pageToken);
        }
        return search;
    }

}