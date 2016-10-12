/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe2;


/**
 *
 * @author mike-wieder
 */
public class MatrixMain_MasterSlave {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        int[][] matrix_A = {
            {1,-2,3,4,-1},
            {-2,3,0,1,2},
            {4,-1,2,1,-2},
            {-2,1,3,-1,3},
            {0,2,-1,2,4},
        };
        int[][] matrix_B = {
            {2,-4,-1,1,-2},
            {-1,1,-2,2,1},
            {5,0,3,-2,-4},
            {1,-2,1,0,2},
            {2,3,-3,0,0},
        };
        
        if(matrix_A.length != matrix_B[0].length)
            throw new Exception("Arrays are not suitable for multiplication");
        
        int[][] product = new int[matrix_A.length][matrix_B[0].length];
        
        for(int i=0; i < matrix_A.length; i++) {
            for(int j=0; j < matrix_B[0].length; j++) {
                int[] flipped_matrix = {matrix_B[0][j],matrix_B[1][j],matrix_B[2][j],matrix_B[3][j],matrix_B[4][j]};
                new WorkerThread(matrix_A[i], flipped_matrix, product, "Thread "+i+"-"+j);
            }
        }
        
    }
    
}

class WorkerThread extends Thread {
    
    private int[] skalar_A;
    private int[] skalar_B;
    private int[][] matrix;
    private int product;
    private String threadName;
    
    public WorkerThread(int[] skalar_A, int[] skalar_B, int[][] matrix, String threadName) throws Exception {
        super(threadName);
        this.threadName = threadName;
        if(skalar_A.length != skalar_B.length)
            throw new Exception("Skalare besitzen unterschiedliche Laenge");
        this.skalar_A = skalar_A;
        this.skalar_B = skalar_B;
        this.matrix = matrix;
        start();
    }
    
    public void run() {
        for(int i=0; i<skalar_A.length;i++) {
            product += skalar_A[i]*skalar_B[i];
        }
        System.out.println(product + threadName);
    }
    
}
