package com.example.nan.myapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.view.View;

import java.util.ArrayList;


abstract class XWidget{
    ArrayList<XWidget> children;
    int id;
    int width;
    int height;
    int insets;
    public XWidget(){

    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void layout(int left, int top, int right, int bot){
           for(int i=0; i<children.size();i++){
               children.get(i).height= bot-top;
               children.get(i).width = right-left;
           }
    }
    public abstract void arrange(int parcelLeft, int parcelWidth);

    public abstract void addChild(XWidget item);

    public abstract void draw(Canvas canvas);
}
