package Assignment1;


/**********************************************************************
Purpose/Description: A program that searches for stability indexes in an
*                   array of integers, and returns them as a list.         
Author’s Panther ID: 6161146
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
***********************************************************************/
                      /* PROBLEM NO. 1 */

import java.util.ArrayList;
import java.util.Scanner;

public class StabilityIndex {
    
    /**
     * SumArray calculates the partial sum of all its integers.
     * The sum of elements has time complexity O(n)
     * @param array an array of integers to compute the sum
     * @return      the total sum of all its elements
     */  
    
    public static int sumArray(int[] array) {
        int result = 0;
        for (int i=0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }
    
    /**
     * FindStabilityIndex finds stability indexes on a given array.
     * It takes advantage of saving the total sum and removing the 
     * index being search to achieve linear time O(n)
     * @param array the input array of integers to search
     * @return      an arrayList with all stability indexes found
     */
    
    public static ArrayList<Integer> findStabilityIndex(int[] array) {
        ArrayList<Integer> indexList = new ArrayList<>(); 
        int leftSum = 0;
        int rightSum = sumArray(array);
        
        for (int i=0; i < array.length; i++) {                 
            if(i - 1 >= 0)               // Check for out of bounds
                leftSum += array[i-1]; 
            rightSum -= array[i];       
            
            if (leftSum == rightSum)
                indexList.add(i);
        }
        return indexList;
    }
    
//  ------------------------------------------------------------------------
    
    /**         TESTBENCH
    * Prompts user for an array of values, then searches for stability
    * indexes that may exist on the array, and displays them.
    */
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
                
        while (loop) {
            System.out.println("Enter valid input array as comma separated "
                             + "values: (e.g 0,-3,5,-4,-2,3,1,0)");
            int[] array = getArray(s.nextLine());

            ArrayList<Integer> stability = findStabilityIndex(array); 
            if (!stability.isEmpty()) 
                System.out.println("Found " + stability.size() + " stability "
                                 + "index at " + stability);
            else
                System.out.println("No stability index was found");
            
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
