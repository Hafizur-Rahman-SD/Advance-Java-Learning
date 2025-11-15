package com.assignment.solid.isp;

public class MultiFunctionPrinter implements Printable,Scannable,Faxable {
    @Override
    public void print() {
        System.out.println("Printing Basic Printer");
    }
    @Override
    public void scan() {
        System.out.println("Scanning Basic Printer");
    }
    @Override
    public void fax() {
        System.out.println("Fax Printing Basic Printer");
    }
}
