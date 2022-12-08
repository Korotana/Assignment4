package com.example.assignment4.Command;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.TargetCommand;
import com.example.assignment4.Model.BlobModel;

import java.text.DecimalFormat;

public class ResizeCommand implements TargetCommand {
    Blob myBlob;
    BlobModel model;
    double dr;
    DecimalFormat df;

    public ResizeCommand(BlobModel newModel, Blob newBlob, double newR) {
        model = newModel;
        myBlob = newBlob;
        dr = newR;
        df = new DecimalFormat("#.##");
    }

    public void doIt() {
        model.resizeBlob(myBlob, dr);
    }

    public void undo() {
        model.resizeBlob(myBlob, dr * -1);
    }

    public double getDr() {
        return dr;
    }

    public void setDr(double dr) {
        this.dr = dr;
    }

//    public String toString() {
//        return "Move: " + df.format(dx) + "," + df.format(dy);
//    }
}