package Assignment1;


/**********************************************************************
Purpose/Description: A program that given a formated* array of integers
*                    searches to see if a given element exists on the 
*                    array. If it exists, it returns its index, else it
*                    return -1.
*                    *: Array sequence must be organized by first 
*                    increasing order, then decreasing.
Author’s Panther ID: 6161146
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
***********************************************************************/
                      /* PROBLEM NO. 2 */

import java.util.Scanner;

public class ArraySearch {
    
    public final static int NOT_FOUND = -1;
    
    /**
    * MaxIndexSearch computes the index containing the largest integer
    * (the maximum) on the given formated array, by using binary search.
    * It has O(log n) time complexity.
    * @param  array  the correctly formated input array to search
    * @return        index pointing to maximum
    */
    
    private static int maxIndexSearch(int[] array) {
        int bottom = 0;                 // Start index of subarray 
        int top = array.length-1;       // Last index of subarray
        
        while (true) {
            int middle = (top + bottom) / 2;   
            if ( middle+1 < array.length && array[middle+1] > array[middle] ) { 
                bottom = middle + 1;       
            }
            else if (middle-1 >= 0 && array[middle-1] > array[middle]) {
                top = middle - 1;
            }
            else {
                return middle;
            }
        }
    }
    
    /**
    * IncSubarraySearch computes a binary search on a section of a given
    * array with increasingly ordered elements.
    * It has O(log n) time complexity.
    * @param array   the correctly formated input array to search
    * @param bottom  the lower bound index of the increasing subarray
    * @param top     the upper bound index (inclusive) of the subarray
    * @param element the element to search for
    * @return        index of found element, or -1 if not on array
    */
    
    private static int incSubarraySearch(int[] array, int bottom, int top, int element) {        
        while (top >= bottom) {
            int middle = (top + bottom) / 2;   
            if (array[middle] == element) {
                return middle;
            }
            else if (array[middle] < element) {
                bottom = middle + 1;
            }
            else {
                top = middle - 1;
            }
        }
        return NOT_FOUND;
    } 
    
    /**
    * DecSubarraySearch computes a binary search on a section of a given array
    * with decreasingly ordered elements. 
    * It has O(log n) time complexity.
    * @param array   the correctly formated input array to search
    * @param bottom  the lower bound index of the decreasing subarray
    * @param top     the upper bound index (inclusive) of the subarray
    * @param element the element to search for
    * @return        index of found element, or -1 if not on array
    */
    
    private static int decSubarraySearch(int[] array, int bottom, int top, int element) {
        while (top >= bottom) {
            int middle = (top + bottom) / 2;   
            if (array[middle] == element) {
                return middle;
            }
            else if (array[middle] > element) {
                bottom = middle + 1;
            }
            else {
                top = middle - 1;
            }
        }
        return NOT_FOUND;
    } 
    
    /**
    * SearchElement finds if a given element exists in an correctly
    * formated array. This is the main method who uses calls to the
    * previously mentioned methods. 
    * This method, without adding time complexity of its calls, has
    * time complexity O(1). Hence as it calls at most maxIndexSearch,
    * increasingSubarraySearch and decreasingSubarraySearch, it has a
    * T(n) = 3log n -> O(log n)
    * @param array   the correctly formated input array to search
    * @param element the element to search for
    * @return        index of found element, or -1 if not on array
    */
    
    public static int searchElement(int[] array, int element) {
        if (array.length == 0)
            return NOT_FOUND;
        
        int max = maxIndexSearch(array);
        if (array[max] == element) 
            return max; 
        
        int searchResult;
        
        searchResult = incSubarraySearch(array, 0, max-1, element);
        if (searchResult != NOT_FOUND)
            return searchResult;     

        searchResult = decSubarraySearch(array, max+1, array.length-1, element);
        if (searchResult != NOT_FOUND)
            return searchResult;
        
        return NOT_FOUND;
    }
    
//  ------------------------------------------------------------------------
    
    /**         TESTBENCH
    * Prompts user for an array of values, then for an element to search, and 
    * returns if the element exist or not, and its index.
    */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
                
        while (loop) {
            System.out.println("Enter valid input array as comma separated "
                             + "values: (e.g 4,6,9,15,7,-2)");
            int[] array = getArray(s.nextLine());
            System.out.print("Enter integer value to search: ");
            int element = Integer.parseInt(s.nextLine());

            int searchResult = searchElement(array, element);
            if (searchResult != NOT_FOUND) 
                System.out.println("The value " + element + " was found in "
                                 + "the array at index " + searchResult);
            else
                System.out.println("The value " + element + " was not found "
                                 + "in the array");
            
            System.out.print("Retry? (y/n) ");
            if (s.nextLine().toLowerCase().equals("n"))
                loop = false;
            System.out.println();
        }
    }
    
    /**
    * Auxiliary method used to get an array as input. It processes a line of
    * comma separated values to an array of integers.
    * @param csvInput string input line of comma separated values
    * @return         array of integers
    */
    
    public static int[] getArray(String csvInput) {
        String[] splitted = csvInput.replace(" ","").split(",");
        int[] array = new int[splitted.length];
        for (int i=0; i<array.length; i++) 
            array[i] = Integer.parseInt(splitted[i]);
        return array;
    }

}
