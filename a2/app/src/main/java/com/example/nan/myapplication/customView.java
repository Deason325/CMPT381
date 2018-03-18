package com.example.nan.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Nan on 2018-01-30.
 */


    public class customView extends View {


    private Paint p = new Paint();
    //XFrame root = new XFrame(1);

    public customView(Context context) {
        super(context);
        init(null);


    }

    public customView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public customView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public customView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

    }





    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.rgb(100, 10, 255));
        canvas.drawRect(100, 300, 400, 500, p);
        p.setColor(Color.rgb(0,255,0));
        //p.setColor(Color.GREEN);
        //root.layout(0,0,getWidth(),getHeight());
        //root.layout(getLeft(),getTop(),getRight(),getBottom());

    }
}





