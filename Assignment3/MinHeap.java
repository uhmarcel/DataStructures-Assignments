
package Assignment3;

 /**************************************************************
 Purpose/Description: replaceKey implementation from p2 of assignment 3.
 Author’s Panther ID: 6161146
 Certification:
 I hereby certify that this work is my own and none of it is the work of
 any other person.
 **************************************************************/ 

public class MinHeap {
    
    /**
     * Method ReplaceKey
     * Replaces an element in the heap with a new value, then restores
     * the heap property.
     * @param oldKey    key value to be replaced
     * @param newKey    new key to replace oldKey
     */
    
    public void replaceKey(int oldKey, int newKey) {
        int index;  
        boolean found = false;
        
        // Search for element in heap. Starts at index 1.
        for (index = 1; index < heap.length; index++) {
            if (heap[index] == oldKey) {
                found = true;
                break;
            }
        }
        
        // If oldKey is not on the heap, display error and terminate
        if (!found) {
            System.out.println("Element " + oldKey + " was not found");
            return;
        }
        
        // Replace keys
        heap[index] = newKey;
        
        // Restore heap property
        if ( (index > 1) && (heap[index] < heap[index/2]) ) 
            percolateUp(index);
        else 
            percolateDown(index);
    }
        
    /*
       Time Complexity Analysis 
       The implementation of the method replaceKey consists of three main steps;
       search the heap for oldKey, replacing the keys, and restoring the heap 
       property.

       First, during the search step, the heap array is traversed each element 
       at a time to find the old key. In the worst case, the element would be at 
       the end of the array or not existing, therefore having a time complexity 
       of O(n).

       Second, the replacement of keys is just an assignment and takes O(1) time.

       Lastly, restoring the heap property requires to either percolateUp, or 
       percolateDown. Both of these methods swap elements between levels 
       (child-parent or parent-child), therefore it can at most take the same 
       amount of steps as the height of the heap tree which as discussed in 
       class is log(n), therefore this step takes O(log n) time.

       In conclusion, the total time complexity of replaceKey is O(n), as it is 
       the dominant term. 
     */
    
    /*---------------------------- END -------------------------------*/  
    
    
    /*---------------------- ASSUMED METHODS --------------------------
    NOTE: The following are methods retrieved from class to implement the
    MinHeap class, which were assumed as given for the questions. 
    They are not my work.
    ------------------------------------------------------------------*/
    
    public void percolateUp( int startNode ) {
        int tmp = heap[startNode];
        int hole;   
        for (hole = startNode; hole > 1 && tmp < heap[hole/2]; hole /= 2) {
                heap[hole] = heap[hole / 2];
        }
        heap[hole] = tmp;
    }
    
    public void percolateDown(int startNode) {
        int tmp = heap[startNode];
        int child, hole;
        for(hole = startNode; hole * 2 <= size; hole = child ) {
            child = hole * 2;   
            if (child != size && (heap[child + 1] < heap[child])) 
                child++;
            if(heap[child] < tmp) 
                heap[hole] = heap[child]; 
            else 
                break;
        }
        heap[hole] = tmp;
    }
    
    // Added insert for testing
    public void insert(int x) {
        if(size == heap.length-1) 
            return;
        heap[size + 1] = x;
        percolateUp(size + 1); 
        ++size;
    }
       
    private int[] heap;
    private int size;
    
    public MinHeap(int maxSize) {
        heap = new int[maxSize];
        size = 0;
    }
    
}
