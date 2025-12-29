package buoi4.bai7;

public class Bai7 {
	public static void main(String[] args) {
		String s1 = "t3h";
		String s2 = new String("t3h");

		// So sánh bằng ==

		if (s1 == s2) {
			System.out.println("s1 == s2: true");
		} else {
			System.out.println("s1 == s2: false");
		}
		// So sánh bằng .equals()
		if (s1.equals(s2)) {
			System.out.println("s1.equals(s2): true");
		} else {
			System.out.println("s1.equals(s2): false");
		}
		// So sánh không phân biệt hoa/thường
		String str1 = "JAVA";
		String str2 = "java";

		System.out.println("\n----- Dùng .equalsIgnoreCase() (KHÔNG phân biệt hoa/thường) -----");
		if (str1.equalsIgnoreCase(str2)) {
			System.out.println("str1.equalsIgnoreCase(str2): true");
		} else {
			System.out.println("str1.equalsIgnoreCase(str2): false");
		}
	}

}
