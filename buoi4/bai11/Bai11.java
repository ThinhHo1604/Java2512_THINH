package buoi4.bai11;

public class Bai11 {
	public static void main(String[] args) {
		int[] number = { 1, 2, 3, 4, 5, 6,7 };

		System.out.println("Mảng trước khi đảo");
		printArrray(number);

		reverseArray(number);

		System.out.println("Mảng sau khi đảo");
		printArrray(number);
	}

	public static void reverseArray(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n / 2; i++) {
			int j = n - 1 - i;

			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	public static void printArrray(int[] arr) {
		for (int x : arr) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
}