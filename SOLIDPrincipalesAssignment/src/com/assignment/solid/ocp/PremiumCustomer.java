package com.assignment.solid.ocp;

public class PremiumCustomer implements DiscountPolicy {
    @Override
    public double getDiscount(double amount) {
        return amount * 0.90;
    }
}
