package com.example.nan.a4;

/**
 * Created by Nan on 2018-03-15.
 */
import android.content.res.Resources;
import android.view.View;
import android.graphics.*;
import android.content.Context;

public class TriangleView extends View implements TriangleModelListener{

    Paint paint;
    TriangleModel trianglemodel;
    TriangleViewController controller;
    float radius = 12;
    public TriangleView(Context context){
        super(context);
        paint = new Paint();
    }

    public void setModel(TriangleModel model){
        this.trianglemodel=model;
    }
    public void setController(TriangleViewController controller1){
        this.controller=controller1;
    }

    int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    int width = Resources.getSystem().getDisplayMetrics().widthPixels;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.rgb(127,0,225));
        canvas.drawRect(0,width,0,height,paint);

        paint.setColor(Color.rgb(0,112,102));
        for (Triangle t : trianglemodel.triangles){
            paint.setStyle(Paint.Style.FILL);
            if (t == controller.selected){
                canvas.save();
                canvas.rotate((float)(t.angle*57.2958),t.trueX,t.trueY);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.WHITE);
                canvas.drawRect(t.tx-t.width/2,t.ty-t.height/2,t.tx+t.width/2,t.ty+t.height/2,paint);
                canvas.drawRect(t.tx,t.ty,t.tx+1,t.ty+1,paint);

                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.YELLOW);
                canvas.drawOval(t.tx-t.width/2-radius,t.ty-t.height/2-radius,t.tx-t.width/2+radius,t.ty-t.height/2+radius,paint);
                paint.setColor(Color.GREEN);
                canvas.drawOval(t.tx+t.width/2-radius,t.ty+t.height/2-radius,t.tx+t.width/2+radius,t.ty+t.height/2+radius,paint);
                paint.setColor(Color.rgb(255,0,255));
                canvas.restore();
            }else{
                paint.setColor(Color.rgb(0,112,102));
            }
            canvas.save();
            canvas.rotate((float)(t.angle*57.2958),t.trueX,t.trueY);
            Path p = new Path();
            p.setFillType(Path.FillType.EVEN_ODD);
            p.rewind();
            p.moveTo(t.tx-t.width/2+(float)(t.width*t.p1x), t.ty-t.height/2+(float)(t.height*t.p1y));

            p.lineTo(t.tx-t.width/2+(float)(t.width*t.p2x),t.ty-t.height/2+(float)(t.height*t.p2y));
            p.lineTo(t.tx-t.width/2+(float)(t.width*t.p3x),t.ty-t.height/2+(float)(t.height*t.p3y));
            p.close();
            canvas.drawPath(p,paint);
            canvas.restore();
        }
        for (Triangle t : trianglemodel.triangles) {
            if (t == controller.selected) {
                paint.setColor(Color.WHITE);
                paint.setTextSize(24);
                canvas.drawText("TX: " + t.tx, 20, 25, paint);
                canvas.drawText("TY: " + t.ty, 20, 50, paint);
                canvas.drawText("SX: " + t.sx, 20, 75, paint);
                canvas.drawText("SY: " + t.sy, 20, 100, paint);
                canvas.drawText("A: " + t.angle, 20, 125, paint);

            }
        }
    }


    @Override
    public void modelChanged() {
        invalidate();
    }
}
