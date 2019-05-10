package Assignment4;

/**********************************************************************
Purpose/Description: A program that sorts an array of binary numbers in
                     linear running time complexity.
Author’s Panther ID: 6161146
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
***********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class EvenRadixSort {
    
    public static final int DISTINCT_DIGITS = 5;   // Even numbers in a digit
        
    /**
     * Method radixSort
     * Sorts the given input array using the radix sort method.
     * This implementation of the radix sort required the array to have
     * only even digits.
     * Requires the use of bucketSortRec method.
     * @param arr  input array of integers with only even digits
     */
    
    public static void radixSort(int arr[]) {
        List<Queue<Integer>> buckets = new ArrayList<>();
        int max = getMax(arr);
        
        // Initialize each bucket queue to a linked list
        for (int i = 0; i < DISTINCT_DIGITS; i++) 
            buckets.add(new LinkedList<>());

        bucketSortRec(arr, 1, max, buckets);
    }
    
    /**
     * Method bucketSortRec
     * Assistant method for radixSort. sorts input according to the value
     * of one of its digits, using bucket sort.
     * @param arr      input array of integers with even digits
     * @param curExp   exponent representing the current digit to sort 
     * @param maxExp   representation of the largest digit to sort 
     * @param buckets  list of buckets to perform bucket sort on.
     */
    
    public static void bucketSortRec(int arr[], int curExp, int maxExp, 
            List<Queue<Integer>> buckets) {
        if (curExp > maxExp)  return;
        int digitIndex = 0;
        int arrIndex = 0;
        
        // Insert elements on corresponding buckets
        for (int i=0; i<arr.length; i++) {
            digitIndex = ((arr[i] / curExp) / 2) % buckets.size();
            buckets.get(digitIndex).add(arr[i]);
        }
        // Reassamble array with elements following bucket order
        for (Queue<Integer> q : buckets) {
            while (!q.isEmpty()) {
                arr[arrIndex] = q.remove();
                arrIndex++;
            }
        }
        // Sort array again for the next digit 
        bucketSortRec(arr, curExp * 10, maxExp, buckets);
    }
    
    /**
     * Method getMax
     * Finds the largest integer in a given array.
     * @param arr  input array of integers
     * @return     the largest element of the array
     */
    
    public static int getMax(int arr[]) {
        if (arr.length == 0)  
            return -1;
        int largest = arr[0];
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > largest)
                largest = arr[i];
        }
        return largest;
    }
    
    /**
     * Method preprocessArray
     * Processes the elements of an input array to ensure they only contain even 
     * digits, following the rule that if an odd digit is found, then it is 
     * replaced by the greater even digit less than itself.
     * @param arr  input array of integers for preprocessing
     */
        
    public static void preprocessArray(int arr[]) {
        Stack<Integer> s = new Stack<>();
        int preprocessed;
        int currentDigit;
        
        for (int i=0; i<arr.length; i++) {
            preprocessed = 0;
            while (arr[i] > 0) {
                s.push(arr[i] % 10);
                arr[i] = arr[i] / 10;
            }
            while (!s.isEmpty()) {
                currentDigit = s.pop();
                if (currentDigit % 2 == 1)
                    currentDigit--;
                preprocessed = preprocessed * 10 + currentDigit;
            }
            arr[i] = preprocessed;
        }
    }
    
/////////////////////////////////////////////////////////////////////////////
    
    /*          TESTING
    * Prompts user for an array of numbers, then displays the result of
    * preprocessing the array, and the final sorted array using radixSort.
    */
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int array[];
        
        System.out.println("Enter an array of numbers to sort as comma"
                + " separated values: (e.g 12,41,3,91,123,7)");
        array = getArray(s.nextLine());
        
        preprocessArray(array);
        System.out.println("After preprocessing:");
        printArray(array);
                
        radixSort(array);
        System.out.println("Sorted array:");
        printArray(array);
    }
    
    /**
     * Method getArray
     * Auxiliary method used to get an array as input. It processes a line of
     * comma separated values to an array of integers.
     * @param csvInput string input line of comma separated values
     * @return         array of integers
     */
    
    public static int[] getArray(String csvInput) {
        String[] splitted = csvInput.replace(" ","").split(",");
        int[] array = new int[splitted.length];
        if (splitted[0].equals(""))
            return new int[0];
        for (int i=0; i<array.length; i++) 
            array[i] = Integer.parseInt(splitted[i]);
        return array;
    }
    
    /**
     * Method printArray
     * Auxiliary method to display the elements of an array.
     * @param array  an array of integers to be displayed
     */
    
    public static void printArray(int[] array) {
        if (array.length == 0) return;
        System.out.print(array[0]);
        for (int i=1; i<array.length; i++) 
            System.out.print("," + array[i]);
        System.out.println();
    }

}
