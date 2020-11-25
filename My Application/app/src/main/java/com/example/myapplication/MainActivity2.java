package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.LogRecord;

public class MainActivity2 extends AppCompatActivity {
    private Button click;
    private TextView text;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            text.setText(msg.what+"");
        }
    };

   // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        click = findViewById(R.id.click);
        text = findViewById(R.id.text);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        int cont = 0;
                        while (cont <= 10){
                            Message message = new Message();
                            message.what = cont;
                            handler.sendMessage(message);
                            cont+=1;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

    }
}