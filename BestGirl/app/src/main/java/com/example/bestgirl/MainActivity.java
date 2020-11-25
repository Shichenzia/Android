package com.example.bestgirl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what % 2800 == 0 ){
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.opao);
                mediaPlayer.start();
            }else {
                setAudio();
                
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAudio();
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.opao);
        mediaPlayer.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int cont = 0;
                while (cont <= 10000){
                    Message message = new Message();
                    message.what = cont;
                    handler.sendMessage(message);
                    cont+=1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void setAudio(){
        AudioManager audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND
                        );

    }
}