package com.assignment.solid.ocp;

public class DiscountCalculator {

    private final DiscountPolicy discountPolicy;

    public DiscountCalculator(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    public double calculate (double amount) {
        return discountPolicy.getDiscount(amount);
    }


}
