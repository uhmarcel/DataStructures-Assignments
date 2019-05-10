package Assignment2;


import java.util.List;
import java.util.ListIterator;

 /**************************************************************
 Purpose/Description: Method to calculate the difference between two
                      sorted lists in linear time complexity.
 Author’s Panther ID: 6161146
 Certification:
 I hereby certify that this work is my own and none of it is the work of
 any other person.
 **************************************************************/ 

public class Difference {
    
    /** 
     * Function difference:
     * Given two input sorted lists, calculates their difference
     * and saves the result in given input Difference.
     * Algorithm complexity O(n)
     * @param <AnyType> Input list type, must implement Comparable
     * @param L1    Sorted list to be be subtracted from
     * @param L2    Sorted list with elements to subtract
     * @param Difference List to store result L1 - L2
     */
    
    public static <AnyType extends Comparable<? super AnyType>> void 
     difference(List<AnyType> L1, List<AnyType> L2, List<AnyType> Difference) {
    
        ListIterator<AnyType> iterL1 = L1.listIterator();
        ListIterator<AnyType> iterL2 = L2.listIterator();
        
        AnyType itemL1 = null;  // Added item declaration
        AnyType itemL2 = null;  //
        
        if ( iterL1.hasNext() && iterL2.hasNext() ) {
            itemL1 = iterL1.next();
            itemL2 = iterL2.next();
        }
        
        // CODE STARTS HERE
        
        Difference.clear();       
        // For the case where input L2 is empty (for L1 - {} = L1)
        if (itemL1 == null && iterL1.hasNext()) 
            itemL1 = iterL1.next();
  
        while (itemL1 != null) {
            if (itemL2 == null) {   // If no items to substract, add L1 items
                Difference.add(itemL1);
                itemL1 = iterL1.hasNext() ? iterL1.next() : null;
            }
            else {     // If both items are not null, compare
                int comparison = itemL1.compareTo(itemL2);
                if (comparison > 0) {       // L2 not in L1, ignore
                    itemL2 = iterL2.hasNext() ? iterL2.next() : null;
                }
                else if (comparison < 0) {  // L1 not in L2, add to Difference
                    Difference.add(itemL1);
                    itemL1 = iterL1.hasNext() ? iterL1.next() : null;
                }
                else {    // itemL1 == itemL2, ignore itemL1
                    itemL1 = iterL1.hasNext() ? iterL1.next() : null;
                }
            }
        }
        
    }    

}
