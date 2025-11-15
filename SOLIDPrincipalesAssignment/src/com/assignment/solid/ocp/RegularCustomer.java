package com.assignment.solid.ocp;

public class RegularCustomer implements DiscountPolicy {
    @Override
    public double getDiscount(double amount) {
        return amount * 0.95;
    }
}
