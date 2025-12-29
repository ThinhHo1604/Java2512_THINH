package buoi4.bai8;

import java.util.Scanner;

public class Bai8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] matrix = new int[3][3];

		enterMatrix(matrix, sc);
		System.out.println();
		printMatrix(matrix);

		int sum = sumMatrix(matrix);
		System.out.println("\nTổng các phần tử: "+ sum);
	};

	public static void enterMatrix(int[][] matrix, Scanner sc) {
		System.out.println("Nhập các phần tử của ma trận 3x3");

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				System.out.println("Nhập phần tử [" + row + "][" + col + "]:");
				matrix[row][col] = sc.nextInt();
			}
		}
	}

	public static void printMatrix(int[][] matrix) {
		System.out.println("Ma Trận");

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				System.out.print(matrix[row][col] + "\t");
			}
			System.out.println();
		}
	}
	
	public static int sumMatrix(int[][] matrix){
		int sum = 0;
		
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				sum += matrix[row][col];
			}
		}
		return sum;
	}
}
