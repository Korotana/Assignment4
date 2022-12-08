package com.example.assignment4.Controller;

import com.example.assignment4.Blob;
import com.example.assignment4.Command.CreateCommand;
import com.example.assignment4.Command.DeleteCommand;
import com.example.assignment4.Command.MoveCommand;
import com.example.assignment4.Command.ResizeCommand;
import com.example.assignment4.Model.BlobModel;
import com.example.assignment4.Model.InteractionModel;
import javafx.scene.input.MouseEvent;



public class BlobController {
    BlobModel model;
    InteractionModel iModel;
    double prevX,prevY;
    double dX,dY,dR;
    double dragStartX, dragStartY, dragEndResize, dragEndResizeToMove, dragEndResizeY, dragEndMoveX, dragEndMoveY;;
    ResizeCommand rc;
    MoveCommand mc;

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
                    dragStartX = event.getX();
                    dragStartY = event.getY();
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
                    rc = new ResizeCommand(model,iModel.getSelected(),event.getX());
                    if (iModel.getSelected().r > 5.1){
                        dR = event.getX()-dragStartX;
                    }
                    dragEndResize = event.getX();
                    dragEndResizeY = event.getY();
                }else {
                    model.moveBlob(iModel.getSelected(), dX, dY);
                    mc = new MoveCommand(model,iModel.getSelected(), event.getX(), event.getY());
                    dragEndMoveX = event.getX();
                    dragEndMoveY = event.getY();
                }
                dragEndResizeToMove = event.getX();
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
                currentState = State.READY;
            }
            case DRAGGING -> {
//                iModel.unselect();
                double totalDX = event.getX() - dragStartX;
                double totalDY = event.getY() - dragStartY;
                if (rc != null) {
//                    System.out.println(dragEndMoveX);
//                    System.out.println(dragEndResizeToMove);
//                    System.out.println(dragEndResize);
//                    System.out.println("==================");
                    if (dragEndResize == dragEndResizeToMove && dR != 0) {
                        rc.setDr(dR);
                        iModel.addToUndoStack(rc);
                        rc = null;
                        dR = 0;
                    }
                    else if (dragEndResize != dragEndResizeToMove && Math.abs(dragEndResizeToMove - event.getX()) < 1.1){
                            //When doing resize and shift is released to go into move state
                            System.out.println("DID RESIZE TO MOVE SIMULTANEOUSLY");
                            rc.setDr(dragEndResize - dragStartX);
                            iModel.addToUndoStack(rc);
                            totalDX = event.getX() - dragEndResize;
                            totalDY = event.getY() - dragEndResizeY;
                            MoveCommand mc = new MoveCommand(model, iModel.getSelected(), totalDX, totalDY);
                            iModel.addToUndoStack(mc);
                    }else if (dragEndMoveX != dragEndResizeToMove && Math.abs(dragEndResizeToMove - event.getX()) < 1.1){
                        System.out.println("DID MOVE TO RESIZE SIMULTANEOUSLY");
                        totalDX = dragEndMoveX - dragStartX;
                        totalDY = dragEndMoveY - dragStartY;
                        MoveCommand mc = new MoveCommand(model, iModel.getSelected(), totalDX, totalDY);
                        iModel.addToUndoStack(mc);
                        rc.setDr(dragEndResize-dragEndMoveX);
                        iModel.addToUndoStack(rc);
                    }

                } else {
                        MoveCommand mc = new MoveCommand(model, iModel.getSelected(), totalDX, totalDY);
                        iModel.addToUndoStack(mc);
                }
                currentState = State.READY;
            }
        }
    }

    public void handleDeleteKeyPressed() {
        System.out.println("In Del");
        if (iModel.getSelected() != null) {
            DeleteCommand dc = new DeleteCommand(model, iModel.getSelected(),iModel.getSelected().x, iModel.getSelected().y);
            dc.doIt();
            iModel.addToUndoStack(dc);
        }
//        if (iModel.getRubberBandSelections() != null) model.deleteMultipleBlob(iModel.getRubberBandSelections());
        else System.out.println("NOTHING SELECTED TO DELETE");

    }

    public void handleUndo() {iModel.handleUndo();}

    public void handleRedo() {iModel.handleRedo();}


}

