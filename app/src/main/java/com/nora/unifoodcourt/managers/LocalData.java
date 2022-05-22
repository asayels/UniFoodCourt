package com.nora.unifoodcourt.managers;


import com.nora.unifoodcourt.R;
import com.nora.unifoodcourt.model.FoodModel;
import com.nora.unifoodcourt.model.RestaurantModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalData {

    public static RestaurantModel getRestaurantByID(int restaurantID) {
        return restaurants.stream().filter(r -> r.getID() == restaurantID).findAny().get();
    }

    public static FoodModel getFoodByID(int restaurantID, int foodID) {
        return food.stream().filter(r -> r.getRestaurantID() == restaurantID && r.getID() == foodID).findAny().get();
    }

    public static List<FoodModel> getFoodListByID(int restaurantID) {
        return food.stream().filter(r -> r.getRestaurantID() == restaurantID).collect(Collectors.toList());
    }

    public static List<RestaurantModel> restaurants = Arrays.asList(
            new RestaurantModel(1, "Alsaaj Alreefi", R.drawable._1_alsaaj_alreefi),
            new RestaurantModel(2, "Subway", R.drawable._2_subway_logo),
            new RestaurantModel(3, "Baskin Robbins", R.drawable._3_baskin_robbins),
            new RestaurantModel(4, "Starbucks", R.drawable._4_starbucks_logo),
            new RestaurantModel(5, "Sugar Sprinkles", R.drawable._5_sugar_sprinkles)
    );

    public static List<FoodModel> food = Arrays.asList(
            new FoodModel(1, 1, "Caesar Salad", 15, R.drawable._1_caesar_salad),
            new FoodModel(2, 1, "Cheese Mankeesh", 10, R.drawable._1_cheese_mankeesh),
            new FoodModel(3, 1, "Fries", 8, R.drawable._1_fries),
            new FoodModel(4, 1, "Margherita Pizza", 20, R.drawable._1_margherita_pizza),
            new FoodModel(5, 1, "Zataar Mankeesh", 10, R.drawable._1_zataar_mankeesh),

            new FoodModel(1, 2, "Chicken Salad", 15, R.drawable._2_chicken_salad),
            new FoodModel(2, 2, "Chicken Fajita", 16, R.drawable._2_chicken_fajita),
            new FoodModel(3, 2, "Chicken Teriyaki", 16, R.drawable._2_chicken_teriyaki),
            new FoodModel(4, 2, "Italian Bmt", 16, R.drawable._2_italian_bmt),
            new FoodModel(5, 2, "Turkey Breast", 16, R.drawable._2_turkey_breast),

            new FoodModel(1, 3, "Chocolate", 15, R.drawable._3_chocolate),
            new FoodModel(2, 3, "Cotton Candy", 15, R.drawable._3_cotton_candy),
            new FoodModel(3, 3, "Jamoca Almond Fudge", 15, R.drawable._3_jamoca_almond_fudge),
            new FoodModel(4, 3, "Pralines N cream", 15, R.drawable._3_pralines_n_cream),
            new FoodModel(5, 3, "Vanilla", 15, R.drawable._3_vanilla),

            new FoodModel(1, 4, "Black Coffee", 14, R.drawable._4_black_coffee),
            new FoodModel(2, 4, "Iced Americano", 16, R.drawable._4_iced_americano),
            new FoodModel(3, 4, "Iced Latte", 18, R.drawable._4_iced_latte),
            new FoodModel(4, 4, "White Mocha", 21, R.drawable._4_white_mocha),
            new FoodModel(5, 4, "Iced White Mocha", 21, R.drawable._4_iced_white_mocha),

            new FoodModel(1, 5, "Carrot", 10, R.drawable._5_carrot),
            new FoodModel(2, 5, "Double_chocolate", 10, R.drawable._5_double_chocolate),
            new FoodModel(3, 5, "Oreo_cupcake", 10, R.drawable._5_oreo_cupcake),
            new FoodModel(4, 5, "Red_velvet", 10, R.drawable._5_red_velvet),
            new FoodModel(5, 5, "Tiramisu", 10, R.drawable._5_tiramisu)
    );


}
