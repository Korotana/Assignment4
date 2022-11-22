package com.example.assignment4.View;

import com.example.assignment4.Controller.BlobController;
import com.example.assignment4.Interface.BlobModelListener;
import com.example.assignment4.Interface.IModelListener;
import com.example.assignment4.Model.BlobModel;
import com.example.assignment4.Model.InteractionModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BlobView extends StackPane implements BlobModelListener, IModelListener {
    GraphicsContext gc;
    Canvas myCanvas;
    BlobModel model;
    InteractionModel iModel;
    public double width=800, height=800;

    public BlobView() {
        myCanvas = new Canvas(width,height);
        gc = myCanvas.getGraphicsContext2D();
        this.setStyle("-fx-background-color: #acffff");
        this.getChildren().add(myCanvas);
    }

    private void draw() {
        gc.clearRect(0,0,myCanvas.getWidth(),myCanvas.getHeight());
        model.getBlobs().forEach(b -> {
            if (b == iModel.getSelected()) {
                gc.setFill(Color.TOMATO);
            } else {
                gc.setFill(Color.BEIGE);
            }
            gc.fillOval(b.x-b.r,b.y-b.r,b.r*2,b.r*2);
            gc.setFill(Color.BLACK);
            gc.fillText(Integer.toString(model.getBlobs().indexOf(b)),b.x-b.r + b.r,b.y-b.r + b.r);
        });

        model.rubberBandArrayList.forEach(band -> {
            gc.setStroke(Color.GREEN);
            gc.strokeRect(band.left,band.top,band.width,band.height);
        });

    }

    public void setModel(BlobModel newModel) {
        model = newModel;
    }

    public void setIModel(InteractionModel newIModel) {
        iModel = newIModel;
    }

    @Override
    public void modelChanged() {
        draw();

    }

    @Override
    public void iModelChanged() {
        draw();
    }

    public void setController(BlobController controller) {

        myCanvas.setOnMousePressed(controller::handlePressed);
        myCanvas.setOnMouseDragged(controller::handleDragged);
        myCanvas.setOnMouseReleased(controller::handleReleased);
    }
}
