package com.foodrandomzier.foodrandomizer.Repository;

import com.foodrandomzier.foodrandomizer.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}
