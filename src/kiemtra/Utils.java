package buoi6.kiemtra;

import java.util.Scanner;

public class Utils {

	public static final Scanner sc = new Scanner(System.in);

	public static String formatName(String name) {
		if (name == null || name.trim().isEmpty()) {
			return "";
		}
		name = name.trim();
		StringBuilder res = new StringBuilder();
		String[] words = name.split("\\s+");

		for (String word : words) {
			word.substring(0, 1).toUpperCase().substring(1).toLowerCase();
			res.append(word).append(" ");
		}
		return res.toString().trim();
	}
}
