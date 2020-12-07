package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
//import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
//import android.widget.MediaController;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener{
    private ImageView play;
    private VideoView videoView;
    private Boolean isPlay = false;
    //private MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        play = findViewById(R.id.play);

        videoView = findViewById(R.id.video_view);
        play.setOnClickListener(this);


        if (ContextCompat.checkSelfPermission(MainActivity8.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity8.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPath();
        }
    }
    private void initVideoPath(){
        File file = new File(Environment.getExternalStorageDirectory(),"/sdcard/shengrikuaile.mp4");
        videoView.setVideoPath("http://123.qudashu.com/video.mp4");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play:
                if(isPlay){
                    videoView.pause();
                    isPlay = false;
                    // play.setImageDrawable(getResources().getDrawable(R.drawable.open));
                }else {
                    videoView.start();
                    isPlay = true;
                    // play.setImageResource(R.drawable.pause);

                }
                break;
            default:
                break;
        }
    }
    protected void onDestroy() {

        super.onDestroy();
        if (videoView != null){
            videoView.suspend();
        }
    }
}