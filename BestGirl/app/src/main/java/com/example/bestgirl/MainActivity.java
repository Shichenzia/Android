package com.example.bestgirl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer=null; //声频
    private AudioManager audioManager=null; //音频
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mediaPlayer= MediaPlayer.create(MainActivity.this, R.raw.music);
//        mediaPlayer.setLooping(true);//设置循环播放
//        mediaPlayer.start();//播放声音

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            //initMediaPlayer();
        }

//        public void initMediaPlayer() {
//
//        }

    }
}