package com.assignment.solid.dsp;

public class DipMain {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment(200.00);
        System.out.println("Payment Successful");
    }
}
