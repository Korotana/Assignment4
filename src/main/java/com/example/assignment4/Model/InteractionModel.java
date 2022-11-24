package com.example.assignment4.Model;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.IModelListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InteractionModel {
    List<IModelListener> subscribers;
    Blob selected;

    private HashMap<Integer, ArrayList<Blob>> rubberBandSelections;

    public InteractionModel() {
        subscribers = new ArrayList<>();
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

}
