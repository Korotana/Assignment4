package com.example.assignment4.Command;

import com.example.assignment4.Blob;
import com.example.assignment4.Interface.TargetCommand;
import com.example.assignment4.Model.BlobModel;

import java.text.DecimalFormat;

public class MoveCommand implements TargetCommand {
    Blob myBlob;
    BlobModel model;
    double dx, dy;
    DecimalFormat df;

    public MoveCommand(BlobModel newModel, Blob newBox, double newDX, double newDY) {
        model = newModel;
        myBlob = newBox;
        dx = newDX;
        dy = newDY;
        df = new DecimalFormat("#.##");
    }

    public void doIt() {
        model.moveBlob(myBlob, dx, dy);
    }

    public void undo() {
        model.moveBlob(myBlob, dx * -1, dy * -1);
    }

    public String toString() {
        return "Move: " + df.format(dx) + "," + df.format(dy);
    }
}