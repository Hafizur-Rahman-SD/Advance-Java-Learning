package com.assignment.solid.srp;

public class SrpMain {
    public static void main (String[] args) {
        //ekhon ami emp banabo
        Employee emp = new Employee("Hafizur Rahman",178, 30000);

        //now calculate my salary
        SalaryCalculator calc = new SalaryCalculator();
        double finalSalary = calc.calculateSalary(emp);

        //now genarate report

        ReportGenerator report = new ReportGenerator();
        report.generateReport(emp,finalSalary);

    }
}
//apadoto kaj ses akhon dekhi run kroe ki hoy