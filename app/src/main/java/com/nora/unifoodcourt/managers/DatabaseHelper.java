package com.nora.unifoodcourt.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nora.unifoodcourt.activity.MainActivity;
import com.nora.unifoodcourt.model.FoodModel;
import com.nora.unifoodcourt.model.OrderModel;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    //Constructor to initialise the database
    public DatabaseHelper(Context context) {
        super(context, context.getPackageName() + ".db", null, 1);
    }

    //Creating database tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Users(studentID text primary key, password text, name text, phone text, email text)");
        db.execSQL("create table Orders(id INTEGER primary key autoincrement, studentID text, restaurantID INTEGER, orderReceived INTEGER)");
        db.execSQL("create table Items(id INTEGER primary key autoincrement, orderID INTEGER, foodID INTEGER, quantity INTEGER)");
    }

    //Deleting database tables on upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Users");
        db.execSQL("drop table if exists Orders");
        db.execSQL("drop table if exists Items");
        onCreate(db);
    }


    //Inserting new order to the database
    public boolean insertNewOrder(List<FoodModel> foodModels, int restaurantID) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentvalues = new ContentValues();
        contentvalues.put("orderReceived", 0);
        contentvalues.put("restaurantID", restaurantID);
        contentvalues.put("studentID", MainActivity.STUDENT_ID);
        long orderID = db.insert("Orders", null, contentvalues);
        if (orderID == -1)
            return false;

        for (FoodModel model : foodModels) {
            contentvalues.clear();
            contentvalues.put("orderID", (int) orderID);
            contentvalues.put("foodID", model.getID());
            contentvalues.put("quantity", model.getQuantity());
            long itemID = db.insert("Items", null, contentvalues);
            if (itemID == -1)
                return false;
        }

        return true;
    }

    //Inserting new user to the database
    public boolean insertNewUser(String studentID, String password, String name, String phone, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("studentID", studentID);
        contentvalues.put("password", password);
        contentvalues.put("name", name);
        contentvalues.put("phone", phone);
        contentvalues.put("email", email);

        long ins = db.insert("Users", null, contentvalues);

        if (ins == -1) return false;
        else return true;
    }

    //Retrieve a user by studentID from the database
    public Cursor retrieveUser(String studentID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Users where studentID='" + studentID + "'", null);
        return cursor;
    }

    //Retrieve all user orders from the database
    public List<OrderModel> retrieveMyOrders() {
        List<OrderModel> orderModelList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor orderCursor = db.rawQuery("select * from Orders where studentID='" + MainActivity.STUDENT_ID + "'", null);

        while (orderCursor.moveToNext()) {

            OrderModel orderModel = new OrderModel();
            orderModel.setOrderID(orderCursor.getInt(0));
            orderModel.setStudentID(orderCursor.getString(1));
            orderModel.setOrderReceived(orderCursor.getInt(3) == 1);

            int restaurantID = orderCursor.getInt(2);
            orderModel.setRestaurantModel(restaurantID);

            Cursor itemCursor = db.rawQuery("select * from Items where orderID='" + orderModel.getOrderID() + "'", null);

            while (itemCursor.moveToNext()) {
                int foodID = itemCursor.getInt(2);
                int quantity = itemCursor.getInt(3);

                orderModel.addFood(restaurantID, foodID, quantity);
            }
            orderModelList.add(orderModel);
        }

        return orderModelList;
    }

    public void updateOrderReceived(int orderID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("orderReceived", 1);
        db.update("Orders", contentvalues, "id = ?", new String[]{String.valueOf(orderID)});
    }

}



