package com.nora.unifoodcourt.model;

public class FoodModel {

    private int mID;
    private int mRestaurantID;
    private String mName;
    private int mPrice;
    private int mPic;
    private int mQuantity;


    public FoodModel(int ID, int restaurantID, String name, int price, int pic) {
        mID = ID;
        mRestaurantID = restaurantID;
        mName = name;
        mPrice = price;
        mPic = pic;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public int getRestaurantID() {
        return mRestaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        mRestaurantID = restaurantID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public int getPic() {
        return mPic;
    }

    public void setPic(int pic) {
        mPic = pic;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }
}
