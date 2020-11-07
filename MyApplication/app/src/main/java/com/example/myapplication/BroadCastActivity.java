package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BroadCastActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private MyBroadCast myBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast3);
        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapplication.BroadCastActivity3");
                sendBroadcast(intent);
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.myapplication.BroadCastActivity3");
        MyBroadCast myBroadCast = new MyBroadCast();
        registerReceiver(myBroadCast,intentFilter);

    }
    class MyBroadCast extends BroadcastReceiver {
        public void onReceive(Context context,Intent intent){
            @SuppressLint("ServiceCast") ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
           if(networkInfo != null && networkInfo.isAvailable()){
               Toast.makeText(BroadCastActivity.this,"实现",Toast.LENGTH_SHORT).show();
           }
           else {
               Toast.makeText(BroadCastActivity.this,"未实现",Toast.LENGTH_SHORT).show();
           }

            Toast.makeText(BroadCastActivity.this,"接收广播",Toast.LENGTH_LONG).show();
        }
    }




    protected void onDestory(){
        super.onDestroy();
    }
}