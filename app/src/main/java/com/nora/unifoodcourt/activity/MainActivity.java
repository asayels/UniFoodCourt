package com.nora.unifoodcourt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.nora.unifoodcourt.adapter.RestaurantAdapter;
import com.nora.unifoodcourt.databinding.ActivityMainBinding;
import com.nora.unifoodcourt.managers.LocalData;
import com.nora.unifoodcourt.model.FoodModel;
import com.nora.unifoodcourt.model.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ActivityMainBinding mBinding;
    public static String STUDENT_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = MainActivity.this;

        mBinding.topBarInclude.backImgV.setVisibility(View.VISIBLE);
        mBinding.topBarInclude.backImgV.setOnClickListener(view -> onBackPressed());

        mBinding.topBarInclude.myOrdersImgV.setVisibility(View.VISIBLE);
        mBinding.topBarInclude.myOrdersImgV.setOnClickListener(view ->
                startActivity(new Intent(mContext, MyOrdersActivity.class)));
        mBinding.topBarInclude.titleTxtV.setText("Choose Restaurant");

        List<RestaurantModel> list = LocalData.restaurants;
        RestaurantAdapter adapter = new RestaurantAdapter(list, position -> {
            Intent intent = new Intent(mContext, RestaurantActivity.class);
            intent.putExtra("restaurantID", list.get(position).getID());
            startActivity(intent);
        });

        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
    }

}