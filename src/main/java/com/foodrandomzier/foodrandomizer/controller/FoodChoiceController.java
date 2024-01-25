    package com.foodrandomzier.foodrandomizer.controller;

    import com.foodrandomzier.foodrandomizer.model.Restaurant;
    import com.foodrandomzier.foodrandomizer.service.Convert;
    import com.foodrandomzier.foodrandomizer.service.NearbySearch;
    import com.foodrandomzier.foodrandomizer.service.NearbySearchInt;
    import com.foodrandomzier.foodrandomizer.service.geocodespring;
    import com.google.maps.GeoApiContext;
    import com.google.maps.errors.ApiException;
    import com.google.maps.model.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import java.io.IOException;
    import java.util.List;

    @RestController
    @RequestMapping("/nearbysearch")
    public class FoodChoiceController {
        private NearbySearchInt nearbySearchInt;
        private geocodespring geocode;
        private Convert meow;
        private Restaurant restaur;

        @Autowired
        public FoodChoiceController(geocodespring geocode, NearbySearchInt nearbySearchInt, Convert meow ) {
            this.geocode = geocode;
            this.nearbySearchInt = nearbySearchInt;
            this.meow = meow;
        }

        private static final String PLACES_API_KEY = "AIzaSyB0JeyNXsZWbmZ_wZ46A2KqJXdCgVNrJJM";


        @GetMapping
        public ResponseEntity<?> nearbySearch(
                @RequestParam("location") String city,
                @RequestParam("radius") int radius,
                @RequestParam("rating") int rating,
                @RequestParam("maxprice") int maxprice,
                @RequestParam("minprice") int minprice,
                @RequestParam(value = "keyword", required = false) String keyword) {

            LatLng latlng = geocode.geoCode(city);

            if (latlng == null) {
                return ResponseEntity.badRequest().body("Invalid location");
            }

            GeoApiContext context = new GeoApiContext.Builder().apiKey(PLACES_API_KEY).build();

            try {
                List<PlacesSearchResult> allResults = nearbySearchInt.getAllResults(context, nearbySearchInt.prepareSearchParameters(latlng, radius, maxprice, keyword)
                );

                if (allResults.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }

                List<PlaceDetails> convert = nearbySearchInt.fetchPlaceDetails(allResults, context);
                List<PlaceDetails> filter = nearbySearchInt.filterByPriceLevel(convert, minprice);
                PlaceDetails randomResult = nearbySearchInt.randomize(filter, rating);
                System.out.println(randomResult);
                Restaurant rest = meow.mapToRestaurant(randomResult);
                return ResponseEntity.ok(rest);

            } catch (ApiException | InterruptedException | IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }

        }


    }