package com.example.moneymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.entity.LoaiChi;

import java.util.List;

public class LoaiChiRecyclerviewADapter extends RecyclerView.Adapter<LoaiChiRecyclerviewADapter.LoaiChiViewHolder>{
    private LayoutInflater mLayoutInflater;
    private List<LoaiChi> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewclickListener;

    public LoaiChiRecyclerviewADapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }
    public LoaiChi getItem(int position){
        if(mList == null || position >= mList.size()){
            return null;
        }
        return mList.get(position);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiChiRecyclerviewADapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewclickListener(ItemClickListener itemViewclickListener) {
        LoaiChiRecyclerviewADapter.itemViewclickListener = itemViewclickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_loai_chi_item,parent,false);
        return new LoaiChiViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position) {
        if(mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null)
            return 0;
        return mList.size();
    }

    public void setList(List<LoaiChi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView ivEdit, ivView;
        public CardView cv;
        public int position;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;
            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemViewclickListener != null){
                        itemViewclickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemEditClickListener != null){
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
