package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    Button bt = null;
    TextView text = null;
    ProgressBar pg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bt = findViewById(R.id.btn);
        text = findViewById(R.id.text);
        pg= findViewById(R.id.pg);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownLoad().execute();
            }
        });
    }
    public class DownLoad extends AsyncTask<Void,Integer,Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            int m = 0;
            while(m<=100){
                publishProgress(m);
                m+=10;
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            text.setText("下载完成");
            bt.setText("下载完成");
        }

        @Override
        protected void onPreExecute() {
            bt.setText("正在下载");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            text.setText("当前进度"+values[0]+"%");
            pg.setProgress(values[0]);
        }
    }
}