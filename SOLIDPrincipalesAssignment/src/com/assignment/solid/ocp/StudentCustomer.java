package com.assignment.solid.ocp;

public class StudentCustomer implements DiscountPolicy {
    @Override
    public double getDiscount(double amount) {
        return amount * 0.80;
    }
}
//20