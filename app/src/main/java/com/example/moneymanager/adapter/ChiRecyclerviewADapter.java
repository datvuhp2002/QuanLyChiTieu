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
import com.example.moneymanager.entity.Chi;

import java.util.List;

public class ChiRecyclerviewADapter extends RecyclerView.Adapter<ChiRecyclerviewADapter.ChiViewHolder>{
    private LayoutInflater mLayoutInflater;
    private List<Chi> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewclickListener;

    public ChiRecyclerviewADapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }
    public Chi getItem(int position){
        if(mList == null || position >= mList.size()){
            return null;
        }
        return mList.get(position);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ChiRecyclerviewADapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewclickListener(ItemClickListener itemViewclickListener) {
        ChiRecyclerviewADapter.itemViewclickListener = itemViewclickListener;
    }

    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_chi_item,parent,false);
        return new ChiViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, int position) {
        if(mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.tvAmout.setText(""+mList.get(position).sotien+"Đồng");
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null)
            return 0;
        return mList.size();
    }

    public void setList(List<Chi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ChiViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName,tvAmout;
        public ImageView ivEdit, ivView;
        public CardView cv;
        public int position;
        public ChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmout = itemView.findViewById(R.id.tvAmount);
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
