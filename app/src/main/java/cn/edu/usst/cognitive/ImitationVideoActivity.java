package cn.edu.usst.cognitive;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImitationVideoActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private View btnLayout;

    private static final String ac1 = "S001C001P001R001A033_rgb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitation_video);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.WHITE);

        btnLayout = findViewById(R.id.btn_layout);
        btnLayout.setVisibility(View.INVISIBLE);
        mVideoView = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.a33;
        mVideoView.setVideoURI(Uri.parse(path));
        mVideoView.start();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnLayout.setVisibility(View.VISIBLE);
//                mVideoView.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.btn_replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLayout.setVisibility(View.INVISIBLE);
//                mVideoView.setVisibility(View.VISIBLE);
                mVideoView.start();
            }
        });
    }
}
