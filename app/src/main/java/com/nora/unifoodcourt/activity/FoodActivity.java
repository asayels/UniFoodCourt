package com.nora.unifoodcourt.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.nora.unifoodcourt.adapter.FoodAdapter;
import com.nora.unifoodcourt.databinding.ActivityFoodBinding;
import com.nora.unifoodcourt.managers.LocalData;
import com.nora.unifoodcourt.model.FoodModel;

public class FoodActivity extends AppCompatActivity {

    private Context mContext;
    private ActivityFoodBinding mBinding;
    private int mRestaurantID;
    private int mFoodID;
    private FoodModel mFoodModel;
    private int cartQuantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = FoodActivity.this;

        mRestaurantID = getIntent().getIntExtra("restaurantID", 0);
        mFoodID = getIntent().getIntExtra("foodID", 0);

        mFoodModel = LocalData.getFoodByID(mRestaurantID, mFoodID);

        mBinding.topBarInclude.titleTxtV.setText(mFoodModel.getName());
        mBinding.topBarInclude.backImgV.setVisibility(View.VISIBLE);
        mBinding.topBarInclude.backImgV.setOnClickListener(view -> onBackPressed());

        mBinding.imgV.setImageResource(mFoodModel.getPic());

        mBinding.addToCartBtn.setOnClickListener(v -> {
            mFoodModel.setQuantity(cartQuantity);
            RestaurantActivity.MY_CART.add(mFoodModel);
            finish();
        });

        mBinding.increaseImgBtn.setOnClickListener(v -> {
            cartQuantity += 1;
            mBinding.quantityValueTxtV.setText(String.valueOf(cartQuantity));
        });

        mBinding.decreaseImgBtn.setOnClickListener(v -> {
            if (cartQuantity > 1) {
                cartQuantity -= 1;
                mBinding.quantityValueTxtV.setText(String.valueOf(cartQuantity));
            }
        });


    }

}