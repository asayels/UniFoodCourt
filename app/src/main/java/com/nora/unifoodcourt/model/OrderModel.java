package com.nora.unifoodcourt.model;


import com.nora.unifoodcourt.managers.LocalData;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    private int mOrderID;
    private String mStudentID;
    private RestaurantModel mRestaurantModel;
    private List<FoodModel> mFoodModels = new ArrayList<>();
    private boolean orderReceived;

    public int getOrderID() {
        return mOrderID;
    }

    public void setOrderID(int orderID) {
        mOrderID = orderID;
    }

    public String getStudentID() {
        return mStudentID;
    }

    public void setStudentID(String studentID) {
        mStudentID = studentID;
    }

    public List<FoodModel> getFoodModels() {
        return mFoodModels;
    }

    public RestaurantModel getRestaurantModel() {
        return mRestaurantModel;
    }

    public void setRestaurantModel(int restaurantID) {
        mRestaurantModel = LocalData.getRestaurantByID(restaurantID);
    }

    public void addFood(int restaurantID, int foodID, int quantity) {
        FoodModel foodModel = LocalData.getFoodByID(restaurantID, foodID);
        foodModel.setQuantity(quantity);
        mFoodModels.add(foodModel);
    }

    public double getTotalPrice() {
        return mFoodModels.stream().mapToDouble(m -> m.getQuantity() * m.getPrice()).sum();
    }


    public boolean isOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(boolean orderReceived) {
        this.orderReceived = orderReceived;
    }
}
