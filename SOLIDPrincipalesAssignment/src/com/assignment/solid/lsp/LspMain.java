package com.assignment.solid.lsp;

public class LspMain {
    public static void main(String[] args) {

        Sparrow sparrow = new Sparrow();
        sparrow.eat();
        sparrow.fly();

        System.out.println("I am Hafizur Rahman ");

        Penguin penguin = new Penguin();
        penguin.eat();
        penguin.swim();
    }
}
