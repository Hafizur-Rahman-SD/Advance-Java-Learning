package com.assignment.solid.lsp;

public class Penguin extends Bird {
    @Override
    public void eat() {
        System.out.println("penguin eat Fish ");
    }

    public  void swim(){
        System.out.println("penguin like  Swim");
    }
}
