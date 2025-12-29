package buoi4.bai2;

import java.util.Scanner;

public class Bai2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập chuỗi từ bàn phím: ");
		String str = sc.nextLine();
		System.out.println("Độ dài chuỗi gốc: " + str.length());

		String UpperStr = str.toUpperCase();
		System.out.println("Chuyển sang chữ hoa: " + UpperStr);

		String lowerStr = str.toLowerCase();
		System.out.println("Chuyển sang chuỗi thường: " + lowerStr);

		String trimStr = str.trim();
		System.out.println("Xóa khoảng trắng: " + trimStr);

		System.out.println("Độ dài mới không có khoảng trắng: " + trimStr.length());
	}
}
