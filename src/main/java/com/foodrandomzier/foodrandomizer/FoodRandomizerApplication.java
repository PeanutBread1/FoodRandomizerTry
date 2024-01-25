package com.foodrandomzier.foodrandomizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})

public class FoodRandomizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodRandomizerApplication.class, args)
        ;
    }

}
