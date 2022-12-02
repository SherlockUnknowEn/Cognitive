package cn.edu.usst.cognitive;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jkb.vcedittext.VerificationCodeEditText;

import cn.edu.usst.cognitive.model.EvalContext;
import cn.edu.usst.cognitive.model.Route;
import cn.edu.usst.cognitive.ui.home.HomeFragment;
import cn.edu.usst.cognitive.utils.CUtils;

public class InputNumberActivity extends AppCompatActivity {

    private VerificationCodeEditText mCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_number);
        Window window = this.getWindow();
//取消设置Window半透明的Flag
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//设置状态栏颜色
        window.setStatusBarColor(Color.WHITE);

        mCodeEditText = (VerificationCodeEditText) findViewById(R.id.codeEditText);

        findViewById(R.id.btn_next_imitate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = mCodeEditText.getText().toString();
                EvalContext context = (EvalContext) getIntent().getSerializableExtra("context");
                context.setInputNums(a);
                Log.d("nums", context.getNums());
                Log.d("inputNums", context.getInputNums());
                // 跳转到第二项: 模仿评估
                CUtils.Transit2Activity(InputNumberActivity.this,
                        new Route("3. 模仿模块评估", ImitationVideoActivity.class));
            }
        });
    }
}
