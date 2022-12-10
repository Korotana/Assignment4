package com.example.assignment4.Model;

import com.example.assignment4.Blob;
import com.example.assignment4.Clipboard.TargetClipboard;
import com.example.assignment4.Command.CreateCommand;
import com.example.assignment4.Interface.AppModelListener;
import com.example.assignment4.Interface.TargetCommand;
import com.example.assignment4.Interface.IModelListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class InteractionModel {
    List<IModelListener> subscribers;
    List<AppModelListener> appSubscribers;
    Stack<TargetCommand> undoStack, redoStack;

    ArrayList<Blob> createOrder;

    Blob selected;
    TargetClipboard clipboard;
    String mode;

    private HashMap<Integer, ArrayList<Blob>> rubberBandSelections;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        appSubscribers = new ArrayList<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        clipboard = new TargetClipboard();
    }

    public void addSubscriber(IModelListener sub) {
        subscribers.add(sub);
    }

    public void addAppSubscriber(AppModelListener sub) {appSubscribers.add(sub);}

    private void notifySubscribers() {
        subscribers.forEach(s -> s.iModelChanged());
    }

    private void notifyAppSubscribers(){
        appSubscribers.forEach(sub -> {sub.viewChanged(mode);});
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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
        temp.add(selected);
        clipboard.copy(temp);

    }

    public ArrayList<Blob> getClipBoard() {
        return this.clipboard.getBlobs();
    }

    public ArrayList<Blob> getCreateOrder() {
        return createOrder;
    }

    public void updateCreateOrder()
    {
        this.undoStack.forEach((targetCommand ->
        {
            System.out.println(targetCommand instanceof CreateCommand);
            if (targetCommand instanceof CreateCommand){
                this.createOrder.add(((CreateCommand) targetCommand).myBlob);
            }
        }));

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

}
