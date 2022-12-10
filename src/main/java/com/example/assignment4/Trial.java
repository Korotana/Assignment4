package com.example.assignment4;

import java.util.Timer;
import java.util.TimerTask;

public class Trial {

    long startTime;
    long endTime;
    Blob blob;
    double indexDiff;
    double distance;

    public Trial() {
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public void startTimer(){
        startTime = System.currentTimeMillis();
    }

    public void endTimer(){
        endTime = System.currentTimeMillis() - startTime;
    }

    public void calcID(){
        System.out.println(Math.log(2));
        double log2 = Math.log(2);
        indexDiff = Math.log(distance/ blob.r) / log2;
    }

}
