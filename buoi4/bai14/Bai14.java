package buoi4.bai14;

public class Bai14 {
	public static void main(String[] args) {
		String input = "Ho Quoc Thinh";
		countChar(input);
	}

	public static void countChar(String str){
		int [] counter= new int[256];

		for (int i= 0; i<str.length();i++){
			char c =str.charAt(i);
			counter[c]++;
		}

		for (int i= 0;i<256 ;i++){
			if(counter[i]>0){
			System.out.println("Ký tự '" + (char)i + "': " + counter[i] + " lần");
			}
		}
	}
}
