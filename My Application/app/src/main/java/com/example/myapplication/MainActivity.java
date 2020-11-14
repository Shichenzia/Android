package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button save;
    private Button read;
    private Button createDB;
    private Button addData;
    private Button query;
    private TextView data;
   // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save);
        read = findViewById(R.id.read);
        data = findViewById(R.id.data);
        createDB = findViewById(R.id.createDB);
        addData = findViewById(R.id.addData);
        query = findViewById(R.id.query);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","憨憨");
                editor.putInt("age",20);
                editor.apply();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences editor = getSharedPreferences("data",MODE_PRIVATE);
                String name = editor.getString("name","");
                int age = editor.getInt("age",0);
                data.setText("name="+name+"age= "+age);
            }
        });

        createDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getWritableDatabase();
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book(125,"憨憨",20,256,"世界");
                book.save();
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for(Book book: books){
                    Log.d("MainActivity",book.getName());
                }
            }
        });


    }
}