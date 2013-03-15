package com.ashishpaliwal.codekatta.spoj;

import java.util.Scanner;

/**
 * Problem id #SBSTR1
 */
public class SBSTR1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.next();
        String input2 = scanner.next();
        if(input2.length() > input1.length()) {
            System.out.println("0");
        }

        char[] inputChar1 = input1.toCharArray();
        char[] inputChar2 = input2.toCharArray();

        int index = inputChar1.length - 1;
        int begin = inputChar2.length - 1;

        int length = index;
        boolean found = true;
        while(length > 0) {
            for(int i = begin; i > 0; i--) {
                if(inputChar1[index--] != inputChar2[i]) {
                    found = false;
                    break;
                }
            }
        }
        System.out.println(found ? "1" : "0");
    }

}
