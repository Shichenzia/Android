package com.example.wachat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AddresAdapter extends  ArrayAdapter<Addres>  {
    private  int resourceId;

    public AddresAdapter(Context context, int textViewResourceId, List<Addres> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Addres addres = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView addresImage = (ImageView) view.findViewById(R.id.img);
        TextView addresName =(TextView) view.findViewById(R.id.name);
        addresImage.setImageResource(addres.getImageId());
        addresName.setText(addres.getTxtValue());
        return view;
    }
}
