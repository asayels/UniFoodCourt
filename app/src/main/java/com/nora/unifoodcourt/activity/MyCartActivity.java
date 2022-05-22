package com.nora.unifoodcourt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nora.unifoodcourt.adapter.CartAdapter;
import com.nora.unifoodcourt.databinding.ActivityMyCartBinding;
import com.nora.unifoodcourt.managers.DatabaseHelper;

public class MyCartActivity extends AppCompatActivity {

    private Context mContext;
    private ActivityMyCartBinding mBinding;
    private DatabaseHelper mDatabaseHelper;
    private int mRestaurantID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMyCartBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = MyCartActivity.this;

        mRestaurantID = getIntent().getIntExtra("restaurantID", 0);

        mBinding.topBarInclude.titleTxtV.setText("My Cart");
        mBinding.topBarInclude.backImgV.setOnClickListener(view -> onBackPressed());

        mDatabaseHelper = new DatabaseHelper(mContext);

        double total = RestaurantActivity.MY_CART.stream().mapToDouble(m -> m.getQuantity() * m.getPrice()).sum();
        mBinding.sendOrderBtn.setText("Send Order (" + total + " SAR)");
        mBinding.sendOrderBtn.setOnClickListener(view -> {
            boolean success = mDatabaseHelper.insertNewOrder(RestaurantActivity.MY_CART, mRestaurantID);
            if (success) {
                RestaurantActivity.MY_CART.clear();
                Toast.makeText(mContext, "Your order has been sent successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_LONG).show();

        });

        CartAdapter cartAdapter = new CartAdapter(RestaurantActivity.MY_CART);
        mBinding.recyclerView.setAdapter(cartAdapter);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));


    }
}