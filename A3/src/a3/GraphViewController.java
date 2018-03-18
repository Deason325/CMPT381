/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.ArrayList;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Nan
 */
public class GraphViewController {
    GraphModel model;
    double offsetX,offsetY;
    void setModel(GraphModel Amodel) {
        model = Amodel;
    }

    void handleMouseReleased(MouseEvent event) {
        model.addCircle(event.getX(),event.getY());

    }
    
    void handleMouseDraged(MouseEvent event){
        model.dragCircle(event.getX(),event.getY());
    }
    
    void handleDragDetected(MouseEvent event){
        model.selectCircle(event.getX(),event.getY());
    }
    void handleShiftClicked(MouseEvent event){
        model.addEdge(event.getX(),event.getY());
    }
    
    void checkVertex(MouseEvent event){
        model.checkVertex(event.getX(),event.getY());
    }

    
}
