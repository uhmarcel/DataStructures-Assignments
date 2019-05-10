package Assignment2;


 /**************************************************************
 Purpose/Description: Binary Search Tree data structure class.
 Author’s Panther ID: 6161146
 Certification:
 I hereby certify that this work is my own and none of it is the work of
 any other person.
 **************************************************************/ 

public class BinarySearchTree {
    
    private BinarySearchTreeNode root;

    /*----------------------- ASSIGNMENT METHODS ---------------------------*/    
    /**
     * Function keySum
     * Calculates the sum of all elements in the tree. Returns zero if the 
     * tree is empty.
     * Requires the use of keySumRecursive.
     * @return  Integer sum of all elements.
     */
    public int keySum() {
        return keySumRecursive(root);
    }
    
    /**
     * Function keySumRecursive
     * Internal assistant method for keySum(), uses recursion to navigate
     * elements in the tree and retrieve the sum of all elements.
     * @param root  The tree node to start recursion.
     * @return      Integer sum of all elements.
     */
    private int keySumRecursive(BinarySearchTreeNode root) {
        if (root == null) 
            return 0;
        
        int left = keySumRecursive(root.left);
        int right = keySumRecursive(root.right);        
        return root.key + left + right;
    }
    
    /**
     * Function deleteMin
     * Removes the element with the minimum value from the tree,
     * or returns null if the tree is empty.
     */
        
    public void deleteMin() {
        if (root == null)  return;
        
        BinarySearchTreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        delete(current.key);
    }
    
    /**
     * Function printTree
     * Displays the elements in the tree in increasing order.
     * Requires the use of printTreeRecursive
     */
    public void printTree() {
        printTreeRecursive(root);
        System.out.println();
    }
    
    /**
     * Function printTreeRecursive
     * Internal assistant method for printTree, uses in-order traversal
     * recursion to navigate the tree and print elements in increasing order.
     * @param root  Tree node to start recursion
     */
    private void printTreeRecursive(BinarySearchTreeNode root) {
        if (root == null) return;
        
        printTreeRecursive(root.left);
        System.out.print(root.key + " ");
        printTreeRecursive(root.right);
    }
    
    /**
     * Function printPostorder
     * Displays the elements in the tree according to the tree's
     * Post-order Traversal order.
     * Requires the use of printPostorderRecursive.
     */
    public void printPostorder() {
        printPostorderRecursive(root);
        System.out.println();
    }
    
    /**
     * Function printPostorderRecursive
     * Internal assistant method for printPostorder, uses postorder
     * traversal recursion to navigate the tree and print elements in
     * order.
     * @param root  Tree node to start recursion
     */
    private void printPostorderRecursive(BinarySearchTreeNode root) {
        if (root == null) return;

        printPostorderRecursive(root.left);
        printPostorderRecursive(root.right);
        System.out.print(root.key + " ");
    }
    /*---------------------------- END -------------------------------*/  
    
    
    /*---------------------- ASSUMED METHODS --------------------------
    NOTE: The following are methods retrieved from class to implement the
    Binary Search Tree, which were assumed as given for the questions. 
    They are not my work.
    ------------------------------------------------------------------*/
    public void insert(int key) {
        root = insertRecursive(root, key);
    } 
    public void delete(int key) {
        root = deleteRecursive(root, key);
    }
    public boolean find(int key) { 
        return findRecursive(root, key);
    }
    private BinarySearchTreeNode insertRecursive(BinarySearchTreeNode root, int key) {
        if (root == null) {
            root = new BinarySearchTreeNode(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRecursive(root.left, key);
        else if (key > root.key)
            root.right = insertRecursive(root.right, key);
        return root;
    }
    private BinarySearchTreeNode deleteRecursive(BinarySearchTreeNode root, int key) {
        if (root == null)  return root;
 
        if (key < root.key)
            root.left = deleteRecursive(root.left, key);
        else if (key > root.key)
            root.right = deleteRecursive(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.key = minValue(root.right);
            root.right = deleteRecursive(root.right, root.key);
        }
        return root;
    }
    private boolean findRecursive(BinarySearchTreeNode root, int key) {
        if (root == null) return false;
        if (root.key == key)
            return true;
        else 
            return findRecursive(root.left, key) || findRecursive(root.right, key); 
    }
    private int minValue(BinarySearchTreeNode root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    public class BinarySearchTreeNode {    
        public int key;
        public BinarySearchTreeNode left;
        public BinarySearchTreeNode right;
        
        public BinarySearchTreeNode(int key) {
            this.key = key;
        }
    }
    /*---------------------------- END -------------------------------*/  
} 
    

