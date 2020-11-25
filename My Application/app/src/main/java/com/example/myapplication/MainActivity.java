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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button save;
    private Button read;
    private Button createDB;
    private Button addData;
    private Button query;
    private TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save);
        read = findViewById(R.id.read);
        data = findViewById(R.id.data);
        createDB = findViewById(R.id.createDB);
        addData = findViewById(R.id.addData);
        query = findViewById(R.id.query);

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
//                editor.putString("name","憨憨");
//                editor.putInt("age",20);
//                editor.apply();
//            }
//        });
//
//        read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences editor = getSharedPreferences("data",MODE_PRIVATE);
//                String name = editor.getString("name","");
//                int age = editor.getInt("age",0);
//                data.setText("name="+name+"age= "+age);
//            }
//        });



//        createDB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Connector.getWritableDatabase();
//            }
//        });
//
//        addData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Book book = new Book(125,"憨憨",20,256,"世界");
//                book.save();
//            }
//        });
//        query.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Book> books = DataSupport.findAll(Book.class);
//                for(Book book: books){
//                    Log.d("MainActivity",book.getName());
//                }
//            }
//        });

        createDB.setOnClickListener(this);
        addData.setOnClickListener(this);
        query.setOnClickListener(this);



//        try {
//            URL url = new URL("https://www.fastmock.site/mock/4d4c339e0b02ad25d53757a57c9e3fb8/api/getList");
//            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            InputStream res = connection.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(res));
//            StringBuilder r = new StringBuilder();
//            String line;
//            while ((line = reader.readLine())!=null){
//                r.append(line);
//            }
//            Log.d("MainActivity",r.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.createDB:
                create();
                break;
            case R.id.addData:
                add();
                break;
            case R.id.query:
                query();
                break;
            default:
                break;
        }
    }

    void create(){
        Connector.getWritableDatabase();
    }

    void add(){
        Book book = new Book(125,"憨憨",20,256,"世界");
        book.save();
    }

    void query(){
        List<Book> books = DataSupport.findAll(Book.class);
        for(Book book: books){
            Log.d("MainActivity",book.getName());
        }
    }
}