package Assignment2;


import java.util.Stack;

 /**************************************************************
 Purpose/Description: Method to sort a Stack in decreasing order.
 Author’s Panther ID: 6161146
 Certification:
 I hereby certify that this work is my own and none of it is the work of
 any other person.
 **************************************************************/ 

public class StackSort {
    
    /**
     * Function sort
     * Sorts an input Stack to decreasing order.
     * Algorithm complexity O(n^2)
     * @param s Input stack to be sorted
     * @return  The input stack in decreasing order
     */

    public static Stack<Integer> sort(Stack<Integer> s) {
        Stack<Integer> sorted = new Stack<>();
        
        while (!s.isEmpty()) {
            int current = s.pop();
            // If current element violates 'sorted' stack order, push and pop
            // back elements to original array until it doesn't.
            while ( (!sorted.isEmpty()) && (sorted.peek() < current) ) {
                s.push(sorted.pop());
            }
            sorted.push(current);
        }
        return sorted;
    }
    
    /*
    Time Complexity Analysis:

    In the best case, the input stack starts by being in increasing order, as 
    the process of pop then push naturally reverts the order of the first stack, 
    then taking O(n) time, as there is no need to re-push the elements from two 
    to one.

    In the worst case, the input stack starts already sorted in decreasing 
    order, which again as the process of pushing and popping reverses the 
    order, then all elements would be incorrectly added to the second 
    stack, hence needing to be push back to the first one, which would 
    take O(n^2) time, as is basically iterating again for each element.

    As the functions push, pop, isEmpty and peek are all O(1) constant time, 
    then the total complexity of the algorithm is O(n^2) time.
    */
    
}
