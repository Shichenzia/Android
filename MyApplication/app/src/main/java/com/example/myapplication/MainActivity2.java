package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Double data = intent.getDoubleExtra("data",0);
        TextView textView = findViewById(R.id.txt1);
        String text = "结果为："+data.toString();
        textView.setText(text);

    }
}