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

public class EditAdapter extends RecyclerView.Adapter <EditAdapter.ViewHolder> {
    static ArrayList<EditItems> itemsList;
    static EditActivity fromMain;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvEditName;
        ImageView  ivEditRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEditName = itemView.findViewById(R.id.tvEditName);
            ivEditRemove = itemView.findViewById(R.id.ivEditRemove);

        }


    }

    public EditAdapter(Context context, ArrayList<EditItems> list)
    {
        itemsList = list;
        fromMain = (EditActivity) context;


    }
    @NonNull
    @Override
    public EditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.editlist,parent,false);
        return new EditAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EditAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(itemsList.get(position));
        holder.tvEditName.setText(itemsList.get(position).getItem());
        holder.ivEditRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsList.remove(position);
                notifyItemRemoved(position);
                // remove the item from list
            }
        });
    }
    @Override
    public int getItemCount() {
        return  itemsList.size();

    }
}
