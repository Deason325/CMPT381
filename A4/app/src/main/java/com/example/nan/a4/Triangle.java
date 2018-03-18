package com.example.nan.a4;

import android.util.Log;

/**
 * Created by Nan on 2018-03-15.
 */

public class Triangle {
    float trueX,trueY;
    float radius = 25;
    float tx,ty;
    float sx,sy;
    float angle=0;
    float width,height;
    double p1x,p2x,p3x,p1y,p2y,p3y;
    float iniW;
    float iniH;
    public Triangle(float x, float y,float width, float height,double p1x,double p2x, double p3x,double p1y, double p2y, double p3y){
        this.trueX=x;
        this.trueY=y;
        this.tx=x;
        this.ty=y;
        this.sx=1;
        this.sy=1;
        this.angle=0;
        this.width=width;
        this.height=height;
        this.p1x=p1x;
        this.p2x=p2x;
        this.p3x=p3x;
        this.p1y=p1y;
        this.p2y=p2y;
        this.p3y=p3y;
        this.iniW=width;
        this.iniH=height;
    }

    public boolean contains(float posX, float posY) {
        double p1X = tx-width/2+(float)(width*p1x);
        double p1Y = ty-height/2+(float)(height*p1y);
        double p2X = tx-width/2+(float)(width*p2x);
        double p2Y = ty-height/2+(float)(height*p2y);
        double p3X = tx-width/2+(float)(width*p3x);
        double p3Y = ty-height/2+(float)(height*p3y);

        double A = Math.abs(p1X*(p2Y-p3Y) + p2X*(p3Y-p1Y) + (p3X*(p1Y-p2Y)))*0.5;
        double B = Math.abs(posX*(p2Y-p3Y) + p2X*(p3Y-posY) + (p3X*(posY-p2Y)))*0.5;
        double C = Math.abs(p1X*(posY-p3Y) + posX*(p3Y-p1Y) + (p3X*(p1Y-posY)))*0.5;
        double D = Math.abs(p1X*(p2Y-posY) + p2X*(posY-p1Y) + (posX*(p1Y-p2Y)))*0.5;


        if (Math.abs((A-(B+C+D)))<1){
            return true;
        }
        return false;
    }

    public boolean containsYellow(float posX, float posY){
        if (posX>tx-width/2-radius && posX<tx-width/2+radius && posY<ty-height/2+radius && posY>ty-height/2-radius){
            return true;
        }
        return false;
    }

    public boolean containsGreen(float posX, float posY){
        System.out.println((tx+width/2-radius  ) + " "+(tx+width/2+radius)  + " "+(ty+height/2+radius) + " "+(ty+height/2-radius));
        if (posX>tx+width/2-radius && posX<tx+width/2+radius && posY<ty+height/2+radius && posY>ty+height/2-radius){
            return true;
        }
        return false;
    }

    public void setTranslate(float tX, float tY){
        this.trueX=tx;
        this.trueY=tY;
        this.tx=tX;
        this.ty=tY;
    }

    public void setScale(double sX, double sY){
        this.width=(float)(tx-sX)*2;
        this.height=(float)(ty-sY)*2;
        this.sx = Math.abs(width/iniW);
        this.sy = Math.abs(height/iniH);

    }


    public void setAngle(float radians){
       // tx = (float)(Math.cos(radians)*tx - Math.sin(radius)*ty);
        //ty = (float)(Math.sin(radians)*tx + Math.cos(radius)*ty);

        this.angle=radians;
    }
}
