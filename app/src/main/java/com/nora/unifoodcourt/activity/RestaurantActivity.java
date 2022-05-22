package com.nora.unifoodcourt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.nora.unifoodcourt.adapter.FoodAdapter;
import com.nora.unifoodcourt.databinding.ActivityRestaurantBinding;
import com.nora.unifoodcourt.managers.LocalData;
import com.nora.unifoodcourt.model.FoodModel;
import com.nora.unifoodcourt.model.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    private Context mContext;
    private ActivityRestaurantBinding mBinding;
    private int mRestaurantID;
    private RestaurantModel mRestaurantModel;
    public static List<FoodModel> MY_CART = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = RestaurantActivity.this;

        mRestaurantID = getIntent().getIntExtra("restaurantID", 0);

        mRestaurantModel = LocalData.getRestaurantByID(mRestaurantID);

        mBinding.topBarInclude.titleTxtV.setText(mRestaurantModel.getName());
        mBinding.topBarInclude.backImgV.setVisibility(View.VISIBLE);
        mBinding.topBarInclude.backImgV.setOnClickListener(view -> onBackPressed());

        mBinding.imgV.setImageResource(mRestaurantModel.getPic());

        mBinding.carFAB.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MyCartActivity.class);
            intent.putExtra("restaurantID", mRestaurantID);
            startActivity(intent);
        });


        List<FoodModel> foodModels = LocalData.getFoodListByID(mRestaurantID);
        FoodAdapter adapter = new FoodAdapter(foodModels, position -> {
            Intent intent = new Intent(mContext, FoodActivity.class);
            intent.putExtra("foodID", foodModels.get(position).getID());
            intent.putExtra("restaurantID", mRestaurantID);
            startActivity(intent);
        });

        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MY_CART.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MY_CART.isEmpty())
            mBinding.carFAB.setVisibility(View.GONE);
        else
            mBinding.carFAB.setVisibility(View.VISIBLE);
    }
}