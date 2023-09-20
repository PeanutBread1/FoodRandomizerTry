    package com.foodrandomzier.foodrandomizer.model;

    import com.google.maps.model.PriceLevel;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    @Entity
    public class Restaurant {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private String name;
        private String address;
        private double rating;
        private PriceLevel priceLevel;

        private int priceLevelNum;
        private String placeid;
        private String photo;

        // Constructors

        public Restaurant() {
        }

        public Restaurant(Long id, String name, String address, double rating, PriceLevel priceLevel, String placeid, String photo) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.rating = rating;
            this.priceLevel = priceLevel;
            this.placeid = placeid;
            this.photo = photo;
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

        public String getPlaceid() {
            return placeid;
        }

        public void setPlaceid(String placeid) {
            this.placeid = placeid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        // Other methods if needed
    }

