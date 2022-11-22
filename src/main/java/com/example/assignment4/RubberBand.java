package com.example.assignment4;

public class RubberBand {

    public double left, top, width, height;

    public RubberBand(double newLeft, double newTop, double newWidth, double newHeight) {
        left = newLeft;
        top = newTop;
        width = newWidth;
        height = newHeight;
    }

    public boolean checkHit(double x, double y) {
        return x >= left && x <= left+width && y >= top && y <= top+height;
    }


}
