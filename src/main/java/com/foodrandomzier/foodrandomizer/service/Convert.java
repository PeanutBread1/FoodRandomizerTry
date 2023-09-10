package com.foodrandomzier.foodrandomizer.service;

import com.foodrandomzier.foodrandomizer.model.Restaurant;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PriceLevel;

public class Convert {
    public static Restaurant mapToRestaurant(PlaceDetails placeDetails) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(placeDetails.name);
        restaurant.setRating(placeDetails.rating);
        restaurant.setAddress(placeDetails.formattedAddress);
        restaurant.setPlaceid(placeDetails.placeId);
        // Set other fields as needed
        return restaurant;
    }
}