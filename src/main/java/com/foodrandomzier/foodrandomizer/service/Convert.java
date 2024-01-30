package com.foodrandomzier.foodrandomizer.service;

import com.foodrandomzier.foodrandomizer.model.Restaurant;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PriceLevel;
import org.springframework.stereotype.Service;

@Service
public class Convert {
    public Restaurant mapToRestaurant(PlaceDetails placeDetails) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(placeDetails.name);
        restaurant.setRating(placeDetails.rating);
        restaurant.setAddress(placeDetails.formattedAddress);
        restaurant.setPriceLevel(placeDetails.priceLevel);
        restaurant.setPriceLevelNum(placeDetails.priceLevel.ordinal());
        // Set other fields as needed
        return restaurant;
    }
}
