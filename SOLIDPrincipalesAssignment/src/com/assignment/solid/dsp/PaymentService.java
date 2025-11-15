package com.assignment.solid.dsp;

public class PaymentService {
    private PayPalPayment payment =new PayPalPayment();

    public void makePayment(double amount){
        payment.pay(amount);
    }

}
//its brack dip principale for future implementation