package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("zxcvb");
                intent.putExtra("name","hanhan");
                sendBroadcast(intent);
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("zxcvb");
        MyBroadCast myBroadCast = new MyBroadCast();
        registerReceiver(myBroadCast,intentFilter);
    }


    class  MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this,intent.getStringExtra("name"),Toast.LENGTH_LONG).show();
        }
    }
}
