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

public class listAdapter extends  RecyclerView.Adapter<listAdapter.ViewHolder>  {
    static ArrayList <List> itemsList;
    static boolean flag;
    static MainActivity fromMain;
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName, tvItem1,tvItem2,tvTime;
        ImageView ivPriority, ivDelete;
        ImageView ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvItem1= itemView.findViewById(R.id.tvItem1);
            tvItem2 = itemView.findViewById(R.id.tvItem2);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivEdit= itemView.findViewById(R.id.ivEdit);
            ivPriority= itemView.findViewById(R.id.ivPriority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fromMain.onItemSelected(itemsList.indexOf((List) itemView.getTag()));
                }
            });

        }
    }
    public listAdapter(Context context, ArrayList<List> list)
    {
        itemsList = list;
        fromMain = (MainActivity) context;


    }
    public listAdapter()
    {


    }
    @NonNull
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(itemsList.get(position));
       holder.tvName.setText(itemsList.get(position).getTaskName());
        holder.tvItem1.setText(itemsList.get(position).getItem1());
        holder.tvItem2.setText(itemsList.get(position).getItem2());
        holder.tvTime.setText(itemsList.get(position).getTime());
        holder.ivDelete.setVisibility(View.INVISIBLE);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.ivDelete.setVisibility(View.VISIBLE);
                holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemsList.remove(position);  // remove the item from list
                        notifyItemRemoved(position);

                    }
                });
                return false;
            }
        });

        if(itemsList.get(position).getPriority().equals("H"))
        {
            holder.ivPriority.setImageResource(R.drawable.red);
        }
        else if(itemsList.get(position).getPriority().equals("M"))
        {
            holder.ivPriority.setImageResource(R.drawable.green);
        }
        else
        {
            holder.ivPriority.setImageResource(R.drawable.yellow);
        }


    }

    @Override
    public int getItemCount() {
       return  itemsList.size();

    }
    public void receiveFlag(boolean fl)
    {
        flag= fl;
    }
}
