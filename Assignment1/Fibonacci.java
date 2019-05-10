package Assignment1;


/**********************************************************************
Purpose/Description: A program that computes an nth Fibonacci
                     number. This implementation of the problem
                     uses the matrix exponentiation approach to 
                     achieve O(log(n)) time complexity.
                     This implementation running on my machine (i5)
                     was able to compute:                      
                     fib(10,000,000) in 3.5 seconds
                     fib(1,000,000) in 0.2 seconds
Author’s Panther ID: 6161146
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
***********************************************************************/
                      /* PROBLEM NO. 3 */

import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {
    
    /**
    * This method's only purpose is to return a copy of the base
    * matrix for Fibonacci numbers in BigInteger format, as it is
    * used often in the calculation.                |fib(2) fib(1)|
    * @return   a 2x2 matrix of BigIntegers  -> M = |fib(1) fib(0)|
    */
    
    private static BigInteger[][] getBigIntegerFibBaseMatrix() {
        BigInteger[][] matrix = new BigInteger[2][2];
        matrix[0][0] = BigInteger.ONE;      
        matrix[0][1] = BigInteger.ONE;
        matrix[1][0] = BigInteger.ONE; 
        matrix[1][1] = BigInteger.ZERO;   
        return matrix;
    }
    
    /**
    * The matrixMultiply method calculates the matrix multiplication 
    * between two 2x2 matrix. This method only works properly for 
    * Fibonacci numbers as I omitted unnecessary calculations.
    * @param A  First 2x2 matrix operand for multiplication.
    *           The operation result gets saved overriding A.
    * @param B  Second 2x2 matrix operand for multiplication.
    */
    
    private static void matrixMultiply(BigInteger[][] A, BigInteger[][] B) {
        //Ommited A[1][0] calculation as in fib its always equal to its diagonal
        BigInteger f2 = (A[0][0].multiply(B[0][0])).add(A[0][1].multiply(B[1][0]));
        BigInteger f1 = (A[0][0].multiply(B[0][1])).add(A[0][1].multiply(B[1][1]));
        BigInteger f0 = (A[1][0].multiply(B[0][1])).add(A[1][1].multiply(B[1][1]));
        A[0][0] = f2;
        A[0][1] = f1;    //           |fib(n+1)  fib(n) |
        A[1][0] = f1;    //       A = | fib(n)  fib(n-1)|
        A[1][1] = f0;
    }
    
    /**             
    * Recursive implementation of pow(b, n), this approach takes advantage
    * of the property b^2n = (b^n))^2 to reduce the time complexity of the
    * problem. Each recursion divides the problem size by half, achieving 
    * O(log n) time complexity.
    * @param m    a 2x2 matrix of BigIntegers as base for exponentiation
    *             input m gets overridden with the result of the operation.
    * @param pow  the exponent of the operation
    */

    private static void recursivePower(BigInteger[][] m, int pow) {
        if (pow == 1)  // when pow gets to one, stop recursion
            return;
        BigInteger[][] b = getBigIntegerFibBaseMatrix();
        recursivePower(m, pow/2);   // call again with problem half size
        matrixMultiply(m, m);       // square matrix
       
        if (pow % 2 == 1)           // if exponent odd, multiply by base
            matrixMultiply(m, b);
    }
    
    /**
    * The method computes the nth Fibonacci number by the use of 
    * matrix exponentiation.
    * @param  n  the desired Fibonacci nth term to compute  
    * @return    the resulting integer in BigInteger format
    */
    
    public static BigInteger fib(int n) {
        if (n < 2)
            return BigInteger.ONE;
        BigInteger[][] matrix = getBigIntegerFibBaseMatrix();
        recursivePower(matrix, n-1);
        return matrix[0][0];        
    }
        
    /**         TESTBENCH
    * Prompts user for input integer to compute the Fibonacci number,
    * user is expected to enter a valid integer, there is no validation.
    */
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.print("Enter value to compute nth fibonacci number: ");
            int n = Integer.parseInt(s.nextLine());

            long start = System.nanoTime();
            BigInteger f = fib(n);
            long end = System.nanoTime();

            System.out.println("fib(" + n + ") = " + f);
            System.out.println("Executed in " + (end-start)/1000 + " us");

            System.out.print("Retry? (y/n) ");
            if (s.nextLine().toLowerCase().equals("n"))
                loop = false;
            System.out.println();
        }
    }

}

/* --------------------------------------------------------------------------
                        ALGORITHM ANALISYS
    
private static BigInteger[][] getBigIntegerFibBaseMatrix() {
    BigInteger[][] matrix = new BigInteger[2][2];
    matrix[0][0] = BigInteger.ONE;      
    matrix[0][1] = BigInteger.ONE;
    matrix[1][0] = BigInteger.ONE; 
    matrix[1][1] = BigInteger.ZERO;   
    return matrix;
}

- First line is a bidimentional array initialization takes O(1) time
- Next four lines are assigning operations which take O(1) each
- Total time complexity is O(1)



private static void matrixMultiply(BigInteger[][] A, BigInteger[][] B) {
    BigInteger f2 = (A[0][0].multiply(B[0][0])).add(A[0][1].multiply(B[1][0]));
    BigInteger f1 = (A[0][0].multiply(B[0][1])).add(A[0][1].multiply(B[1][1]));
    BigInteger f0 = (A[1][0].multiply(B[0][1])).add(A[1][1].multiply(B[1][1]));
    A[0][0] = f2;
    A[0][1] = f1;
    A[1][0] = f1; 
    A[1][1] = f0;
}

- First three lines have an assignation plus a sequence three of operations each,
  considering operations on BigIntegers having constant time, each line has time
  O(4*c1) = O(1) each.
- Last four lines are assigning a value and take a constant O(1) time.
- Total time complexity O(3*(4*c1) + 4*c2) = O(1)



private static void recursivePower(BigInteger[][] m, int pow) {
    if (pow == 1)
        return;
    BigInteger[][] b = getBigIntegerFibBaseMatrix();
    recursivePower(m, pow/2);
    matrixMultiply(m, m);  
    if (pow % 2 == 1) 
        matrixMultiply(m, b);
}

- First line is an if statement with a conditional that takes O(1) time
- Second line is a return operation, takes constant time O(1).
- Third line initializes a 2d array and calls getBigIntegerFibBaseMatrix(),
  the initialization takes constant time, and the call as analized before
  also takes constant time O(1).
- Forth line calls the same method as to start a recursion: the problem will 
  be called with a parameter of half the size of the original, until size one.
  For a problem size n, this takes a total of log n steps, hence O(log n) time
  complexity.
- Fifth line, matrixMultiply, as analized before takes O(1) time.
- Sixth line has an if with two operations, which take O(1) time.
- Seventh line, matrixMultiply, take O(1) time.
- Total time complexity O(c1 + c2*log n) = O(log n)



public static BigInteger fib(int n) {
    if (n < 2)
        return BigInteger.ONE;
    BigInteger[][] matrix = getBigIntegerFibBaseMatrix();
    recursivePower(matrix, n-1);
    return matrix[0][0];        
}

- First line is an if containing a relational operation O(1).
- Second line returns a constant O(1).
- Third line initializes a 2d array and calls getBigIntegerFibBaseMatrix(),
  the initialization takes constant time, and the call as analized before
  also takes constant time O(1).
- Forth line calls recursivePower, which takes O(log n) time.
- Fifth line returns a value from the array, which takes O(1) time.
- Total time complexity O(c1 + c2*log n) = O(log n)

In conclusion, a call to fib(int n) takes sublinear O(log n) time.

*/                            