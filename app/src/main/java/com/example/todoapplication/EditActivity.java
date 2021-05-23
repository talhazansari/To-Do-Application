package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    EditText etDateTwo, etTimeTwo,etTaskNameTwo;
    Button btnSaveTwo;
    String date,time,priority,taskName;
    RadioGroup radioGroup;
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    static ArrayList<EditItems> list;
    ArrayList<EditItems> tempList;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editactivity);
        i = 0;
        etDateTwo = findViewById(R.id.etDateTwo);
        etTimeTwo = findViewById(R.id.etTimeTwo);
        btnSaveTwo = findViewById(R.id.btnSaveTwo);
        radioGroup = findViewById(R.id.radiogroup2);
        etTaskNameTwo = findViewById(R.id.etTaskNameTwo);
        list = new ArrayList<>();
        date = getIntent().getStringExtra("Date");
        time = getIntent().getStringExtra("Time");
        priority= getIntent().getStringExtra("Priority");
        taskName = getIntent().getStringExtra("TaskName");
        tempList = new ArrayList<>();
        etTaskNameTwo.setText(taskName);
        etDateTwo.setText(date);
        etTimeTwo.setText(time);
        checkPriority(priority);

        btnSaveTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String date = etDateTwo.getText().toString().trim();
                String time = etTimeTwo.getText().toString().trim();
                String task = etTaskNameTwo.getText().toString().trim();
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("task",task);

                setResult(RESULT_OK,intent);
                finish();
            }
        });
        recyclerView();

    }
    private void checkPriority(String priority)
    {
        if(priority.equals("H"))
        {
            radioGroup.check(R.id.radio1);
        }
        else if(priority.equals("M"))
        {
            radioGroup.check(R.id.radio2);

        }
        else if(priority.equals("L"))
        {
            radioGroup.check(R.id.radio3);

        }
    }
    private void recyclerView()
    {
            rv = findViewById(R.id.rvIDThree);
            rv.setHasFixedSize(true);
        manager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
            rv.setLayoutManager(manager);
          EditActivity.list.add(new EditItems(MainActivity.dummyList.get(0)));
        EditActivity.list.add(new EditItems("Scissor"));
        EditActivity.list.add(new EditItems("Mouse"));
        EditActivity.list.add(new EditItems("Laptop"));
        EditActivity.list.add(new EditItems("Mobile"));
        EditActivity.list.add(new EditItems("Notepad"));
            adapter = new EditAdapter(this,list);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
    }
}