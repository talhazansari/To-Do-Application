package com.example.todoapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int add = 1;
    final int edit =2;
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    Button b;
    static ArrayList<List> list;
    static ArrayList<String> dummyList;
    FloatingActionButton fab;
    boolean flag;
    ImageView ivDelete;
    String file ="myFile.txt";
    ImageView ivPriority;
    ImageView ivEdit;
    TextView tvEmpty;
    ArrayList<Items> tempList;

    int clickPosition;
    String item, task, priority, time, date;
    //                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiliazer();
        flag = false;
        list = new ArrayList<>();
        dummyList = new ArrayList<>();
        tvEmpty = findViewById(R.id.tvEmpty);
        recyclerView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddTask2.class);
                startActivityForResult(intent,add);
            }
        });


    }
    private void saveDataIntoFile()
    {
        FileOutputStream f = null;
        String data = "\n" + list.get(clickPosition).taskName + "\n" + list.get(clickPosition).priority +  "\n" + list.get(clickPosition).time + "\n" + list.get(clickPosition).item2;
        try {
            f= openFileOutput(file, Context.MODE_APPEND);
            try {
                f.write(data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void recyclerView()
    {
        rv = findViewById(R.id.rvID);
        rv.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
//        list.add(new List("Shopping","Xbox One","Read More","7:34 PM","H"));
//        list.add(new List("Studying","Physics","Read More","7:30 PM","H"));
        adapter = new listAdapter(this,list);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void initiliazer()
    {
        fab = findViewById(R.id.fabAdd);
        ivDelete= findViewById(R.id.ivDelete);
        ivPriority= findViewById(R.id.ivPriority);
        ivEdit = findViewById(R.id.ivEdit);
    }
    public void onItemSelected(int position) {
        Intent intent = new Intent(MainActivity.this,EditActivity.class);
        clickPosition= position;

        intent.putExtra("Date", list.get(position).item2);
        intent.putExtra("Time",list.get(position).time);
        intent.putExtra("Priority",list.get(position).priority);
        intent.putExtra("TaskName",list.get(position).taskName);
        intent.putExtra("ItemsArray",tempList);
        startActivityForResult(intent,edit);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==add)
        {
            if(resultCode==RESULT_OK) {
                tvEmpty.setText("Filled List");
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                item= data.getStringExtra("itemName");
                 task= data.getStringExtra("task");
                 priority = data.getStringExtra("priority");
                time = data.getStringExtra("time");
                date = data.getStringExtra("date");
                list.add(new List(task,item,date,time,priority));
                dummyList.add(item);
                adapter.notifyDataSetChanged();
            }
        }
        else if(requestCode==edit)
        {
            if(resultCode==RESULT_OK) {
                String tempItem;
                String tempTask;
                String tempPriority;
                String tempTime;
                String tempDate;
               tempTask = data.getStringExtra("task");
                tempTime = data.getStringExtra("time");
                tempDate = data.getStringExtra("date");
                list.set(clickPosition,new List(tempTask,"Read More",tempDate,tempTime,"H"));
                adapter.notifyDataSetChanged();
            }
        }
    }
}
