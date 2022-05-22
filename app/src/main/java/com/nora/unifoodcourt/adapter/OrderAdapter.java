package com.nora.unifoodcourt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nora.unifoodcourt.databinding.CardviewOrderBinding;
import com.nora.unifoodcourt.managers.OnClick;
import com.nora.unifoodcourt.model.RestaurantModel;
import com.nora.unifoodcourt.model.OrderModel;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.NewViewHolder> {

    //Variable declaration
    private Context mContext;
    private List<OrderModel> mOrderModelList;
    private OnClick mOnClick;

    //Constructor to initialize variable
    public OrderAdapter(List<OrderModel> orderModelList, OnClick onClick) {
        mOrderModelList = orderModelList;
        mOnClick = onClick;
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        CardviewOrderBinding mBinding = CardviewOrderBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new OrderAdapter.NewViewHolder(mBinding.getRoot());
    }


    @Override
    public void onBindViewHolder(final NewViewHolder holder, final int position) {

        OrderModel model = mOrderModelList.get(position);
        holder.mBinding.orderIDTxtV.setText(String.valueOf(model.getOrderID()));
        holder.mBinding.priceTxtV.setText(model.getTotalPrice() + " SAR");
        holder.mBinding.nameTxtV.setText(model.getRestaurantModel().getName());
        holder.mBinding.imgV.setImageResource(model.getRestaurantModel().getPic());

        holder.mBinding.parent.setOnClickListener(view -> mOnClick.onClick(position));

    }

    @Override
    public int getItemCount() {
        return mOrderModelList.size();
    }

    public static class NewViewHolder extends RecyclerView.ViewHolder {

        CardviewOrderBinding mBinding;

        public NewViewHolder(View itemView) {
            super(itemView);
            mBinding = CardviewOrderBinding.bind(itemView);
        }
    }

}
