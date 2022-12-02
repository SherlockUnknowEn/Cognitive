package cn.edu.usst.cognitive.utils;

import android.app.Activity;
import android.content.Intent;

import cn.edu.usst.cognitive.ImitationVideoActivity;
import cn.edu.usst.cognitive.TransitionActivity;
import cn.edu.usst.cognitive.model.EvalContext;
import cn.edu.usst.cognitive.model.Route;

public class CUtils {

    public static void Transit2Activity(Activity from, Route route) {
        Intent intent = new Intent(from, TransitionActivity.class);
        EvalContext ec = (EvalContext) from.getIntent().getSerializableExtra("context");
        intent.putExtra("route", route);
        intent.putExtra("context", ec);
        from.startActivity(intent);
    }

}
