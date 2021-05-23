package com.example.todoapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddTask2 extends AppCompatActivity {
    ImageView ivAdd;
    RecyclerView rv;
    Button btnSave;
    EditText taskName;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    static ArrayList<Items> listTwo;
    final int add =2;
    String item;
    String priority;
    ImageView repeat;
    TextView tvAddItem;
    EditText etTime, etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask);
        repeat = findViewById(R.id.ivRepeat);
        ivAdd = findViewById(R.id.ivAdd);
        btnSave = findViewById(R.id.btnSave);
        etTime = findViewById(R.id.etTime);
        etDate = findViewById(R.id.etDate);
        listTwo = new ArrayList<>();
        taskName = findViewById(R.id.etTaskName);
        tvAddItem = findViewById(R.id.tvAddItem);
        recyclerViewTwo();
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTask2.this, AddInList.class);
                startActivityForResult(intent, add);
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeat.setImageResource(R.drawable.ic_baseline_repeat_24);
            }
        });
        priority= "H";

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);

                    priority = checkedRadioButton.getText().toString().trim();



            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String task = taskName.getText().toString().trim();
                String time = etTime.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                intent.putExtra("itemName",item);
                intent.putExtra("task",task);
                intent.putExtra("priority",priority);
                intent.putExtra("time",time);
                intent.putExtra("date",date);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }


    private void recyclerViewTwo()
    {
        rv = findViewById(R.id.rvIDTwo);
        rv.setHasFixedSize(true);
        manager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv.setLayoutManager(manager);
        adapter = new listTwoAdapter(this,listTwo);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==add)
        {
            if(resultCode==RESULT_OK) {
                tvAddItem.setText("Gotcha! Adding Items!");

                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                item = data.getStringExtra("item");
                listTwo.add(new Items(item));
                adapter.notifyDataSetChanged();
            }
        }
    }
}