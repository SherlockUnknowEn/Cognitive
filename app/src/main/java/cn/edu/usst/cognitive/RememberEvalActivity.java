package cn.edu.usst.cognitive;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import cn.edu.usst.cognitive.model.EvalContext;

public class RememberEvalActivity extends AppCompatActivity {

    private static final int size = 5;
    private static final int UPDATE_BTN_ENABLE = 999;
    private static final int NEXT_ACTIVITY = 998;

    private int[] nums = new int[size];
    private MediaPlayer mediaPlayer;
    private int mi = 0;
    private Button startBtn;
    private EvalContext context;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_BTN_ENABLE) {
                if (startBtn != null) {
                    startBtn.setEnabled(true);
                }
            } else if (msg.what == NEXT_ACTIVITY) {
                Intent intent = new Intent(RememberEvalActivity.this, InputNumberActivity.class);
                String str = "";
                for (int i = 0; i < size; i++) {
                    str += nums[i];
                }
                if (context == null) {
                    context = (EvalContext) getIntent().getSerializableExtra("context");
                }
                context.setNums(str);
                intent.putExtra("context", context);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);

        context = (EvalContext) getIntent().getSerializableExtra("context");

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(10);
        }

        startBtn = (Button) findViewById(R.id.btn_start_remember);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBtn.setEnabled(false);
                for (int i = 0; i < size; i++) {
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                mediaPlayer = new MediaPlayer();
                                AssetFileDescriptor fileDescriptor = getAssets().openFd(String.valueOf(nums[mi]) + ".mp3");
                                mi = (mi + 1) % size;
                                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                Toast.makeText(RememberEvalActivity.this, "音频文件损坏，暂时无法播放语音", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    // 设置延迟播放5个数字
                    Timer timer = new Timer();
                    timer.schedule(task, (i) * 2000);
                    Log.d("???", String.valueOf(nums[i]));
//                    timer.cancel();
                }

                // 播放完毕，按钮可用
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = NEXT_ACTIVITY;
                        mHandler.sendMessage(msg);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 6 * 2000);
            }
        });
    }


}
