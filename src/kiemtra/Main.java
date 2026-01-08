package buoi6.kiemtra;

public class Main {
	public static void main(String[] args) {
		WarehouseManager manager = new WarehouseManager();
		int choice;

		do {
			System.out.println("1. Thêm sản phẩm vào kho");
			System.out.println("2. Tìm kiếm sản phẩm theo tên");
			System.out.println("3. Xóa sản phẩm theo ID");
			System.out.println("4. Sắp xếp toàn bộ kho theo giá giảm dần");
			System.out.println("5. Thống kê dạng ma trận");
			System.out.println("6. Hiển thị tất cả sản phẩm");
			System.out.println("0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");

			try {
				choice = Integer.parseInt(Utils.sc.nextLine());
			} catch (NumberFormatException e) {
				choice = -1;
			}

			switch (choice) {
				case 1:
					System.out.print("Nhập mã danh mục (0-4): ");
					int categoryId = Integer.parseInt(Utils.sc.nextLine());

					System.out.print("Nhập ID sản phẩm: ");
					int id = Integer.parseInt(Utils.sc.nextLine());

					System.out.print("Nhập tên sản phẩm: ");
					String name = Utils.formatName(Utils.sc.nextLine());

					System.out.print("Nhập giá sản phẩm: ");
					double price = Double.parseDouble(Utils.sc.nextLine());

					System.out.print("Nhập số lượng: ");
					int quantity = Integer.parseInt(Utils.sc.nextLine());

					Product newProduct = new Product(id, name, price, quantity);
					System.out.println(manager.addProduct(categoryId, newProduct));
					break;

				case 2:
					System.out.print("Nhập từ khóa tìm kiếm: ");
					manager.searchByName(Utils.sc.nextLine());
					break;

				case 3:
					System.out.print("Nhập ID sản phẩm cần xóa: ");
					manager.deleteById(Integer.parseInt(Utils.sc.nextLine()));
					break;

				case 4:
					manager.sortByPriceDescending();
					break;

				case 5:
					manager.showStatistics();
					break;

				case 6:
					manager.listAll();
					break;

				case 0:
					System.out.println("Thoát chương trình");
					break;

				default:
					System.out.println("Lựa chọn không hợp lệ!");
			}
		} while (choice != 0);
	}
}
