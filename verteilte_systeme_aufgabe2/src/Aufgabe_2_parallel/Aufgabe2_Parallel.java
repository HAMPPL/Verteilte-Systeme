/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe_2_parallel;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Aufgabe2_Parallel {

    public static void main(String[] args) {

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

        Semaphore sem = new Semaphore(0);
        sem.release();
        Matrix matrix = new Matrix(matrix_A, matrix_B);
        Product product = new Product(matrix_A.length, matrix_B[0].length);
        
        while(!matrix.isFinished()) {
            new CalcThread(matrix, product, sem);
        }
        System.out.println(Arrays.deepToString(product.getProductMatrix()));
        
        

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

class Calc {

    int[] skalar_A;
    int[] skalar_B;
    int x;
    int y;

    public Calc(int[] skalar_A, int[] skalar_B, int x, int y) {
        this.skalar_A = skalar_A;
        this.skalar_B = skalar_B;
        this.x = x;
        this.y = y;
    }
}


class Matrix {

    private int[][] matrix_A;
    private int[][] matrix_B;
    private boolean finished;
    int i;
    int j;

    public Matrix(int[][] matrix_A, int[][] matrix_B) {
        this.matrix_A = matrix_A;
        this.matrix_B = matrix_B;
        finished = false;
        i = 0;
        j = 0;
    }

    public Calc getNextCalc() {
        int[] flipped_matrix = {matrix_B[0][j],matrix_B[1][j],matrix_B[2][j],matrix_B[3][j],matrix_B[4][j]};
        Calc calc = new Calc(matrix_A[i], flipped_matrix, i, j); 
        if(!(j < (matrix_B[0].length -1))) {
            if(i >= (matrix_A.length -1)) {
                finished = true;
                
            } else {
                i++;
                j = 0;  
            }
        }else {
            j++;
        }
        return calc;
    }

    public boolean isFinished() {
        return finished;
    }

}

class CalcThread extends Thread {

    Matrix matrix;
    Product product;
    Semaphore sem;

    public CalcThread(Matrix matrix, Product product, Semaphore sem) {
        this.matrix = matrix;
        this.product = product;
        this.sem = sem;
        start();
    }

    @Override
    public void run() {
        
        while (!matrix.isFinished()) {
            
            try {
                sem.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Aufgabe2_Parallel.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calc calc = matrix.getNextCalc();
            sem.release();
            int[] skalar_A = calc.skalar_A;
            int[] skalar_B = calc.skalar_B;

            int result = 0;
            for (int i = 0; i < skalar_A.length; i++) {
                result += skalar_A[i] * skalar_B[i];
            }
            product.writeToProductMatrix(calc.x, calc.y, result);
        }
    }
}