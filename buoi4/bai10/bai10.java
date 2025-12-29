package buoi4.bai10;

public class bai10 {
	private static final int SO_LAN_NOI = 10000;

	public static String noiChuoibangString() {
		String result = "";
		for (int i = 0; i < SO_LAN_NOI; i++) {
			result = result + "A";

		}
		return result;
	}

	public static String noiChuoiBangStringBuilder() {
		StringBuilder sb = new StringBuilder(); 

		// Nối 10,000 ký tự 'A'
		for (int i = 0; i < SO_LAN_NOI; i++) {
			sb.append("A"); 
		}

		return sb.toString(); 
	}

	public static void main(String[] args) {
		System.out.println("📚 GIẢI THÍCH: TẠI SAO STRING CHẬM?");
		System.out.println("----------------------------------------");
		System.out.println("   s = s + \"A\";  // Tạo đối tượng MỚI!");
		System.out.println("Điều gì xảy ra khi nối chuỗi?");
		System.out.println("   1. Đọc nội dung String cũ");
		System.out.println("   2. Tạo vùng nhớ MỚI");
		System.out.println("   3. Copy String cũ + String mới vào vùng mới");
		System.out.println("   4. Vùng nhớ cũ thành 'rác' → chờ GC dọn");
		System.out.println("----------------------------------------");
		System.out.println(noiChuoibangString());
		System.out.println("----------------------------------------");
		System.out.println("NỐI BẰNG STRINGBUILDER (NHANH)");
		System.out.println("========================================");
		System.out.println("   ✅ Thêm trực tiếp vào buffer");
		System.out.println("   ✅ Không tạo đối tượng mới");
		System.out.println("   ✅ Nhanh gấp hàng NGÀN lần!\n");
		System.out.println(noiChuoiBangStringBuilder());
		System.out.println("========================================");
		System.out.println("NỐI BẰNG STRINGBUFFER (ĐA LUỒNG)");
		System.out.println("========================================");
		System.out.println("   ✅ Thread-safe (synchronized)");
		System.out.println("   ⚠️  Chậm hơn StringBuilder chút (do lock)");
		System.out.println("   ✅ Dùng khi nhiều thread cùng sửa\n");

	}
}
