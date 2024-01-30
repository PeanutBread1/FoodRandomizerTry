package com.foodrandomzier.foodrandomizer.model;

import com.google.maps.model.PriceLevel;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@Entity
public class Restaurant {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        private Long id;
        @Column(name = "restaurantname")
        private String name;
        @Column(name = "address")
        private String address;
        @Column(name = "rating")
        private double rating;
        @Column(name = "pricelevel")
        private PriceLevel priceLevel;
        @Column(name = "pricelevelnum")
        private int priceLevelNum;

        // Constructors

        public Restaurant() {
        }

        public Restaurant(Long id, String name, String address, double rating, PriceLevel priceLevel) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.rating = rating;
            this.priceLevel = priceLevel;

        }

        // Getters and Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public PriceLevel getPriceLevel() {
            return priceLevel;
        }
        public int getPriceLevelNum(){
            return priceLevelNum;
        }

        public void setPriceLevelNum (int priceLevelNum){this.priceLevelNum = priceLevelNum;}

        public void setPriceLevel(PriceLevel priceLevel) {
            this.priceLevel = priceLevel;
        }





        // Other methods if needed
    }

