package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;

public class MainActivity7 extends AppCompatActivity  {

    private ImageView play;
    private ImageView bt_2;
    private ImageView bt_3;
    private Boolean isPlay = false;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);


        if(ContextCompat.checkSelfPermission(MainActivity7.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity7.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            initMediaPlayer();
        }
    }
    private void initMediaPlayer(){

        try {
            File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
//            mediaPlayer.stop();
//            mediaPlayer.reset();
//            Log.d("TAG", "initMediaPlayer: "+file.getPath());
//            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.setDataSource("http://123.qudashu.com/mmm.mp3");
            Log.d("TAG", "initMediaPlayer: "+file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else {
                    Toast.makeText(MainActivity7.this,"拒绝访问权限无法使用",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                if(isPlay){
                    mediaPlayer.pause();
                    isPlay = false;

                    // play.setImageDrawable(getResources().getDrawable(R.drawable.open));
                }else {
                    mediaPlayer.start();
                    isPlay = true;
                    // play.setImageResource(R.drawable.pause);

                }

                break;
//            case R.id.bt_2:
//                if(mediaPlayer.isPlaying()){
//                    mediaPlayer.pause();
//                }
//                break;
//            case R.id.bt_3:
//                if(mediaPlayer.isPlaying()){
//                    mediaPlayer.reset();
//                    initMediaPlayer();
//                }
//                break;
            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}