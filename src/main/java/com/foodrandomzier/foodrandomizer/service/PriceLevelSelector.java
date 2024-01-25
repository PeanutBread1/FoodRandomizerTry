package com.foodrandomzier.foodrandomizer.service;

import com.google.maps.model.PriceLevel;
import org.springframework.stereotype.Service;

@Service
public class PriceLevelSelector {
    public PriceLevel choosePriceLevel(int priceLevelCode) {
        // Convert the priceLevelCode to the corresponding PriceLevel enum
        // and return the selected PriceLevel.
        switch (priceLevelCode) {
            case 0:
                return PriceLevel.FREE;
            case 1:
                return PriceLevel.INEXPENSIVE;
            case 2:
                return PriceLevel.MODERATE;
            case 3:
                return PriceLevel.EXPENSIVE;
            case 4:
                return PriceLevel.VERY_EXPENSIVE;
            default:
                // If an invalid code is provided, return null or a default PriceLevel.
                return null;
        }
    }


}