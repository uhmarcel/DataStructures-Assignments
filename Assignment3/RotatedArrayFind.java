package Assignment3;
 
 /**************************************************************
 Purpose/Description: Find implementation from p1 of assignment 3.
 Author’s Panther ID: 6161146
 Certification:
 I hereby certify that this work is my own and none of it is the work of
 any other person.
 **************************************************************/ 

import java.util.Scanner;

public class RotatedArrayFind {
    
    public static final int NOT_FOUND = -1;
    
    /**
     * Method find
     * Searches a given array to find a key given as a parameter, if found
     * retrieves the index in the array. Requires the use of findMinRecursive
     * and findRecursive to find the key.
     * @param array input array to be search from
     * @param key   input key to search on the array
     * @return      the index of the element, or -1 if not found
     */
    
    public static int find(int[] array, int key) {
        int breakPoint = findMinRecursive(array, 0, array.length-1);
        int found = NOT_FOUND;
        
        if (breakPoint != 0) 
            found = findRecursive(array, 0, breakPoint-1, key);
        if (found == NOT_FOUND) 
            found = findRecursive(array, breakPoint, array.length-1, key);
        return found;
    }
    
    /**
     * Method findMinRecursive
     * Helper method for find, searches the input array to find the minimum
     * value, and return its index. The search is implemented as a binary 
     * search.
     * @param array input array to search from
     * @param from  starting index to search from array
     * @param to    ending index to search from array
     * @return      index of the element with the minimum value
     */
    
    private static int findMinRecursive(int[] array, int from, int to) {
        if (from == to) return from;
        
        int middle = (from + to) / 2;
        int leftMin = array[from];
        int rightMin = array[to];
        
        if (leftMin > array[middle])  leftMin = array[middle];
        if (rightMin > array[middle+1]) rightMin = array[middle+1];
        
        if (leftMin < rightMin)
            return findMinRecursive(array, from, middle);
        return findMinRecursive(array, middle + 1, to);
    }
    
    /**
     * Method findRecursive
     * Searches an array to find a given key element, to then return its 
     * index. The search is implemented as a binary search.
     * @param array input array to search from
     * @param from  starting index to search from array
     * @param to    ending index to search from array
     * @param key   the element to search for
     * @return      returns the index of the element, or -1 if not found
     */
    
    private static int findRecursive(int[] array, int from, int to, int key) {
        if (from > to) return NOT_FOUND;
        
        int middle = (from + to) / 2;
        if (key == array[middle])
            return middle;
        if (key < array[middle]) {
            return findRecursive(array, from, middle-1, key);
        }
        return findRecursive(array, middle+1, to, key);
    }
    
// ------------------------------------------------------------------------- //
    
    /*                       TESTBENCH
     * Prompts user for an array of values, then for an element to search, and 
     * returns if the element exist or not, and its index.
     */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
                
        while (loop) {
            System.out.println("Enter valid input array as comma separated "
                             + "values: (e.g 4,5,6,7,1,2,3)");
            int[] array = getArray(s.nextLine());
            System.out.print("Enter integer value to search: ");
            int element = Integer.parseInt(s.nextLine());

            int findResult = find(array, element);
            if (findResult != NOT_FOUND) 
                System.out.println("The value " + element + " was found in "
                                 + "the array at index " + findResult);
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

// ------------------------------------------------------------------------- //

/*
 * Time Complexity Analisis:
 * The algorithm consists of three steps, the method find() calls at most 
 * findMinRecursive() once and findRecursive() twice:
 *
 * int findMinRecursive(int[] array, int from, int to):
 * Consists of a modified binary search to find the minimum element (and 
 * therefore determine the pivot point of the rotation on the array). As each
 * recursion cuts the amount of elements to search by half, then the time 
 * complexity of the method is O(log n).
 * 
 *  int findRecursive(int[] array, int from, int to, int key):
 * This method again uses binary search to find an element in an array of 
 * increasing order. As every iteration the element to search are reduced by 
 * half, and the rest of operations within each iteration are O(1), then this 
 * method has time complexity O(log n).
 * 
 *  Overall, in the worst case, the method find has to operate findMinRecursive 
 * once (log n), and findRecursive on each side of the pivot point (2 x log n), 
 * therefore taking 3 x log n time, which is equivalent to say O(log n) 
 * complexity.
 */