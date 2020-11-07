package com.example.wachat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class address extends Fragment {

    private List<Addres> addresList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFruits();
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ListView listView = view.findViewById(R.id.list_view);
        AddresAdapter adapter = new AddresAdapter(getActivity(),R.layout.list_item,addresList);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Fruit fruit = fruitList.get(position);
//                Toast.makeText(MainActivity.this,fruit.getTxtValue(),Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this,detail_page.class);    //跳转设置
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private  void initFruits(){
        for(int i=0;i<2;i++){
            Addres addres01 = new Addres(R.drawable.addres1, "张三");
            addresList.add(addres01);
            Addres addres02 = new Addres(R.drawable.addres2, "李四");
            addresList.add(addres02);
            Addres addres03 = new Addres(R.drawable.addres3, "王二");
            addresList.add(addres03);
            Addres addres04 = new Addres(R.drawable.addres4, "铁子");
            addresList.add(addres04);
            Addres addres05 = new Addres(R.drawable.addres5, "憨憨");
            addresList.add(addres05);
            Addres addres06 = new Addres(R.drawable.addres6, "贴贴");
            addresList.add(addres06);
            Addres addres07 = new Addres(R.drawable.addres7, "大哥");
            addresList.add(addres07);
        }
    }
}