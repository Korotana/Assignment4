package com.example.assignment4.Model;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.TargetCommand;
import com.example.assignment4.Interface.IModelListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class InteractionModel {
    List<IModelListener> subscribers;
    Stack<TargetCommand> undoStack, redoStack;
    Blob selected;

    private HashMap<Integer, ArrayList<Blob>> rubberBandSelections;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void addSubscriber(IModelListener sub) {
        subscribers.add(sub);
    }

    private void notifySubscribers() {
        subscribers.forEach(s -> s.iModelChanged());
    }

    public Blob getSelected() {
        return selected;
    }

    public void setSelected(Blob b) {
        selected = b;
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
