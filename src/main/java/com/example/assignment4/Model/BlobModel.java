package com.example.assignment4.Model;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.BlobModelListener;
import com.example.assignment4.RubberBand;
import com.example.assignment4.View.BlobView;

import java.util.*;

public class BlobModel {
    private List<BlobModelListener> subscribers;
    private List<Blob> blobs;
    public ArrayList<RubberBand> rubberBandArrayList;
    public double rubberTop, rubberLeft, rubberWidth, rubberHeight;

    public BlobModel() {
        subscribers = new ArrayList<>();
        blobs = new ArrayList<>();
        rubberBandArrayList = new ArrayList<>();
    }

    public void addBlob(double x, double y) {
        blobs.add(new Blob(x,y));
        notifySubscribers();
    }

    public void moveBlob(Blob b, double dx, double dy) {
        b.move(dx,dy);
        notifySubscribers();
    }

    public void resizeBlob(Blob b,double nr){
        if (b.r > 5) b.r += nr;
        else {b.r = 5.1;
            System.out.println("CANNOT DECREASE MORE THAN THAT");}
        notifySubscribers();
    }

    public void deleteBlob(Blob b){
        blobs.remove(b);
        notifySubscribers();
    }

    public void addSubscriber(BlobView sub) {
        subscribers.add(sub);
    }

    private void notifySubscribers() {
        subscribers.forEach(s -> s.modelChanged());
    }

    public List<Blob> getBlobs() {
        return blobs;
    }

    public boolean hitBlob(double x, double y) {
        for (Blob b : blobs) {
            if (b.contains(x,y)) return true;
        }
        return false;
    }

    public Blob whichHit(double x, double y) {
        for (Blob b : blobs) {
            if (b.contains(x,y)) return b;
        }
        return null;
    }

    Integer selectionsNum = 1;
    public HashMap<Integer, ArrayList<Blob>> createRubberBand(double w, double h, boolean selectionComplete) {
        HashMap<Integer,ArrayList<Blob>> selections = new HashMap<>();
        RubberBand band = new RubberBand(rubberLeft, rubberTop, w-rubberLeft, h-rubberTop);
        rubberBandArrayList.add(band);
        if (selectionComplete){
            ArrayList<Blob> selectionList = new ArrayList<>();
            blobs.forEach(b -> {
                if (band.checkHit(b.x,b.y)){
                    selectionList.add(b);
                }
            });
            if (selectionList.size() > 0) selections.put(selectionsNum,selectionList);
        }
        selectionsNum+=1;
        notifySubscribers();
        rubberBandArrayList.remove(band);
        return selections;
    }
}
