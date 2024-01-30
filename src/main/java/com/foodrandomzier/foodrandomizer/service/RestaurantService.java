package com.foodrandomzier.foodrandomizer.service;

import com.foodrandomzier.foodrandomizer.model.Restaurant;
import com.foodrandomzier.foodrandomizer.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService  {
    private final RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    public Restaurant saveRestaurant(Restaurant restaurant) {
        // Business logic (if any) goes here
        return restaurantRepository.save(restaurant); // Use the save method provided by JpaRepository
    }
}
