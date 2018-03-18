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
public class Vertex {
    double x,y;
    double radius = 15;
    int label;
    boolean selected = false;
    boolean drawEdge = false;
    public Vertex(double x1, double y1){
        x=x1;
        y=y1;
    }

    Vertex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public void setLabel(int l){
        label=l;
    }
    
    public boolean existVertex(double checkX, double checkY){
        if (distance(checkX,checkY)<radius){
            return true;
        }
        return false;
    }

    public boolean overlap(double checkX, double checkY){
        if (distance(checkX,checkY)<radius*2){
            return true;
        }
        return false;
    }
    
    private double distance(double cX, double cY){
        double dis = Math.sqrt((cX-x)*(cX-x) + (cY-y)*(cY-y));
        return dis;
    }
    
 
    
}
