package com.example.assignment4.Controller;

import com.example.assignment4.Blob;
import com.example.assignment4.Model.BlobModel;
import com.example.assignment4.Model.InteractionModel;
import javafx.scene.input.MouseEvent;

public class BlobController {
    BlobModel model;
    InteractionModel iModel;
    double prevX,prevY;
    double dX,dY;

    public boolean isShiftDown = false;

    enum State {READY,PREPARE_CREATE, DRAGGING}
    State currentState = State.READY;

    public BlobController() {
    }

    public void setModel(BlobModel newModel) {
        model = newModel;
    }

    public void setIModel(InteractionModel newIModel) {
        iModel = newIModel;
    }

    public void handlePressed(MouseEvent event) {
        switch (currentState) {
            case READY -> {
                if (model.hitBlob(event.getX(),event.getY())) {
                    Blob b = model.whichHit(event.getX(),event.getY());
                    iModel.setSelected(b);
                    prevX = event.getX();
                    prevY = event.getY();
                    currentState = State.DRAGGING;
                } else {
                    model.rubberLeft = event.getX();
                    model.rubberTop = event.getY();
                    currentState = State.PREPARE_CREATE;
                }
            }
        }
    }

    public void handleDragged(MouseEvent event) {
        dX = event.getX() - prevX;
        dY = event.getY() - prevY;
        prevX = event.getX();
        prevY = event.getY();
        switch (currentState) {
            case PREPARE_CREATE -> {
                if (isShiftDown) currentState = State.READY;
                model.createRubberBand(event.getX(), event.getY(), false);
            }
            case DRAGGING -> {
//                dX = event.getX() - prevX;
//                dY = event.getY() - prevY;
//                prevX = event.getX();
//                prevY = event.getY();

                if (isShiftDown){
                    model.resizeBlob(iModel.getSelected(),dX);
                }else {
                    model.moveBlob(iModel.getSelected(), dX, dY);
                }
            }
        }
    }

    public void handleReleased(MouseEvent event) {
        switch (currentState) {
            case PREPARE_CREATE -> {
                if (isShiftDown) {
                    model.addBlob(event.getX(),event.getY());
                    iModel.setSelected(model.whichHit(event.getX(),event.getY()));
                }else {
                    iModel.unselect();
                }
                currentState = State.READY;
            }
            case DRAGGING -> {
//                iModel.unselect();
                model.createRubberBand(event.getX(), event.getY(), true);
                currentState = State.READY;
            }
        }
    }

    public void handleDeleteKeyPressed() {
        System.out.println("In Del");
        System.out.println(iModel.getSelected());
        if (iModel.getSelected() != null) model.deleteBlob(iModel.getSelected());
        else System.out.println("NOTHING SELECTED TO DELETE");

    }

}

