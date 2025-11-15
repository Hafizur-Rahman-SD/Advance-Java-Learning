package com.assignment.solid.lsp;

import java.util.List;

public class LspRectangleMain {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Rectangle(4, 5),
                new Square(4)
        );

        for (Shape s : shapes) {
            System.out.println("Area: " + s.area());
        }
    }
}
