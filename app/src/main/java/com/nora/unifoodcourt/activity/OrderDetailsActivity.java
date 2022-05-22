package com.nora.unifoodcourt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.nora.unifoodcourt.adapter.CartAdapter;
import com.nora.unifoodcourt.adapter.FoodAdapter;
import com.nora.unifoodcourt.adapter.OrderAdapter;
import com.nora.unifoodcourt.databinding.ActivityMyOrdersBinding;
import com.nora.unifoodcourt.databinding.ActivityOrderDetailsBinding;
import com.nora.unifoodcourt.managers.DatabaseHelper;
import com.nora.unifoodcourt.model.OrderModel;

import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    private Context mContext;
    private ActivityOrderDetailsBinding mBinding;
    private DatabaseHelper mDatabaseHelper;
    private OrderModel mOrderModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = OrderDetailsActivity.this;
        mDatabaseHelper = new DatabaseHelper(mContext);

        mBinding.topBarInclude.titleTxtV.setText("Order Details");
        mBinding.topBarInclude.backImgV.setOnClickListener(view -> onBackPressed());

        mOrderModel = new Gson().fromJson(getIntent().getStringExtra("orderModel"), OrderModel.class);


        mBinding.orderInclude.orderIDTxtV.setText(String.valueOf(mOrderModel.getOrderID()));
        mBinding.orderInclude.priceTxtV.setText(mOrderModel.getTotalPrice() + " SAR");
        mBinding.orderInclude.nameTxtV.setText(mOrderModel.getRestaurantModel().getName());
        mBinding.orderInclude.imgV.setImageResource(mOrderModel.getRestaurantModel().getPic());

        CartAdapter adapter = new CartAdapter(mOrderModel.getFoodModels());
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.orderReceivedBtn.setOnClickListener(view -> {
            new DatabaseHelper(mContext).updateOrderReceived(mOrderModel.getOrderID());
            mOrderModel.setOrderReceived(true);
            updateStatus();
        });

        updateStatus();
    }

    private void updateStatus() {
        if (mOrderModel.isOrderReceived()) {
            mBinding.orderReceivedTxtV.setVisibility(View.VISIBLE);
            mBinding.orderReceivedBtn.setVisibility(View.INVISIBLE);
            mBinding.creditCardBtn.setVisibility(View.GONE);
            mBinding.cashBtn.setVisibility(View.GONE);
        } else {
            mBinding.orderReceivedTxtV.setVisibility(View.INVISIBLE);
            mBinding.orderReceivedBtn.setVisibility(View.VISIBLE);
            mBinding.creditCardBtn.setVisibility(View.VISIBLE);
            mBinding.cashBtn.setVisibility(View.VISIBLE);
        }
    }
}