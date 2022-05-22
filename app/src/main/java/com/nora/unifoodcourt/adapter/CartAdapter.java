package com.nora.unifoodcourt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nora.unifoodcourt.databinding.CardviewCartItemBinding;
import com.nora.unifoodcourt.model.FoodModel;
import com.nora.unifoodcourt.model.RestaurantModel;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.NewViewHolder> {

    //Variable declaration
    private Context mContext;
    private List<FoodModel> mData;

    //Constructor to initialize variable
    public CartAdapter(List<FoodModel> data) {
        mData = data;
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        CardviewCartItemBinding mBinding = CardviewCartItemBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new CartAdapter.NewViewHolder(mBinding.getRoot());
    }


    @Override
    public void onBindViewHolder(final NewViewHolder holder, final int position) {
        FoodModel model = mData.get(position);

        holder.mBinding.itemNameTxtV.setText(model.getName());
        holder.mBinding.itemPriceTxtV.setText(model.getPrice() + " SAR");
        holder.mBinding.itemQuantityTxtV.setText(model.getQuantity() + "x");

        holder.mBinding.itemImgV.setImageResource(model.getPic());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class NewViewHolder extends RecyclerView.ViewHolder {

        CardviewCartItemBinding mBinding;

        public NewViewHolder(View itemView) {
            super(itemView);
            mBinding = CardviewCartItemBinding.bind(itemView);
        }
    }

}
