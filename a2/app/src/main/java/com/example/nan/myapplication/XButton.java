package com.example.nan.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.LinearLayout;
import android.widget.TextView;


public class XButton extends XWidget {
    private Paint p = new Paint();
    String text;
    public int insets;




    public XButton(int id, int width, int height, String text){
        this.id= id;
        this.width=width;
        this.height=height;
        this.text=text;
    }


    public void addChild(XWidget xWidget){


    }
    public void arrange(int prefWidth, int prefHeight){
        width=prefWidth;
        height=prefHeight;

        if (width>getWidth()){
            width=getWidth();
        }
        if (height>getHeight()){
            height=getHeight();
        }

    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(insets,insets,width,height,p);
        p.setColor(Color.GRAY);
    }
}
