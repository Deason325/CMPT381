package com.example.nan.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class XFrame extends XWidget{
    ArrayList<XWidget> children;
    Random rn = new Random();
    private Paint p = new Paint();
    int left;
    int top;
    //true for Horizontal, false for Vertical
    boolean ori = true;

    public XFrame(int id){
        this.id=id;
        arrange(0,getWidth());
    }

    public void setOrientation(String orientation){
        if (orientation == "HORIZONTAL"){
            ori = true;
        }
        if (orientation == "VERTICAL"){
            ori = false;
        }else{
            //error;
            return ;
        }
    }
    public void addChild(XWidget item){
       children.add(item);
    }

    public void arrange(int Tleft, int perfWidth){
        width = perfWidth;
        left = Tleft;
        height = getHeight()/2;

        int childWidth = width/children.size();

        for (int i =0; i< children.size(); i++){
            children.get(i).arrange(left+i*childWidth,childWidth);
        }
    }

    public void arrangeV(int Ttop, int prefHeight){
        height = prefHeight;
        top = Ttop;
        width = getWidth()/2;

        int childHeight = height/children.size();

        for (int i =0; i< children.size(); i++){
            children.get(i).arrange(top+i*childHeight,childHeight);
        }
    }


    public void draw(Canvas canvas){

        canvas.drawColor(Color.YELLOW);

            canvas.drawRect(left,getHeight()/2,width,height,p);


        p.setColor(Color.rgb(rn.nextInt(255+1),rn.nextInt(255+1),rn.nextInt(255+1)));
        for(XWidget child : children){
            draw(canvas);

        }
    }



}
