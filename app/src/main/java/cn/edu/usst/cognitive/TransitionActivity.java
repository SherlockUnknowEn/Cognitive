package cn.edu.usst.cognitive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.usst.cognitive.model.EvalContext;
import cn.edu.usst.cognitive.model.Route;

public class TransitionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        setFinishOnTouchOutside(false);

        Intent intent = getIntent();
        TextView tv = (TextView) findViewById(R.id.modal_textview);

        Route route = (Route) intent.getSerializableExtra("route");
        EvalContext context = (EvalContext) intent.getSerializableExtra("context");
        tv.setText(route.getText());
        TimerTask delayTask = new TimerTask() {
            @Override
            public void run() {
                Intent nextIntent = new Intent(TransitionActivity.this, route.getCls());
                nextIntent.putExtra( "context", context);
                startActivity(nextIntent);
                TransitionActivity.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(delayTask,2000);//延时两秒执行 run 里面的操作
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        // prevent "back" from leaving this activity
    }
}
