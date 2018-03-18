/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.ArrayList;

/**
 *
 * @author Nan
 */
public class GraphModel {
    boolean shifted;
    int dragged = 0;
    boolean check = false;
    boolean release = false;
    ArrayList<GraphModelListener> subscribers;
    
    public GraphModel(){
        subscribers = new ArrayList<>();
    }
    
    
    public void addSubscriber(GraphModelListener aSubscriber) {
        subscribers.add(aSubscriber);
    }

    public void addCircle(double x, double y){
        shifted = false;
        release = true;
        check = false;
        dragged = 3;
        notifySubscribers(x,y,dragged,shifted,check,release);
    }


    
    public void selectCircle(double x, double y){
        dragged = 1;
        shifted = false;
        check = false;
        release = false;
        notifySubscribers(x,y,dragged,shifted,check,release);

    }
    
        
    public void dragCircle(double x, double y){
        dragged = 2;
        shifted = false;
        check = false;
        release = false;
        notifySubscribers(x,y,dragged,shifted,check,release);
    }
    
 
    public void addEdge(double x, double y){
        shifted = true;
        dragged = 0;
        check = false;
        release = false;
        notifySubscribers(x,y,dragged,shifted,check,release);
    }
    
    public void checkVertex(double x, double y){
        shifted = false;
        dragged = 0;
        check=true;
        release = false;
        notifySubscribers(x,y,dragged,shifted,check,release);
    }
    
    
    public void removeSubscriber(GraphModelListener aSubscriber) {
        subscribers.remove(aSubscriber);
    }

    private void notifySubscribers(double x, double y,int d,boolean s,boolean c,boolean r) {
        subscribers.forEach(sub -> sub.modelChanged(x,y,d,s,c,r));
    }
    
 
}
