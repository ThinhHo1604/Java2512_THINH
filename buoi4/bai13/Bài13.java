package buoi4.bai13;

public class Bài13 {
	public static void main(String[] args) {
		int[] numbers = { 10, 5, 20, 20, 8, 15 };
		findSecondLargest(numbers);
	}

	public static void findSecondLargest(int[] arr) {
		if (arr.length < 2) {
			System.out.println("mảng quán ngắn");
			return;
		}

		int max = Integer.MIN_VALUE;
		System.out.println(max);
		int seconmax = Integer.MIN_VALUE;

		for (int x : arr) {
			if (x > max) {
				seconmax = max;
				max = x;
			} else if (x > seconmax && x != max) {
				seconmax = x;
			}
		}
		if (seconmax == Integer.MIN_VALUE) {
			System.out.println("Không có số lớn thứ hai");
		} else {
			System.out.println("Số lớn thứ nhát: " + max);
			System.out.println("Số lớn thứ hai: " + seconmax);
		}

	}
}
