package com.assignment.solid.srp;

public class SalaryCalculator {
    public double calculateSalary(Employee emp) {
        //let amra 10% bounas dibo emp k
        return  emp.getBasicSalary() + (emp.getBasicSalary()*0.1);
    }
}
// ei jaygay shudu salary hisab hobe