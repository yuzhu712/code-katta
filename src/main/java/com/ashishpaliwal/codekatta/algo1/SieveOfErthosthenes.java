package com.ashishpaliwal.codekatta.algo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class SieveOfErthosthenes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int upperBound = scanner.nextInt();

        boolean[] primes = new boolean[upperBound + 1];
        List<Integer> primeNumbers = new ArrayList<Integer>();
        for(int i = 2; i < upperBound; i++) {
            if(!primes[i]) {
                primeNumbers.add(i);

                for(int j = i * i; j < upperBound; j += i) {
                    primes[j] = true;
                }
            }
        }
        System.out.println(Arrays.toString(primeNumbers.toArray(new Integer[0])));
    }
}
