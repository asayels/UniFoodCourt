package com.nora.unifoodcourt.model;

import java.util.List;

public class RestaurantModel {

    private int mID;
    private String mName;
    private int mPic;

    public RestaurantModel(int ID, String name, int pic) {
        mID = ID;
        mName = name;
        mPic = pic;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPic() {
        return mPic;
    }

    public void setPic(int pic) {
        mPic = pic;
    }

}
