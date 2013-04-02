package com.ashishpaliwal.codekatta.algos;

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


    public static void main(String[] args) {
        int[] sortedArr = {10, 11, 15, 17, 19, 20};
        printPairsForSum(21, sortedArr);
        sortedArr = new int[]{1, 2, 2, 3, 4, 5};
        printPairsForSum(6, sortedArr);
    }
}
