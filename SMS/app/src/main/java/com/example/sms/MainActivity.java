package com.example.sms;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.util.Student;

import org.litepal.crud.DataSupport;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private LinearLayout studentLay;
    private LinearLayout centreLay;
    private LinearLayout mineLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentLay = findViewById(R.id.student);
        centreLay = findViewById(R.id.center);
        mineLay = findViewById(R.id.mine);

        // 删除数据库缓存
        DataSupport.deleteAll(Student.class);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        transaction.replace(R.id.main,new StudentFragment());
        transaction.commit();

        studentLay.setOnClickListener(this);
        centreLay.setOnClickListener(this);
        mineLay.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        clearBackColor();
        switch (v.getId()){
            case R.id.student:
                // 删除数据库缓存
                DataSupport.deleteAll(Student.class);
                studentLay.setBackgroundColor(ContextCompat.getColor(this, R.color.colorShow));
                transaction.replace(R.id.main,new StudentFragment());
                transaction.commit();

                break;
            case R.id.center:
                centreLay.setBackgroundColor(ContextCompat.getColor(this, R.color.colorShow));

                transaction.replace(R.id.main,new CentreFragment());
                transaction.commit();
                break;
            case R.id.mine:
                mineLay.setBackgroundColor(ContextCompat.getColor(this, R.color.colorShow));
                mineLay.setPadding(0,0,0,0);
                transaction.replace(R.id.main,new MyFragment());
                transaction.commit();
                break;
            default:
                break;
        }


    }

    public void clearBackColor(){
        studentLay.setBackgroundColor(ContextCompat.getColor(this, R.color.colorHide));
        centreLay.setBackgroundColor(ContextCompat.getColor(this, R.color.colorHide));
        mineLay.setBackgroundColor(ContextCompat.getColor(this, R.color.colorHide));

    }
}