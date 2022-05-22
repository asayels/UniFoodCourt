package com.nora.unifoodcourt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nora.unifoodcourt.databinding.CardviewFoodBinding;
import com.nora.unifoodcourt.databinding.CardviewRestaurantBinding;
import com.nora.unifoodcourt.managers.OnClick;
import com.nora.unifoodcourt.model.FoodModel;
import com.nora.unifoodcourt.model.RestaurantModel;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.NewViewHolder> {

    //Variable declaration
    private Context mContext;
    private List<FoodModel> mData;
    private OnClick mOnClick;

    //Constructor to initialize variable
    public FoodAdapter(List<FoodModel> data, OnClick onClick) {
        mData = data;
        mOnClick = onClick;
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        CardviewFoodBinding mBinding = CardviewFoodBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new FoodAdapter.NewViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(final NewViewHolder holder, final int position) {
        FoodModel model = mData.get(position);

        holder.mBinding.nameTxtV.setText(model.getName());
        holder.mBinding.imgV.setImageResource(model.getPic());
        holder.mBinding.priceTxtV.setText(model.getPrice() + " SAR");
        holder.mBinding.parent.setOnClickListener(view -> mOnClick.onClick(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class NewViewHolder extends RecyclerView.ViewHolder {

        CardviewFoodBinding mBinding;

        public NewViewHolder(View itemView) {
            super(itemView);
            mBinding = CardviewFoodBinding.bind(itemView);
        }
    }

}
