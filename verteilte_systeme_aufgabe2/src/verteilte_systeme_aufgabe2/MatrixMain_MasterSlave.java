/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe2;

import java.util.ArrayList;
import java.util.Arrays;

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

        long startTime = System.nanoTime();

        int threadCount = Integer.valueOf(args[0]);

        int[][] matrix_A = {
            {1, -2, 3, 4, -1},
            {-2, 3, 0, 1, 2},
            {4, -1, 2, 1, -2},
            {-2, 1, 3, -1, 3},
            {0, 2, -1, 2, 4},};
        int[][] matrix_B = {
            {2, -4, -1, 1, -2},
            {-1, 1, -2, 2, 1},
            {5, 0, 3, -2, -4},
            {1, -2, 1, 0, 2},
            {2, 3, -3, 0, 0},};

        if (matrix_A.length != matrix_B[0].length) {
            throw new Exception("Arrays are not suitable for multiplication");
        }

        Product product = new Product(matrix_A.length, matrix_B[0].length);

        ArrayList<WorkerThread> threadPool = new ArrayList<>();

        ArrayList<WorkPackage> wp = new ArrayList<>();

        for (int i = 0; i < matrix_A.length; i++) {
            for (int j = 0; j < matrix_B[0].length; j++) {
                int[] flipped_matrix = {matrix_B[0][j], matrix_B[1][j], matrix_B[2][j], matrix_B[3][j], matrix_B[4][j]};
                wp.add(new WorkPackage(matrix_A[i], flipped_matrix, i, j));
            }
        }

        WorkPackage[][] work = new WorkPackage[threadCount][(25 / threadCount) + 1];
        int count = 0;

        for (int i = 0; i < ((matrix_A.length * matrix_B[0].length) / threadCount + 1); i++) {
            for (int j = 0; j < threadCount; j++) {
                if (count >= 25) {
                    break;
                }
                work[j][i] = wp.get(count);
                count++;
            }
        }

        for (int i = 0; i < threadCount; i++) {
            threadPool.add(new WorkerThread(product, "isdgbiuljkadfjk", work[i]));
        }

        for (WorkerThread wt : threadPool) {
            wt.join();
        }

        System.out.println(Arrays.deepToString(product.getProductMatrix()));
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

    }

}

class Product {

    public int[][] product_matrix;

    public Product(int x, int y) {
        product_matrix = new int[x][y];
    }

    public int[][] getProductMatrix() {
        return product_matrix;
    }

    public synchronized void writeToProductMatrix(int x, int y, int value) {
        product_matrix[x][y] = value;
    }
}

class WorkPackage {

    int[] skalar_A;
    int[] skalar_B;
    int x;
    int y;

    public WorkPackage(int[] skalar_A, int[] skalar_B, int x, int y) {
        this.skalar_A = skalar_A;
        this.skalar_B = skalar_B;
        this.x = x;
        this.y = y;
    }
}


class WorkerThread extends Thread {

    private Product product_matrix;
    private int product;
    private String threadName;
    WorkPackage[] work;

    public WorkerThread(Product product, String threadName, WorkPackage[] work) throws Exception {
        super(threadName);
        this.threadName = threadName;
        this.work = work;
        this.product_matrix = product;

        start();
    }

    @Override
    public void run() {
        for (int j = 0; j < work.length; j++) {
            if (work[j] != null) {
                product = 0;
                for (int i = 0; i < work[j].skalar_A.length; i++) {
                    int backup = product;
                    product += work[j].skalar_A[i] * work[j].skalar_B[i];
                    //System.out.println(backup + " += " + work[j].skalar_A[i] + " * " + work[j].skalar_B[i] + " = " + product); 
                }
                //System.out.println(Arrays.toString(work[j].skalar_A) + "   " + Arrays.toString(work[j].skalar_B) + "   " + work[j].x + "  " + work[j].y + "  " + product);
                product_matrix.writeToProductMatrix(work[j].x, work[j].y, product);
            }
        }
    }
}

//[[21, -17, 19, -9, -8], [-2, 15, -9, 4, 9], [16, -25, 11, -2, -15], [15, 20, -1, -6, -9], [3, 10, -17, 6, 10]]
