package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddInList extends AppCompatActivity {
    Button btnAdd;
    EditText etItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_in_list);
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.EtItem);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String item = etItem.getText().toString().trim();
                intent.putExtra("item",item);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}