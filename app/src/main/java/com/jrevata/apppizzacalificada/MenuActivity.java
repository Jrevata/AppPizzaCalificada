package com.jrevata.apppizzacalificada;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MenuActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        videoView = (VideoView) findViewById(R.id.videoview);
        videoView.setMediaController(new MediaController(this));

        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video_pizza));
        videoView.start();

    }

    public void ordenar(View view){
        Intent listSong = new Intent(getApplicationContext(), DeliveryActivity.class);
        startActivity(listSong);
    }
}
