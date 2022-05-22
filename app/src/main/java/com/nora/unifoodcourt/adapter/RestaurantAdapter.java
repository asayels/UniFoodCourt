package com.nora.unifoodcourt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nora.unifoodcourt.databinding.CardviewRestaurantBinding;
import com.nora.unifoodcourt.managers.OnClick;
import com.nora.unifoodcourt.model.RestaurantModel;
import com.nora.unifoodcourt.model.RestaurantModel;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.NewViewHolder> {

    //Variable declaration
    private Context mContext;
    private List<RestaurantModel> mData;
    private OnClick mOnClick;

    //Constructor to initialize variable
    public RestaurantAdapter(List<RestaurantModel> data, OnClick onClick) {
        mData = data;
        mOnClick = onClick;
    }

    @Override
    public RestaurantAdapter.NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        CardviewRestaurantBinding mBinding = CardviewRestaurantBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new RestaurantAdapter.NewViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(final RestaurantAdapter.NewViewHolder holder, final int position) {
        RestaurantModel model = mData.get(position);

        holder.mBinding.nameTxtV.setText(model.getName());
        holder.mBinding.imgV.setImageResource(model.getPic());
        holder.mBinding.parent.setOnClickListener(view -> mOnClick.onClick(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class NewViewHolder extends RecyclerView.ViewHolder {

        CardviewRestaurantBinding mBinding;

        public NewViewHolder(View itemView) {
            super(itemView);
            mBinding = CardviewRestaurantBinding.bind(itemView);
        }
    }

}
