package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private  Button btn1 ;
//    private ProgressBar pb1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        btn1 = (Button) findViewById(R.id.btn1);
//        pb1 = (ProgressBar) findViewById(R.id.pb1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int x = pb.getProgress();
////                x = x+10 ;
////                pb.setProgress(x);
//                //Toast.makeText(MainActivity.this,"你好",Toast.LENGTH_LONG).show();
//                AlertDialog.Builder dl = new AlertDialog.Builder(MainActivity.this);
//                dl.setTitle("shanbi");
//                dl.setMessage("hanpi");
//                dl.setCancelable(false);
//                dl.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                dl.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                dl.show();
//            }
//
//
//        });
//    }

    private ListView myList;
    private String[] data={"hanhga","jsdkfhs"};
    private ArrayList<Fruit> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit,fruitList);
        myList = (ListView) findViewById(R.id.my_list);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        myList.setAdapter(adapter);
    }

    private  void initFruits(){
        for(int i=0;i<2;i++){
            Fruit apple = new Fruit(R.drawable.apple_pic,"apple");
            fruitList.add(apple);
            Fruit banana = new Fruit(R.drawable.banana_pic,"banana");
            fruitList.add(banana);
            Fruit cherry = new Fruit(R.drawable.cherry_pic,"cherry");
            fruitList.add(cherry);
            Fruit grape = new Fruit(R.drawable.grape_pic,"grape");
            fruitList.add(grape);
            Fruit mango = new Fruit(R.drawable.mango_pic,"mango");
            fruitList.add(mango);
            Fruit orange = new Fruit(R.drawable.orange_pic,"orange");
            fruitList.add(orange);

        }
    }
}