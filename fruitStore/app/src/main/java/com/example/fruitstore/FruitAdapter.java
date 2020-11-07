package com.example.fruitstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private  int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView fruitImage = (ImageView) view.findViewById(R.id.imge);
        TextView fruitName =(TextView) view.findViewById(R.id.txt1);
        TextView fruitPrice =(TextView) view.findViewById(R.id.txt2);
       fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getTxtValue());
        fruitPrice.setText(fruit.getPrice());
        return view;
    }
}
