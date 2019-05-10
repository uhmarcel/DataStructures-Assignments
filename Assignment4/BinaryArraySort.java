package Assignment4;

/**********************************************************************
Purpose/Description: A program that sorts an array of binary numbers in
                     linear running time complexity.
Author’s Panther ID: 6161146
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
***********************************************************************/

import java.util.Scanner;

public class BinaryArraySort {
    
    /**
     * Method binaryArraySort
     * Sorts a given array of binary numbers in ascending order in linear
     * time complexity.
     * @param binaryArray  input array of binary numbers to be sorted
     */
    
    public static void binaryArraySort(int[] binaryArray) {
        int zeroesCount = 0;
        
        // Count total amount of zeroes in array
        for (int i=0; i<binaryArray.length; i++) {
            if (binaryArray[i] == 0)
                zeroesCount++;
        }
        // Set array elements to zeroes up to zeroesCount
        for (int i=0; i<zeroesCount; i++) {
            binaryArray[i] = 0;
        }
        // Set remainder elements to one
        for (int i=zeroesCount; i<binaryArray.length; i++) {
            binaryArray[i] = 1;
        }
    }

/////////////////////////////////////////////////////////////////////////////
    
    /*          TESTING
    * Prompts user for an array of binary numbers, then displays array sorted
    * in ascending order.
    */
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] binaryArray;

        System.out.println("Enter an array of binary numbers to sort as comma"
                + " separated values: (e.g 1,1,0,1,0,1)");
        
        binaryArray = getArray(s.nextLine());
        binaryArraySort(binaryArray);
        
        System.out.println("Sorted array: ");
        printArray(binaryArray);
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
