/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.util.Objects;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Nan
 */
public class JavaFXApplication2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Double min1 = 100.0;
        Double max1 = 500.0;
        Double initial1 = 250.0;
        
        Double min2 = 0.0;
        Double max2 = 200.0;
        Double initial2 = 200.0;
        
        Double min3 = 100.0;
        Double max3 = 500.0;
        Double initial3 = 100.0;
        
        
        
        Button btn = new Button();
        Label label = new Label("s");
        Slider sl = new Slider();
        VBox vbox = new VBox(8);
        HBox hbox = new HBox(8);
        
        
        btn.setText("Quit");
        sl.setMin(150.0);
        sl.setMax(1000.0);
        sl.setValue(275.0);
        String num = Double.toString(sl.getValue());
        label.setText(num);
        label.setFont(Font.font(24));
      
        sl.valueProperty().addListener((observable, oldValue, newValue)->{
            
            label.setText(Double.toString(Math.round(newValue.floatValue()*10.0)/10.0));
            
    });
        
        
        Label label1 = new Label("");
        BarControl bc1 = new BarControl(min1,max1,initial1,label1);
        Label label2 = new Label("");
        BarControl bc2 = new BarControl(min2,max2, initial2,label2);
        
        Label label3 = new Label("");
        BarControl3 bc3 = new BarControl3(min3,max3,initial3,label3);
       
        
        hbox.getChildren().addAll(bc1,label1, bc2, label2, bc3, label3);
        
        vbox.getChildren().addAll(hbox, sl,label,btn);

        //listener, action on listen
 
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Goodbye!");
            primaryStage.close();
        });
        
      
        
     
        Scene scene = new Scene(vbox, 500, 350);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    class BarControl extends Pane{
        
        public BarControl(Double min, Double max, Double initial, Label x){
            
          
            Canvas widgetCanvas = new Canvas(50,200);
            GraphicsContext gc = widgetCanvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, 50, 200);
            
            Proper p = new Proper();
            p.amountProperty().addListener(new ChangeListener(){
                @Override public void changed(ObservableValue o, Object oldVal, Object newVal){
                      x.textProperty().bind(Bindings.convert(p.amountProperty()));
                }
            });
           
            
            
            
            
            GraphicsContext gc1 = widgetCanvas.getGraphicsContext2D();
            gc1.setFill(Color.YELLOW);
            
            if(Objects.equals(initial, min)){
                gc1.fillRect(5,199,40,1);
                x.setText(Double.toString(initial));

            }else{
            gc1.fillRect(5, (1-(initial-min)/(max-min))*200, 40, initial);
            x.setText(Double.toString(initial));
            }
            
            widgetCanvas.setOnMouseClicked(e->{
                Double yV;
                if(e.getY()>200){
                    yV=200.0;
                }else if(e.getY()<0){
                    yV=0.0;
                }else{
                    yV=e.getY();
                }
                if(yV==0){
                    x.setText(Double.toString(min+0.0));
                    gc1.fillRect(5,199.5,40,0.5);
                    p.setAmount(0.0);
                }else{
                    p.setAmount((Math.round((min+(1-yV/200) 
                        * (max-min))*10.0)/10.0));
                gc1.setFill(Color.BLACK);
                gc1.fillRect(0, 0, 50, 200);
                gc1.setFill(Color.YELLOW);
                gc1.fillRect(5, yV, 40, (max-yV));
                }
            
            });     
            
           
            
            widgetCanvas.setOnMouseDragged(e->{
                Double yV;
                if(e.getY()>200){
                    yV=200.0;
                }else if(e.getY()<0){
                    yV=0.0;
                }else{
                    yV=e.getY();
                }
               if(yV==200){
                    x.setText(Double.toString(min+0.0));
                    gc1.fillRect(5,199.5,40,0.5);
                    p.setAmount(0.0);
                }else{
                p.setAmount((Math.round((min+(1-yV/200) 
                        * (max-min))*10.0)/10.0));
                gc1.setFill(Color.BLACK);
                gc1.fillRect(0, 0, 50, 200);
                gc1.setFill(Color.YELLOW);
                
                gc1.fillRect(5, yV, 40, (max-yV));
                }
            });

            this.getChildren().add(widgetCanvas); 
        }
          
    }
    class BarControl3 extends Pane{
        
        public BarControl3(Double min, Double max, Double initial, Label x){
            Canvas widgetCanvas = new Canvas(50,200);
            GraphicsContext gc = widgetCanvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, 50, 200);
            
            GraphicsContext gc1 = widgetCanvas.getGraphicsContext2D();
            gc1.setFill(Color.YELLOW);
            
            if(Objects.equals(initial, min)){
                gc1.fillRect(5,199,40,1);
                x.setText(Double.toString(initial));

            }else{
            gc1.fillRect(5, (1-(initial-min)/(max-min))*200, 40, initial);
            x.setText(Double.toString(initial));
            }
            widgetCanvas.setOnMouseClicked(e->{
                Double yV;
                if(e.getY()>200){
                    yV=200.0;
                }else if(e.getY()<0){
                    yV=0.0;
                }else{
                    yV=e.getY();
                }
                if(yV==0){
                    x.setText(Double.toString(min+0.0));
                    gc1.fillRect(5,199.5,40,0.5);
                }else{
                gc1.setFill(Color.BLACK);
                gc1.fillRect(0, 0, 50, 200);
                gc1.setFill(Color.YELLOW);
                gc1.fillRect(5, yV, 40, (max-yV));
               
                x.setText(Double.toString(Math.round((min+(1-yV/200) 
                        * (max-min))*10.0)/10.0));
                }
            
            });     
            
           
            
            widgetCanvas.setOnMouseDragged(e->{
                Double yV;
                if(e.getY()>200){
                    yV=200.0;
                }else if(e.getY()<0){
                    yV=0.0;
                }else{
                    yV=e.getY();
                }
               if(yV==200){
                    x.setText(Double.toString(min+0.0));
                    gc1.fillRect(5,199.5,40,0.5);
                }else{
                gc1.setFill(Color.BLACK);
                gc1.fillRect(0, 0, 50, 200);
                gc1.setFill(Color.YELLOW);
                gc1.fillRect(5, yV, 40, (max-yV));
                x.setText(Double.toString(Math.round((min+(1-yV/200) 
                        * (max-min))*10.0)/10.0));
                }
            });

            this.getChildren().add(widgetCanvas); 
        }
    }
    
    class Proper{
        private DoubleProperty amount = new SimpleDoubleProperty();
        private Double num;
        
        public double getNum(){
            return this.num;
        }
        public final double getAmount(){
            return amount.get();
        }
        public final void setAmount(double value){
            amount.set(value);
        }
        public DoubleProperty amountProperty(){
            return amount;
        }
        
    }
}
