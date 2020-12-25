package com.example.sms;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.util.Admin;

import org.litepal.crud.DataSupport;

import java.util.List;


public class MyFragment extends Fragment {
    private Button loginOutBtn;
    private TextView nameText;
    private TextView usernameText;
    private TextView photoText;
    private TextView powerText;
    private TextView describeText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_my, container, false);
        loginOutBtn = view.findViewById(R.id.loginOut);
        nameText = view.findViewById(R.id.a_name);
        usernameText = view.findViewById(R.id.a_username);
        photoText = view.findViewById(R.id.a_photo);
        powerText = view.findViewById(R.id.a_power);
        describeText = view.findViewById(R.id.a_describe);


        List<Admin>  admin = DataSupport.findAll(Admin.class);
        if(admin.size()!=0){
            nameText.setText(admin.get(0).getA_name());
            usernameText.setText(admin.get(0).getA_username());
            photoText.setText(String.valueOf(admin.get(0).getA_phone()));
            powerText.setText(String.valueOf(admin.get(0).getA_power()));
            describeText.setText(admin.get(0).getA_describe());
        }

        loginOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空数据库用户数据
                DataSupport.deleteAll(Admin.class);

                Intent intent = new Intent(getActivity(),LoginActivity.class);
                Toast.makeText(getActivity(),"退出成功！",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        return view;
    }
}