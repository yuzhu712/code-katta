package com.ashishpaliwal.codekatta.algos;

import java.util.Arrays;

/**
 *  Input is a sorted array
 *  Print all the pairs which sum to a given number
 */
public class PrintAllSums {

    /**
     *
     * Prints all the pairs whose sum is equal to the sum value provided.
     * This solution does not work for duplicate elements in an array
     *
     * @param sumValue      Sum value
     * @param sortedArray   sorted array input
     */
    public static void printPairsForSum(int sumValue, int[] sortedArray) {
        int leftIndex = 0;
        int rightIndex = sortedArray.length - 1;

        while(leftIndex < rightIndex) {
            int sum = sortedArray[leftIndex] + sortedArray[rightIndex];
            if(sumValue == sum) {
                System.out.println(String.format("{%d,%d}", sortedArray[leftIndex], sortedArray[rightIndex]));
                leftIndex++;
                rightIndex--;
            } else if (sum < sumValue) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
    }

    /**
     * Prints the pairs whose sum is equal to sunValue.
     * This approach uses Binary search for getting to the pairs.
     *
     * This approach also has limitations as Binary search won't reveal all the pairs
     * like for this pair {1, 2, 2, 3, 4, 4}
     *
     *
     * @param sumValue      Sum value
     * @param sortedArray   sorted array input
     */
    public static void printPairsForSumBSearch(int sumValue, int[] sortedArray) {
        for (int i = 0; i < sortedArray.length; i++) {
            int num = sumValue - sortedArray[i];
            int index = Arrays.binarySearch(sortedArray, i + 1, sortedArray.length, num);
            if(index > 0) {
                System.out.println(String.format("{%d,%d}", sortedArray[i], sortedArray[index]));
            }
        }
    }


    public static void main(String[] args) {
        int[] sortedArr = {10, 11, 15, 17, 19, 20};
        printPairsForSum(21, sortedArr);
        System.out.println("---------------------");
        printPairsForSumBSearch(21, sortedArr);
        System.out.println("---------------------");
        sortedArr = new int[]{1, 2, 2, 3, 4, 4};
        printPairsForSum(6, sortedArr);
        System.out.println("---------------------");
        printPairsForSumBSearch(6, sortedArr);
    }
}
