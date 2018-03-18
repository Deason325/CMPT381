/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Nan
 */
public class GraphDemo extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        GraphView view = new GraphView(500);
        GraphModel model = new GraphModel();
        GraphViewController controller = new GraphViewController();
        MiniView miniView = new MiniView(2000); 
        InteractionModel intModel = new InteractionModel();
        MiniViewController mController = new MiniViewController();
        
        view.setModel(model);
        controller.setModel(model);
        model.addSubscriber(view);
        
        miniView.setModel(model);
        model.addSubscriber(miniView);
        miniView.setView(view);
        
        VBox root = new VBox();
        StackPane sp = new StackPane();
        sp.getChildren().addAll(view,miniView);
        sp.setAlignment(view,Pos.TOP_LEFT);
        sp.setAlignment(miniView,Pos.TOP_LEFT);
        miniView.setPickOnBounds(false);
        root.getChildren().addAll(sp);
        Scene scene = new Scene(root);
        
        scene.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.SHIFT){
                view.setOnMouseReleased(controller::handleShiftClicked);
            }
        });

        scene.setOnKeyReleased(e->{
            if(e.getCode() == KeyCode.SHIFT){
                view.setOnMouseReleased(controller::handleMouseReleased);
                
            }
        });
        
       // view.setOnMouseClicked(controller::handleMouseClicked);
       view.setOnMousePressed(controller::checkVertex);
      
        view.setOnMouseReleased(controller::handleMouseReleased);
        view.setOnDragDetected(controller::handleDragDetected);
        view.setOnMouseDragged(controller::handleMouseDraged);
        miniView.setOnMouseDragged(controller::handleMouseDraged);
        miniView.setOnDragDetected(controller::handleDragDetected);
       
        primaryStage.setScene(scene);
        primaryStage.show();
        view.iniDraw();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
