package com.ashishpaliwal.codekatta.fun;

/**
 *
 */
public class Sqrt {

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

    }
}
