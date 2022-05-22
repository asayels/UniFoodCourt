package com.nora.unifoodcourt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.nora.unifoodcourt.adapter.OrderAdapter;
import com.nora.unifoodcourt.databinding.ActivityMyOrdersBinding;
import com.nora.unifoodcourt.managers.DatabaseHelper;
import com.nora.unifoodcourt.model.OrderModel;

import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    private Context mContext;
    private ActivityMyOrdersBinding mBinding;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMyOrdersBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mContext = MyOrdersActivity.this;
        mDatabaseHelper = new DatabaseHelper(mContext);

        mBinding.topBarInclude.titleTxtV.setText("My Orders");
        mBinding.topBarInclude.backImgV.setOnClickListener(view -> onBackPressed());


    }

    @Override
    protected void onResume() {
        super.onResume();
        List<OrderModel> orderList = mDatabaseHelper.retrieveMyOrders();
        if (!orderList.isEmpty())
            mBinding.noOrdersTxtV.setVisibility(View.GONE);
        OrderAdapter ordersAdapter = new OrderAdapter(orderList, position -> {
            Intent intent = new Intent(mContext, OrderDetailsActivity.class);
            intent.putExtra("orderModel", new Gson().toJson(orderList.get(position)));
            startActivity(intent);
        });
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext,1));
        mBinding.recyclerView.setAdapter(ordersAdapter);

    }
}