package com.example.assignment4.Command;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.TargetCommand;
import com.example.assignment4.Model.BlobModel;

import java.text.DecimalFormat;

public class DeleteCommand implements TargetCommand {
    Blob myBlob;
    BlobModel model;
    double blobX, blobY;
    DecimalFormat df;

    public DeleteCommand(BlobModel newModel,Blob newB,double newX, double newY) {
        model = newModel;
        myBlob = newB;
        blobX = newX;
        blobY = newY;
        df = new DecimalFormat("#.##");
    }

    public void doIt() {
        model.deleteBlob(myBlob);
    }

    public void undo() {
        model.addBlob(myBlob);
    }

    public String toString() {
        return "Create: " + df.format(blobX) + ", " + df.format(blobY);
    }
}
