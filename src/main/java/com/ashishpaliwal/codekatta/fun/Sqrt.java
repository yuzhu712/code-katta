package com.ashishpaliwal.codekatta.fun;

/**
 *
 */
public class Sqrt {

    /**
     * Calculate sqrt using Newton's method
     *
     * @param number    Number whose square root is to be calculated
     * @return
     */
    public static double sqrtNewton(double number) {
        if(number < 0) {
            return Double.NaN;
        }

        double Epsilon = 1E-15;
        double t = number;

        while(Math.abs(t - number/t) > Epsilon*t) {
            t = (number/t + t) / 2.0;
        }
        return t;
    }

    public static double sqrt(double number) {
        if(number < 0) {
            return -1;
        }

        if(number == 0 || number == 1) {
            return number;
        }

        double precision = 0.00001;
        double start = 0;
        double end = number;

        if(number < 1) {
            end = 1;
        }

        while(end - start > precision) {
            double mid = (start + end)/2;
            double midsqr = mid * mid;
            if(midsqr == number) {
                return mid;
            } else if (midsqr < number) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return (start + end)/2;
    }

    public static void main(String[] args) {
        System.out.println("Sqrt of 9 = "+sqrt(9));
        System.out.println("Sqrt of 27 = "+sqrt(27));
        System.out.println("Sqrt of 89 = "+sqrt(89));
        System.out.println("---------------Newton's method ----- ");
        System.out.println("Sqrt of 9 = "+sqrtNewton(9));
        System.out.println("Sqrt of 27 = "+sqrtNewton(27));
        System.out.println("Sqrt of 89 = "+sqrtNewton(89));
    }
}
