package com.assignment.solid.lsp;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public void getSide(double side) {this.side = side;}

    @Override
    public double area() {
        return side*side;
    }
}
