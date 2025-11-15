package com.assignment.solid.ocp;

public class OcpMain {
    public static void main(String[] args) {
        double amount = 1000.0;

        DiscountCalculator regular = new DiscountCalculator(new RegularCustomer());
        DiscountCalculator premium = new DiscountCalculator(new PremiumCustomer());
        DiscountCalculator vip = new DiscountCalculator(new VipCustomer());

        System.out.println("Regular customer pays: " + regular.calculate(amount));
        System.out.println("Premium customer pays: " + premium.calculate(amount));
        System.out.println("VIP customer pays: " + vip.calculate(amount));

        // Now let's add a new customer type without changing old code
        DiscountCalculator student = new DiscountCalculator(new StudentCustomer());
        System.out.println("Student customer pays: " + student.calculate(amount));
    }
}
