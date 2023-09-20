package com.foodrandomzier.foodrandomizer.service;

import com.foodrandomzier.foodrandomizer.model.Restaurant;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {
    public Restaurant saveRestaurant(Restaurant retaurant);
}
