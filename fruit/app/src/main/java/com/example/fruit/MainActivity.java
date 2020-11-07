package com.example.fruit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getTxtValue(),Toast.LENGTH_SHORT).show();
            }
        });
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