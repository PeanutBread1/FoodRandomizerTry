    package com.foodrandomzier.foodrandomizer.controller;

    import com.foodrandomzier.foodrandomizer.model.Restaurant;
    import com.foodrandomzier.foodrandomizer.service.Convert;
    import com.foodrandomzier.foodrandomizer.service.NearbySearch;
    import com.foodrandomzier.foodrandomizer.service.RestaurantService;
    import com.foodrandomzier.foodrandomizer.service.geocodespring;
    import com.google.maps.GeoApiContext;
    import com.google.maps.errors.ApiException;
    import com.google.maps.model.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.io.IOException;
    import java.util.List;
    import java.util.Random;

    @RestController
    @RequestMapping("/nearbysearch")
    public class FoodChoiceController {

        private static final String PLACES_API_KEY = "AIzaSyB0JeyNXsZWbmZ_wZ46A2KqJXdCgVNrJJM";

        @GetMapping
        public ResponseEntity<?> nearbySearch(
                @RequestParam("location") String city,
                @RequestParam("radius") int radius,
                @RequestParam("rating") int rating,
                @RequestParam("maxprice") int maxprice,
                @RequestParam("minprice") int minprice,
                @RequestParam(value = "keyword", required = false) String keyword) {

            LatLng latlng = geocodespring.geoCode(city);

            if (latlng == null) {
                return ResponseEntity.badRequest().body("Invalid location");
            }

            GeoApiContext context = new GeoApiContext.Builder().apiKey(PLACES_API_KEY).build();

            try {
                List<PlacesSearchResult> allResults = NearbySearch.getAllResults(context, NearbySearch.prepareSearchParameters(latlng, radius, maxprice, keyword)
                );

                if (allResults.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }

                // Print details (for debugging purposes, you could remove this in production)
                /*allResults.forEach(result -> {
                    System.out.println("Name: " + result.name);
                    System.out.println("Rating: " + result.rating);
                    System.out.println("Total User Ratings: " + result.userRatingsTotal);
                    System.out.println("-----------------------");
                });*/
                List<PlaceDetails> convert = NearbySearch.fetchPlaceDetails(allResults, context);
                List<PlaceDetails> filter = NearbySearch.filterByPriceLevel(convert, minprice);
                // Get a random result from the filtered results
                PlaceDetails randomResult = NearbySearch.randomize(filter, rating);
                System.out.println(randomResult);
                Restaurant rest = Convert.mapToRestaurant(randomResult);
                return ResponseEntity.ok(rest);

            } catch (ApiException | InterruptedException | IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }

        }
        @Autowired
        private RestaurantService restaurantService;
        @PostMapping("/add")
        public String addRestaurant(@RequestBody Restaurant restaurant){
            restaurantService.saveRestaurant(restaurant);
            return "done";
        }


    }