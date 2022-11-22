package com.example.assignment4.Model;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.IModelListener;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel {
    List<IModelListener> subscribers;
    Blob selected;

    public InteractionModel() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(IModelListener sub) {
        subscribers.add(sub);
    }

    private void notifySubscribers() {
        subscribers.forEach(s -> s.iModelChanged());
    }

    public void setSelected(Blob b) {
        selected = b;
        notifySubscribers();
    }

    public void unselect() {
        selected = null;
    }

    public Blob getSelected() {
        return selected;
    }
}
