/*public class AreaCalculator  {
public double CalculatorArea (double a,double b,double c) {
    double Area=(a*b*c);
    return Area;

}

public double CalculatorArea2(double r) {
    return 3.14*r*r;
}
}
*///Its violate OCP (open Close principal er moto kore kaj hoyni ai jaygay
//unccel bob chay na amra amne code kori tail code sondor dekhay na

package ocp;
public class AreaCalculator  {
public double CalculateArea(String type,double a,double b,double c) {
    if (type.equals("TraiaAngel")) {
        return a*b*c;
    }else if (type.equals("Circle")) {
        return 3.14*a*a;
    }
return 0;
}
}

