package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        EditText text1 = findViewById(R.id.text1);
//        EditText text2 = findViewById(R.id.text2);
//
//        String inputText1=text1.getText().toString();
//        final double inputNum1 = Double.parseDouble(inputText1);
//
//        String inputText2=text2.getText().toString();
//        final double inputNum2 = Double.parseDouble(inputText2);


        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click(0);
            }
        });

        Button sub = findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click(1);
            }
        });

        Button mul = findViewById(R.id.mul);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Click(2);
            }
        });

        Button div = findViewById(R.id.div);
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Click(3);
            }
        });
    }

    public int Click(int x){
        EditText text1 = findViewById(R.id.text1);
        EditText text2 = findViewById(R.id.text2);

        String inputText1=text1.getText().toString();
//        double inputNum1 ;
//        if( inputText1.equals("")){
//             inputNum1 = 0;
//        }else{
//            inputNum1 = Double.parseDouble(inputText1);
//        }

        final double inputNum1 =  inputText1.equals("")? 0: Double.parseDouble(inputText1);

        String inputText2=text2.getText().toString();
//                double inputNum2 ;
//        if( inputText2.equals("")){
//             inputNum2 = 0;
//        }else{
//            inputNum2 = Double.parseDouble(inputText2);
//        }
        final double inputNum2 =  inputText2.equals("")? 0: Double.parseDouble(inputText2);


        Double data = 0.0;
        switch(x){

            case 0:
                data = inputNum1+inputNum2;
                break;

            case 1:
                data = inputNum1-inputNum2;
                break;

            case 2:
                data = inputNum1*inputNum2;
                break;

            case 3:
                if(inputNum2!=0)
                    data = inputNum1/inputNum2;
                else {
                    Toast.makeText(MainActivity.this,"没读过书",Toast.LENGTH_SHORT).show();
                    return 0;
                }
                break;
        }
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("data",data);
        startActivity(intent);
        return 0;
    }

}