package com.example.nan.a4;

import android.util.IntProperty;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Nan on 2018-03-15.
 */

public class TriangleViewController implements View.OnTouchListener {
    Triangle selected = null;
    TriangleModel triangleModel;
    TriangleView triangleView;

    int state = 0;
    IntProperty<Integer> stateProperty;

    final int STATE_READY = 0;
    final int STATE_SELECTED = 1;
    final int STATE_TRANSLATION = 2;
    final int STATE_SCALE = 3;
    final int STATE_ROTATE = 4;

    public TriangleViewController() {
    }

    public void setModel(TriangleModel model){
        this.triangleModel = model;
    }

    public void setView(TriangleView view){
        this.triangleView = view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float posX = motionEvent.getX();
        float posY = motionEvent.getY();

        switch (state){
            case STATE_READY:
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (triangleModel.contains(posX,posY)){
                                selected = triangleModel.findClick(posX,posY);
                                triangleView.invalidate();
                                state = STATE_SELECTED;
                        }
                        break;
                }
            break;
            case STATE_SELECTED:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(triangleModel.containsYellow(posX,posY,selected)){
                            state = STATE_SCALE;
                        }else if(triangleModel.containsGreen(posX,posY,selected)){
                            state = STATE_ROTATE;
                        }else if (!triangleModel.contains(posX,posY)){
                            selected = null;
                            triangleView.invalidate();
                            state = STATE_READY;
                        }else{
                            selected = triangleModel.findClick(posX,posY);
                            triangleView.invalidate();
                            state = STATE_SELECTED;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                       if(triangleModel.contains(posX,posY) && triangleModel.findClick(posX,posY)==selected){
                            state = STATE_TRANSLATION;
                        }else  if(triangleModel.containsYellow(posX,posY,selected)){
                           state = STATE_SCALE;
                        }else if(triangleModel.containsGreen(posX,posY,selected)){
                           state = STATE_ROTATE;
                       }else{
                            selected = null;
                            triangleView.invalidate();
                            state = STATE_READY;
                        }
                }
            break;
            case STATE_TRANSLATION:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        if(triangleModel.contains(posX,posY)){
                            selected.setTranslate(posX,posY);
                            triangleView.invalidate();
                        }else{
                            selected=null;
                            triangleView.invalidate();
                            state = STATE_READY;
                        }
                    break;
                    case MotionEvent.ACTION_DOWN:
                        if(triangleModel.containsYellow(posX,posY,selected)){
                            state = STATE_SCALE;
                        }else if(triangleModel.containsGreen(posX,posY,selected)){
                            state = STATE_ROTATE;
                        }else
                        if(!triangleModel.contains(posX,posY)){
                            selected=null;
                            triangleView.invalidate();
                            state = STATE_READY;
                        }else if(triangleModel.findClick(posX,posY)!=selected){
                            selected = triangleModel.findClick(posX,posY);
                            triangleView.invalidate();
                            state = STATE_SELECTED;
                        }
                        break;
                }
            break;
            case STATE_SCALE:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(triangleModel.containsYellow(posX,posY,selected)){
                            state = STATE_SCALE;
                        }else if(triangleModel.containsGreen(posX,posY,selected)){
                            state = STATE_ROTATE;
                        }else if(!triangleModel.contains(posX,posY)){
                            selected=null;
                            triangleView.invalidate();
                            state = STATE_READY;
                        }else if(triangleModel.findClick(posX,posY)!=selected){
                            selected = triangleModel.findClick(posX,posY);
                            triangleView.invalidate();
                            state = STATE_SELECTED;
                        }else{
                            state = STATE_SELECTED;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        selected.setScale(posX,posY);
                        triangleView.invalidate();
                        break;
                }
                break;
            case STATE_ROTATE:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(triangleModel.containsGreen(posX,posY,selected)){
                            state = STATE_ROTATE;
                        }else if(triangleModel.containsGreen(posX,posY,selected)){
                            state = STATE_ROTATE;
                        }else if(!triangleModel.contains(posX,posY)){
                            selected=null;
                            triangleView.invalidate();
                            state = STATE_READY;
                        }else if(triangleModel.findClick(posX,posY)!=selected){
                            selected = triangleModel.findClick(posX,posY);
                            triangleView.invalidate();
                            state = STATE_SELECTED;
                        }else{
                            state = STATE_SELECTED;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        double theta= Math.atan2(posY-selected.ty,posX-selected.tx);
                        selected.setAngle((float)theta);
                        triangleView.invalidate();
                        break;
                }
                break;


        }
        return true;
    }
}
