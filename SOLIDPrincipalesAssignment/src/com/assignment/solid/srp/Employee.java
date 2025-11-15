package com.assignment.solid.srp;

public class Employee {
    private String name;
    private int id;
    private double basicSalary;
    public Employee(String name, int id, double basicSalary){
        this.name = name;
        this.id = id;
        this.basicSalary = basicSalary;
    }
    public String getName() {return name;}
    public int getId() {return id;}
    public double getBasicSalary() {return basicSalary;}
}
// aita shudhu employee er info rakhar jonnoa