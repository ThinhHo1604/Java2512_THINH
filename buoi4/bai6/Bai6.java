package buoi4.bai6;

public class Bai6 {
	public static void main(String[] args) {
		String sentence = "Học viện công nghệ thông tin T3H";

		int position = sentence.indexOf("T3H");
		System.out.println("Vị trí của T3H: " + position);

		String result = sentence.substring(position);
		System.out.println("Cắt và lấy ra T3H: " + result);

		char ch = sentence.charAt(5);
		System.out.println("ký tự tại vị trí index thứ 5 của chuỗi: "+ch);
		
	}
}
