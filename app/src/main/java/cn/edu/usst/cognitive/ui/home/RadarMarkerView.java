package cn.edu.usst.cognitive.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

import cn.edu.usst.cognitive.R;

@SuppressLint("ViewConstructor")
public class RadarMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public RadarMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
        tvContent.setTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf"));
    }

    private final TextView tvContent;
    private final DecimalFormat format = new DecimalFormat("##0");

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(String.format("%s %%", format.format(e.getY())));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
    }
}
