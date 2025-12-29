package buoi4.bai9;

public class Bai9 {
	public static void main(String[] args) {
		// Thử nghiệm các trường hợp
		System.out.println("Kết quả với 'T3H': " + checkName("T3H")); // true
		System.out.println("Kết quả với 'ABC': " + checkName("ABC")); // false
		System.out.println("Kết quả với null: " + checkName(null)); // false (Không bị lỗi!)

		System.out.println("TẠI SAO \"T3H\".equals(name) AN TOÀN?");
		System.out.println("========================================\n");

		System.out.println("1️⃣ \"T3H\" là HẰNG SỐ STRING:");
		System.out.println("   → Luôn tồn tại trong bộ nhớ");
		System.out.println("   → Không bao giờ = null\n");

		System.out.println("2️⃣ Method equals() của String đã xử lý null:");
		System.out.println("   → Nếu tham số = null → trả về false");
		System.out.println("   → Không ném Exception\n");
	}

	public static boolean checkName(String name) {
		return "T3H".equals(name);

	}

}
