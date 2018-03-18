/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import static a3.GraphView.vertexes;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author Nan
 */
public class MiniView extends Pane implements GraphModelListener{
    Canvas canvas;
    GraphicsContext gc;
    GraphModel model;
    GraphViewController gvc;
               GraphView view;
               
               double fromX;
    double fromY;
    Vertex previous;
            boolean exist = false;
     

    double ratio=0.2;
    double viewWieght = this.getWidth();
    double viewHeight = this.getHeight();
    public MiniView(int size){
        canvas = new Canvas(100,100);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, size, size);
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(0, 0, 50, 50);
         
    }
   
    public void setModel(GraphModel model){
        this.model  = model;
    }
    public void setView(GraphView view){
        this.view = view;
    }
    public void setController(GraphViewController controller){
        this.gvc = controller;
    }
    
    public void draw(double x, double y,boolean release,int drag){
        if(release){
            exist = false;
        }
        
        gc.setFill(Color.ALICEBLUE);
        gc.fillRect(0, 0, 100, 100);
        if(view.offsetX*ratio-20 < 0){
                    gc.setFill(Color.GREENYELLOW);
        gc.fillRect(0, view.offsetY*ratio-20, 40, 40);
        }else if(view.offsetX*ratio-20 > 80){
                    gc.setFill(Color.GREENYELLOW);
        gc.fillRect(80, view.offsetY*ratio-20, 40, 40);
        }else if(view.offsetY*ratio-20<0){
            gc.setFill(Color.GREENYELLOW);
        gc.fillRect(view.offsetX*ratio-20, 0, 40, 40);
        }else if(view.offsetY*ratio-20>80){
            gc.setFill(Color.GREENYELLOW);
        gc.fillRect(view.offsetX*ratio-20, 80, 40, 40);
        }else{
            gc.setFill(Color.GREENYELLOW);
            gc.fillRect(view.offsetX*ratio-20, view.offsetY*ratio-20, 40, 40);
        }

        for(Edge e : view.edges1){
            gc.strokeLine(e.start.x*ratio-e.start.radius+3, e.start.y*ratio-e.start.radius+3, 
                    e.end.x*ratio-e.start.radius+3, e.end.y*ratio-e.start.radius+3);
        }
      
        if(drag==1){
            for(Vertex v1 : view.vertexes1){
                if(v1.existVertex(x, y)){
                    exist = true;
                        fromX=v1.x;
                        fromY=v1.y;
                      previous=v1;
                }
            }
       
        }
            if(exist && previous.drawEdge){
                gc.strokeLine(fromX*ratio-12,fromY*ratio-12,x*ratio-12,y*ratio-12);
            }
           for(Vertex v : view.vertexes1){
            if(v.selected){
                  gc.setFill(Color.ORANGE);
           gc.fillOval(v.x*ratio-v.radius, v.y*ratio-v.radius, v.radius*2*ratio, v.radius*2*ratio);
           gc.setFill(Color.BLACK);
           gc.strokeOval(v.x*ratio-v.radius, v.y*ratio-v.radius, v.radius*2*ratio, v.radius*2*ratio);
            }else if(v.drawEdge){
           gc.setFill(Color.AQUA);
           gc.fillOval(v.x*ratio-v.radius, v.y*ratio-v.radius, v.radius*2*ratio, v.radius*2*ratio);
           gc.setFill(Color.BLACK);
           gc.strokeOval(v.x*ratio-v.radius-1.5, v.y*ratio-v.radius-1.5, v.radius*2*ratio+3, v.radius*2*ratio+3);
            }else{
                 gc.setFill(Color.AQUA);
           gc.fillOval(v.x*ratio-v.radius, v.y*ratio-v.radius, v.radius*2*ratio, v.radius*2*ratio);
           gc.setFill(Color.BLACK);
           gc.strokeOval(v.x*ratio-v.radius, v.y*ratio-v.radius, v.radius*2*ratio, v.radius*2*ratio);
            }
          
        }
         
       
    }

    @Override
    public void modelChanged(double x, double y, int d, boolean s, boolean c, boolean r) {
        draw(x,y,r,d);
    }
    
    
    
    
}
