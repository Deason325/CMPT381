/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 *
 * @author Nan
 */
public class GraphView extends Pane implements GraphModelListener{
    public static ArrayList<Vertex> vertexes;
    ArrayList<Vertex> vertexes1;
    
    public ArrayList<Edge> edges;
    ArrayList<Edge> edges1;
    Canvas canvas;
    GraphicsContext gc;
    GraphModel model;
    int numVertex = 0;
    boolean readyToEdge = false;
    boolean canDraw = true;
    boolean b = false;
    double fromX;
    double fromY;
    Vertex previous;
    Vertex previous1;
    Vertex dragging;
    Vertex dragging1;
    boolean has;
    boolean moved;
    int size;
    double startX;
    double endX;
    double oldX;
    double oldY;
    double startY;
    double endY;
    double offsetX;
    double offsetY;
    int count;
    public GraphView(int size){
        canvas = new Canvas(500,500);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        vertexes = new ArrayList<>();
        vertexes1=new ArrayList<>();
        edges = new ArrayList<>();
        edges1 = new ArrayList<>();
        readyToEdge = false;
        this.size=size;
    }
    
    void setModel(GraphModel Amodel) {
        model = Amodel;
    }
    
    public void iniDraw(){
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, size, size);
        
    }
    public ArrayList getList(){
        return this.vertexes1;
    }
    public void shiftClick(Vertex v, double x, double y){
        boolean exist = false;
            for(Vertex v1 : vertexes){
                if(v1.existVertex(x, y)){
                    exist = true;
                    v=v1;
                }
            }
            for(Vertex v1 : vertexes1){
                if(v1.existVertex(x, y)){
                    exist = true;
                    previous1=v1;
                }
            }
            if(exist){ 
                previous = v;
              
                previous.drawEdge=true;
                previous1.drawEdge=true;
                gc.setFill(Color.AQUA);
                gc.fillOval(v.x-v.radius, v.y-v.radius, v.radius*2, v.radius*2);
                gc.setFill(Color.BLACK);
                gc.strokeOval(v.x-v.radius-1.5, v.y-v.radius-1.5, v.radius*2+3, v.radius*2+3);
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(v.label), v.x-4, v.y+3);
                readyToEdge = true;
                canDraw = false;
                fromX = v.x;
                fromY = v.y;
            }
    }
    
    public void drawEdge(int dragged, Vertex v, double x, double y, int which){
         if(dragged == 1){
                
            }else if (dragged==2){
                gc.setFill(Color.GRAY);
                gc.fillRect(0, 0, size, size);
                gc.setLineWidth(2);
                gc.strokeLine(fromX,fromY,x,y);
                gc.setLineWidth(1);
            }else if(dragged == 3){
                v.drawEdge=false;
                gc.setFill(Color.GRAY);
                gc.fillRect(0, 0, size, size);
                
                
                if(which==0){
                    boolean exist = false;
                for(Vertex v1 : vertexes){
                    if(v1.existVertex(x, y)){
                    exist = true;
                    v=v1;
                    }
                }
                    if(exist){
                    for(Vertex v1 : vertexes){
                        if (v1.x==fromX && v1.y==fromY){
                            Edge e = new Edge(v1,v);
                            edges.add(e);
                            v1.drawEdge=false;
                            readyToEdge = false;
                        }
                    }
                }
                }
                if(which==1){
                    boolean exist = false;
                for(Vertex v1 : vertexes1){
                    if(v1.existVertex(x, y)){
                    exist = true;
                    v=v1;
                    }
                }
                    if(exist){
                    for(Vertex v1 : vertexes1){
                        if (v1.x==fromX && v1.y==fromY){
                            Edge e = new Edge(v1,v);
                            edges1.add(e);
                            v1.drawEdge=false;
                            readyToEdge = false;
                        }
                    }
                }
                }
                
            if(b){
                canDraw=true;
                readyToEdge = false;
               
                b=false;
            }
            if(!canDraw){
                b=true;
                previous.drawEdge=false;
                previous1.drawEdge=false;
                gc.setFill(Color.GREY);
                gc.fillOval(previous.x-v.radius-2, previous.y-v.radius-2, v.radius*2+4, v.radius*2+4);
                gc.setFill(Color.AQUA);
                gc.fillOval(previous.x-v.radius, previous.y-v.radius, v.radius*2, v.radius*2);
                gc.setFill(Color.BLACK);
                gc.strokeOval(previous.x-v.radius, previous.y-v.radius, v.radius*2, v.radius*2);
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(previous.label), previous.x-4, previous.y+3);
            }
        }
    }
    
    public void reDraw(){
        for(Edge e : edges){
            gc.strokeLine(e.start.x, e.start.y, e.end.x, e.end.y);
        } 
        for(Vertex v1 : vertexes){
                if(v1.selected){
                gc.setFill(Color.ORANGE);
                gc.fillOval(v1.x-v1.radius, v1.y-v1.radius, v1.radius*2, v1.radius*2);
                gc.setFill(Color.BLACK);
                gc.strokeOval(v1.x-v1.radius, v1.y-v1.radius, v1.radius*2, v1.radius*2);
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(v1.label), v1.x-4, v1.y+3);
                }else if(v1.drawEdge){
                gc.setFill(Color.AQUA);
                gc.fillOval(v1.x-v1.radius, v1.y-v1.radius, v1.radius*2, v1.radius*2);
                gc.setFill(Color.BLACK);
                gc.strokeOval(v1.x-v1.radius-1.5, v1.y-v1.radius-1.5, v1.radius*2+3, v1.radius*2+3);
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(v1.label), v1.x-4, v1.y+3);
                }else{
                gc.setFill(Color.AQUA);
                gc.fillOval(v1.x-v1.radius, v1.y-v1.radius, v1.radius*2, v1.radius*2);
                gc.setFill(Color.BLACK);
                gc.strokeOval(v1.x-v1.radius, v1.y-v1.radius, v1.radius*2, v1.radius*2);
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(v1.label), v1.x-4, v1.y+3);
                }
        }
    }
    
    public void draw(double x, double y, int dragged, boolean shifted,boolean pressed,boolean release){
         Vertex v = new Vertex(x,y);
         //if mouse pressed, check if there is a vertex
        if(pressed){
            for(Vertex v1:vertexes){
                if(v1.existVertex(x, y)){
                    v=v1;
                    has = true;
                }
            }
        }
        if(!shifted && !readyToEdge){
            if(!has && dragged == 2){
            moved = true;
            }
        //if there is no vertex and mouse released and mouse has not been dragged, create new vertex
            if(!has && release){
                if(!moved){
                    v.setLabel(numVertex);
                    vertexes.add(v);
                    Vertex va = new Vertex(x-offsetX,y-offsetY);
                    va.setLabel(numVertex);
                    vertexes1.add(va);
                    numVertex += 1;
                }
            }
            if(dragged == 1){
               startX = x;
               startY = y;
             
            }
            
            if(!has && dragged == 2){
                
                if(count<1){
                    oldX=x;
                    oldY=y;
                    count++;
                }
                endX=x;
                endY=y;
                 double xMoved=0;
                 double yMoved=0;
                gc.setFill(Color.GRAY);
                gc.fillRect(0, 0, size, size);
            
                     xMoved = endX-oldX;
                    yMoved = endY-oldY;
                
                for(Vertex vv : vertexes){
                    vv.x+=xMoved;
                    vv.y+=yMoved;
                }
              
                oldX=x;
                oldY=y;
                //background
               /* this.setTranslateX(this.getTranslateX()+x);
            this.setTranslateY(this.getTranslateY()+y);*/
              //offset
              
            }
            if(!has && dragged == 3 && moved){   
                offsetX += endX-startX;
                offsetY += endY-startY;
                count=0;
                moved = false;
            }
            //if there is a vertex, 
            if(has){
            for(Vertex v1:vertexes){
                if(v1.existVertex(x, y)){
                    dragging=v1;
                }
            }
            
            for(Vertex v1:vertexes1){
                if(v1.existVertex(x, y)){
                    dragging1=v1;
                }
            }
            
            boolean ableToDraw = true;
            for(Vertex v1 : vertexes){
                if(v1.existVertex(x, y)){
                    ableToDraw = false;
                }
            //on drag detected
            if (dragged==1){
                dragging.selected = true;
                dragging1.selected=true;
            }else if(dragged==2){   //on dragging
                dragging.x = x;
                dragging.y = y;
                dragging1.x=x;
                dragging1.y=y;
            }else if (dragged == 3){    //on drag released
                dragging.selected = false;
                dragging1.selected=false;
                has = false;
                moved = false;
            }
            gc.setFill(Color.GRAY);
            gc.fillRect(0, 0, size, size);
            }  
        }
    }else if(shifted){
            shiftClick(v,x,y);
        }else if(readyToEdge){
            drawEdge(dragged, v, x, y,0);
            drawEdge(dragged, dragging1,x,y,1);
        }
        reDraw();
    }
    
    public void modelChanged(double x,double y, int d,boolean s, boolean c,boolean r){
        draw(x,y,d,s,c,r);
    }

}
