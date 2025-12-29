package buoi4.bai15;

public class Bai15 {
	public static void main(String[] args) {
		int[][] matrix = {
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 },
		};
		int n = 3;
		tinhTongDuongCheo(matrix, n);
	}

	public static void tinhTongDuongCheo(int[][] matrix, int n) {
		int tongChinh = 0;
		int tongPhu = 0;

		for (int i = 0; i < n; i++) {
			tongChinh += matrix[i][i];
			tongPhu += matrix[i][n - 1 - i];
		}
		System.out.println("Ma trận " + n + "x" + n + ":");
		System.out.println("Tổng các phần tử trên đường chéo chính: " + tongChinh);
		System.out.println("Tổng các phần tử trên đường chéo phụ: " + tongPhu);
	}
}
