package buoi4.bai12;

public class Bai12 {
	public static void main(String[] args) {
		String s1 = "radar";
		String s2 = "madam";

		System.out.println("'" + s1 + "' có đối xứng không? " + isPalindrome(s1));
		System.out.println("'" + s2 + "' có đối xứng không? " + isPalindrome(s2));
	}

	public static boolean isPalindrome(String str) {
		char[] arr = str.toCharArray();
		int n = arr.length;

		for (int i = 0; i < n / 2; i++) {
			int j = n - 1 - i;

			if (arr[i] != arr[j]) {
				System.out.println("Không đối xứng");
				return false;
			}
		}
		System.out.println("Đói xứng");
		return true;
	}
}