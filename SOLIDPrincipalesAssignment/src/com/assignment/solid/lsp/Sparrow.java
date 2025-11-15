package com.assignment.solid.lsp;

public class Sparrow extends FlyingBird {

    @Override
    public void eat() {
        System.out.println("Sparrow is flying");
    }

    @Override
    public void fly(){
        System.out.println("Sparrow is flying high ");
    }
}
