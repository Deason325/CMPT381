package com.example.nan.a4;

import java.util.ArrayList;

/**
 * Created by Nan on 2018-03-15.
 */

public class TriangleModel {
    ArrayList<Triangle> triangles;
    ArrayList<TriangleModelListener> subscribers;
    Triangle t1 = new Triangle(300,250,300,300,0.3,1,0,0,0.5,1);
    Triangle t2 = new Triangle(120,750,150,100,0,1,0.5,0,0.2,1);
    Triangle t3 = new Triangle(500,500,200,200,0,0.5,1,0.5,0,1);

    public TriangleModel(){
        triangles = new ArrayList<Triangle>();
        subscribers = new ArrayList<TriangleModelListener>();
        triangles.add(t1);
        triangles.add(t2);
        triangles.add(t3);
    }

    public void addSubscriber(TriangleModelListener subs){
        subscribers.add(subs);
    }

    private void notifySubscribers(){
        for (TriangleModelListener listener : subscribers){
            listener.modelChanged();
        }
    }

    public boolean contains(float x, float y){
        for (Triangle t : triangles){
            if (t.contains(x,y)){
                return true;
            }
        }
        return false;
    }

    public boolean containsYellow(float x, float y, Triangle t){
            if (t.containsYellow(x, y)) {
                return true;
            }
        return false;
    }

    public boolean containsGreen(float x, float y, Triangle t){
        if (t.containsGreen(x, y)) {
            return true;
        }
        return false;
    }

    public Triangle findClick(float x, float y){
        Triangle selected = null;
        for (Triangle t : triangles){
            if (t.contains(x,y)){
                selected = t;
            }
        }
        return selected;
    }
}
