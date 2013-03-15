package com.ashishpaliwal.codekatta.spoj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for PRIME1 on spoj
 */
public class PRIME1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = Integer.parseInt(scanner.nextLine().trim());
        String[] inputs = new String[numTestCases];

        for (int i = 0; i < numTestCases; i++) {
            inputs[i] = scanner.nextLine().trim();
        }

        for (int k = 0; k < numTestCases; k++) {
            String[] input = inputs[k].split(" ");
            int min = Integer.parseInt(input[0]);
            int upperBound = Integer.parseInt(input[1]);

            boolean[] primes = new boolean[upperBound + 1];
            for(int i = 2; i <= upperBound; i++) {
                if(!primes[i]) {
                    if(i >= min) {
                        System.out.println(i);
                    }

                    for(int j = i * i; j <= upperBound; j += i) {
                        primes[j] = true;
                    }
                }
            }
            System.out.println();
        }
    }

}
