package buoi6.kiemtra;

public class WarehouseManager {
	private Product[][] storage;
	private int[] counts;
	private final String[] categoryNames = {
			"Điện tử", "Gia dụng", "Thực phẩm", "Thời trang", "Khác"
	};

	public WarehouseManager() {
		storage = new Product[5][20];
		counts = new int[5];
	}

	public String addProduct(int categoryId, Product p) {
		StringBuilder sb = new StringBuilder();

		if (categoryId < 0 || categoryId >= 5) {
			return "Danh mục không hợp lệ!";
		}

		if (counts[categoryId] >= 20) {
			sb.append("Danh mục đã đầy, không thể thêm sản phẩm");
			return sb.toString();
		}

		for (int i = 0; i < counts[categoryId]; i++) {
			if (storage[categoryId][i].equals(p)) {
				sb.append("Sản phẩm đã tồn tại trong danh mục");
				return sb.toString();
			}
		}

		storage[categoryId][counts[categoryId]] = p;
		counts[categoryId]++;

		sb.append("Đã thêm sản phẩm ").append(p.getName())
				.append(" vào danh mục ").append(categoryId);
		return sb.toString();
	}

	public void searchByName(String keyword) {
		System.out.println("Kết quả tìm kiếm cho từ khóa: \"" + keyword + "\"");
		boolean found = false;
		String lowerKeyword = keyword.toLowerCase();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < counts[i]; j++) {
				String productName = storage[i][j].getName().toLowerCase();

				if (productName.contains(lowerKeyword)) {
					System.out.println(storage[i][j]);
					found = true;
				}
			}
		}

		if (!found) {
			System.out.println("Không tìm thấy sản phẩm phù hợp");
		}
	}

	public void deleteById(int id) {
		boolean found = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < counts[i]; j++) {
				if (storage[i][j].getId() == id) {

					for (int k = j; k < counts[i] - 1; k++) {
						storage[i][k] = storage[i][k + 1];
					}
					storage[i][counts[i] - 1] = null;
					counts[i]--;

					System.out.println("Xóa thành công!");
					found = true;
					return;
				}
			}
		}

		if (!found) {
			System.out.println("Không tìm thấy sản phẩm cần xóa");
		}
	}

	public void sortByPriceDescending() {
		int totalProducts = 0;
		for (int count : counts) {
			totalProducts += count;
		}

		if (totalProducts == 0) {
			System.out.println("Kho rỗng, không có gì để sắp xếp.");
			return;
		}

		Product[] temp = new Product[totalProducts];
		int index = 0;

		for (int i = 0; i < storage.length; i++) {
			for (int j = 0; j < counts[i]; j++) {
				temp[index++] = storage[i][j];
			}
		}

		quickSort(temp, 0, temp.length - 1);

		System.out.println("Danh sách sản phẩm sau khi sắp xếp theo giá giảm dần:");
		for (Product p : temp) {
			System.out.println(p);
		}
	}

	private void quickSort(Product[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	private int partition(Product[] arr, int low, int high) {
		double pivot = arr[high].getPrice();
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr[j].getPrice() >= pivot) {
				i++;
				Product temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		Product temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
	}

	public void showStatistics() {
		System.out.println("------------------------------------------");
		System.out.printf("%-10s | %-20s | %-15s\n", "Danh mục", "Tên danh mục", "Tổng giá trị");
		System.out.println("------------------------------------------");

		double maxTotal = -1;
		int maxCategoryId = -1;

		for (int i = 0; i < 5; i++) {
			double totalCategoryValue = 0;
			for (int j = 0; j < counts[i]; j++) {
				totalCategoryValue += storage[i][j].getPrice() * storage[i][j].getQuantity();
			}

			System.out.printf("%-10d | %-20s | %-15.0f\n", i, categoryNames[i], totalCategoryValue);

			if (totalCategoryValue > maxTotal) {
				maxTotal = totalCategoryValue;
				maxCategoryId = i;
			}
		}
		System.out.println("------------------------------------------");
		if (maxCategoryId != -1) {
			System.out.println(
					"Danh mục có giá trị lớn nhất: " + maxCategoryId + " (" + categoryNames[maxCategoryId] + ")");
		} else {
			System.out.println("Kho rỗng.");
		}
	}

	public void listAll() {
		boolean empty = true;
		for (int i = 0; i < 5; i++) {
			if (counts[i] > 0) {
				empty = false;
				System.out.println("Danh mục " + i + " (" + categoryNames[i] + "):");
				for (int j = 0; j < counts[i]; j++) {
					System.out.println("  " + storage[i][j].toString());
				}
			}
		}
		if (empty) {
			System.out.println("Kho hàng trống.");
		}
	}
}
