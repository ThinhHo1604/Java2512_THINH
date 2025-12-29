package buoi4.bai4;

import java.util.Arrays;
import java.util.Scanner;

public class bai4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập số lượng phần tử: ");
		int n = scanner.nextInt();
		int[] arr = new int[n];

		enterArray(arr, scanner);
		System.out.println("Mảng Gốc: ");
		printArray(arr);

		int max = findMax(arr);
		int min = findMin(arr);
		System.out.println("Max= " + max);
		System.out.println("Min= " + min);

		sortArray(arr);
		System.out.println("Mảng sau khi sắp xếp:");
		printArray(arr);
	}

	public static void enterArray(int[] arr, Scanner scanner) {
		System.out.println("Nhập vào một mảng gồm n só nguyên: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "] =");
			arr[i] = scanner.nextInt();
		}
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] + "");
		}
		System.out.println();
	}

	public static int findMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	public static int findMin(int[] arr) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		return min;
	}

	public static void sortArray(int[] arr) {
		Arrays.sort(arr);
	}
}
