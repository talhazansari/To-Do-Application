package com.example.todoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listTwoAdapter extends  RecyclerView.Adapter<listTwoAdapter.ViewHolder>  {
    static ArrayList <Items> itemsList;
    AddTask2 fromMain;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        ImageView ivPriority, ivRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItem);
            ivRemove = itemView.findViewById(R.id.ivRemove);

        }
    }
    public listTwoAdapter(Context context, ArrayList<Items> list)
    {
        itemsList = list;
        fromMain = (AddTask2) context;


    }
    @NonNull
    @Override
    public listTwoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemslist,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(itemsList.get(position));
        holder.tvName.setText(itemsList.get(position).getItem());
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsList.remove(position);  // remove the item from list
                notifyItemRemoved(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return  itemsList.size();

    }
}