    package com.foodrandomzier.foodrandomizer.controller;

    import com.foodrandomzier.foodrandomizer.model.Restaurant;
    import com.foodrandomzier.foodrandomizer.service.*;
    import com.google.maps.GeoApiContext;
    import com.google.maps.errors.ApiException;
    import com.google.maps.model.*;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import java.io.IOException;
    import java.util.List;

    @RestController
    @RequestMapping("/nearbysearch")
    public class FoodChoiceController {
        private RestaurantService restaurantService;
        private NearbySearchInt nearbySearchInt;
        private geocodespring geocode;
        private Convert meow;
        private Restaurant restaur;

        @Autowired
        public FoodChoiceController(geocodespring geocode, NearbySearchInt nearbySearchInt, Convert meow , RestaurantService restaurantService) {
            this.geocode = geocode;
            this.nearbySearchInt = nearbySearchInt;
            this.meow = meow;
            this.restaurantService= restaurantService;
        }
        private static final String PLACES_API_KEY = "AIzaSyB0JeyNXsZWbmZ_wZ46A2KqJXdCgVNrJJM";
        @GetMapping
        public ResponseEntity<?> nearbySearch(HttpSession session,
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
                session.setAttribute("selectedRestaurant", rest);

                return ResponseEntity.ok(rest);

            } catch (ApiException | InterruptedException | IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }

        }
        @PostMapping
        public ResponseEntity<Restaurant> saveRestaurant(HttpSession session){
            Restaurant restaurant = (Restaurant) session.getAttribute("selectedRestaurant");
            if (restaurant != null) {
                restaurantService.saveRestaurant(restaurant);
                session.removeAttribute("selectedRestaurant");
                return ResponseEntity.ok.build;
            }else {
                return ResponseEntity.badRequest().body(null);
            }
        }



    }