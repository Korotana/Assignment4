package com.example.assignment4.Controller;

import com.example.assignment4.Blob;
import com.example.assignment4.Command.CreateCommand;
import com.example.assignment4.Model.BlobModel;
import com.example.assignment4.Model.InteractionModel;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

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
                    CreateCommand cc = new CreateCommand(model,event.getX(),event.getY());
                    cc.doIt();
                    iModel.addToUndoStack(cc);
                    iModel.setSelected(model.whichHit(event.getX(),event.getY()));
                }else {
                    iModel.setRubberBandSelections(model.createRubberBand(event.getX(), event.getY(), true));
                    iModel.unselect();
                }
//                System.out.println("--------111----"+iModel.rubberBandSelections.get(1));
//                System.out.println("-----22-----"+iModel.rubberBandSelections.get(2));
                currentState = State.READY;
            }
            case DRAGGING -> {
//                iModel.unselect();
                currentState = State.READY;
            }
        }
    }

    public void handleDeleteKeyPressed() {
        System.out.println("In Del");
        if (iModel.getSelected() != null) model.deleteBlob(iModel.getSelected());
        if (iModel.getRubberBandSelections() != null) model.deleteMultipleBlob(iModel.getRubberBandSelections());
        else System.out.println("NOTHING SELECTED TO DELETE");

    }

    public void handleUndo() {iModel.handleUndo();}

    public void handleRedo() {iModel.handleRedo();}


}

