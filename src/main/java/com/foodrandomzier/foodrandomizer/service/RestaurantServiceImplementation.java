    package com.foodrandomzier.foodrandomizer.service;

    import com.foodrandomzier.foodrandomizer.Repository.RestaurantRepository;
    import com.foodrandomzier.foodrandomizer.model.Restaurant;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class RestaurantServiceImplementation implements RestaurantService{
        @Autowired
        private RestaurantRepository restaurantRepository;

        @Override
        public Restaurant saveRestaurant(Restaurant restaurant){
            return restaurantRepository.save(restaurant);
        }
    }
