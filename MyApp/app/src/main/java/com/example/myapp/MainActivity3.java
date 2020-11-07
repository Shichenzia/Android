package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity3 extends AppCompatActivity {
    private ListView myList;
    private String[] data={"hanhga","jsdkfhs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myList = (ListView) findViewById(R.id.my_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity3.this,android.R.layout.simple_list_item_1,data);
        myList.setAdapter(adapter);

    }
}