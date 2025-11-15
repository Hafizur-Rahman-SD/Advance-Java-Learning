package com.assignment.solid.isp;

public class IspMain {
    public static void main(String[] args) {
        Printable basics= new BasicsPrinter();
        basics.print();
        System.out.println(" Lets print all ");

        MultiFunctionPrinter mfp = new MultiFunctionPrinter();
        mfp.print();
        mfp.scan();
        mfp.fax();
    }


}
