package com.example.assignment4.Command;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.TargetCommand;
import com.example.assignment4.Model.BlobModel;

import java.text.DecimalFormat;

public class CreateCommand implements TargetCommand {
    Blob myBlob;
    BlobModel model;
    double blobX, blobY;
    DecimalFormat df;

    public CreateCommand(BlobModel newModel, double newX, double newY) {
        model = newModel;
        myBlob = null;
        blobX = newX;
        blobY = newY;
        df = new DecimalFormat("#.##");
    }

    public void doIt() {
        if (myBlob == null) {
            myBlob = model.createBlob(blobX, blobY);
        } else {
            model.addBlob(myBlob);
        }
    }

    public void undo() {
        model.deleteBlob(myBlob);
    }

    public String toString() {
        return "Create: " + df.format(blobX) + ", " + df.format(blobY);
    }
}
