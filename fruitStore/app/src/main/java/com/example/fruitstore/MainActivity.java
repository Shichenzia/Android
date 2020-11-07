package com.example.fruitstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.activity_newfruit_item,fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getTxtValue(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,detail_page.class);    //跳转设置
                startActivity(intent);
            }
        });
    }

    private  void initFruits(){
        for(int i=0;i<2;i++){
            Fruit apple = new Fruit(R.drawable.apple_pic,"烟台红富士苹果5kg 一级铂金大果 单果230g以上 中秋礼盒 生鲜 新鲜水果 孕妇可食","￥20");
            fruitList.add(apple);
            Fruit banana = new Fruit(R.drawable.banana_pic,"现摘香蕉香甜大香蕉新鲜当季水果整箱10斤","￥30");
            fruitList.add(banana);
            Fruit cherry = new Fruit(R.drawable.cherry_pic,"农家新语 进口车厘子大樱桃2斤 新鲜水果 顺丰空运","￥15");
            fruitList.add(cherry);
            Fruit grape = new Fruit(R.drawable.grape_pic,"当季新鲜夏黑葡萄无籽非巨峰孕妇水果新鲜水果葡萄提子3斤5斤可选 3斤装礼盒","￥5");
            fruitList.add(grape);
            Fruit mango = new Fruit(R.drawable.mango_pic,"新鲜越南大青芒青皮芒果大果当季新鲜甜心芒果大青皮芒果整箱 大青芒5斤(单果350g以上)","￥6");
            fruitList.add(mango);
            Fruit orange = new Fruit(R.drawable.orange_pic,"誉福园高山峡江青皮橘子柑橘蜜橘橙子柑橘酸甜孕妇水果 新鲜水果京东生鲜 5斤装","￥40");
            fruitList.add(orange);

        }
    }
}