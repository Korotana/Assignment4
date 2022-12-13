package com.example.assignment4.Model;

import com.example.assignment4.Blob;
import com.example.assignment4.Clipboard.TargetClipboard;
import com.example.assignment4.Command.CreateCommand;
import com.example.assignment4.Interface.AppModelListener;
import com.example.assignment4.Interface.TargetCommand;
import com.example.assignment4.Interface.IModelListener;
import com.example.assignment4.Trial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeoutException;

public class InteractionModel {
    List<IModelListener> subscribers;
    List<AppModelListener> appSubscribers;
    Stack<TargetCommand> undoStack, redoStack;

    ArrayList<Blob> createOrder;

    ArrayList<Trial> trials;

    Blob selected;
    TargetClipboard clipboard;
    String mode = "edit";
    String prevMode = "";

    private HashMap<Integer, ArrayList<Blob>> rubberBandSelections;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        appSubscribers = new ArrayList<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        clipboard = new TargetClipboard();
        createOrder = new ArrayList<>();
        trials = new ArrayList<>();
    }

    public void addSubscriber(IModelListener sub) {
        subscribers.add(sub);
    }

    public void addAppSubscriber(AppModelListener sub) {
        appSubscribers.add(sub);
    }

    private void notifySubscribers() {
        subscribers.forEach(s -> s.iModelChanged());
    }

    private void notifyAppSubscribers() {
        appSubscribers.forEach(sub -> {
            sub.viewChanged(prevMode);
        });
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
        prevMode = "viewchanged";
        notifyAppSubscribers();
    }


    public Blob getSelected() {
        return selected;
    }

    public void setSelected(Blob b) {
        selected = b;
        System.out.println(selected);
        this.rubberBandSelections = null;
        notifySubscribers();
    }

    public HashMap<Integer, ArrayList<Blob>> getRubberBandSelections() {
        return rubberBandSelections;
    }

    public void setRubberBandSelections(HashMap<Integer, ArrayList<Blob>> rubberBandSelections) {
        this.rubberBandSelections = rubberBandSelections;
        this.selected = null;
        notifySubscribers();
    }

    public void unselect() {
        selected = null;
    }

    public void addToUndoStack(TargetCommand bc) {
        undoStack.push(bc);
//        notifyStackSubscribers();
    }

    public void handleUndo() {
        if (undoStack.empty()) {
            System.out.println("Nothing more to undo!");
        } else {
            TargetCommand tc = undoStack.pop();
            tc.undo();
            redoStack.push(tc);
        }
    }

    public void handleRedo() {
        if (redoStack.empty()) {
            System.out.println("Nothing more to redo!");
        } else {
            TargetCommand tc = redoStack.pop();
            tc.doIt();
            undoStack.push(tc);
        }
    }

    public void copyToClipboard() {
        ArrayList<Blob> temp = new ArrayList<>();
        if (selected!=null) temp.add(selected);
        if (rubberBandSelections != null) {
            rubberBandSelections.forEach((integer, blobs) -> {
                blobs.forEach(blob -> temp.add(blob));
            });
        }else {
            System.out.println("NOTHING SELECTED TO COPY");
        }
        clipboard.copy(temp);
        System.out.println("COPIED " + temp.size() + " ITEMS");
    }

    public ArrayList<Blob> getClipBoard() {
        return this.clipboard.getBlobs();
    }

    public ArrayList<Blob> getCreateOrder() {
        return createOrder;
    }

    public void updateCreateOrder() {
        this.undoStack.forEach((targetCommand ->
        {
            if (targetCommand instanceof CreateCommand) {
                this.createOrder.add(((CreateCommand) targetCommand).myBlob);
            }
        }));

        for (int i = 0; i < createOrder.size(); i++) {
            trials.add(new Trial());
        }
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }

    public boolean checkTarget(Blob hit, Integer trialNum) {

        if (trialNum == 0) {
                this.trials.get(trialNum).startTimer();
                this.trials.get(trialNum).setBlob(hit);
                prevMode = "draw";
                notifyAppSubscribers();
                return true;
        } else {
            if (trialNum == this.trials.size()){
//                System.out.println("Lets go to reportView");
                mode = "report";
                notifyAppSubscribers();
            }
            else if (hit == this.createOrder.get(trialNum-1)) {
                this.trials.get(trialNum - 1).endTimer();
                this.trials.get(trialNum).startTimer();
                this.trials.get(trialNum).setBlob(this.createOrder.get(trialNum));
                this.trials.get(trialNum-1).calcDistance(this.trials.get(trialNum).getBlob());
                this.trials.get(trialNum-1).calcID();
                prevMode = "draw";
                notifyAppSubscribers();
                return true;
            }
        }
        return false;
    }
}


//    private void notifyStackSubscribers() {
//        stackSubscribers.forEach(sub -> {
//            sub.stackChanged(undoStack, "Undo");
//            sub.stackChanged(redoStack, "Redo");
//        });
//    }
//
//    public void addSubscriber (BoxModelListener aSub) {
//        subscribers.add(aSub);
//    }
//    public void addStackSubscriber (StackListener aSub) {
//        stackSubscribers.add(aSub);
//    }


