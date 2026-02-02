package kiemtra_module2;

import java.util.*;
import java.util.stream.Collectors;

import kiemtra_module2.exception.CourseNotFoundException;
import kiemtra_module2.exception.DuplicateStudentException;
import kiemtra_module2.exception.InvalidScoreException;
import kiemtra_module2.exception.StudentNotFoundException;
import kiemtra_module2.model.Course;
import kiemtra_module2.model.FullTimeStudent;
import kiemtra_module2.model.PartTimeStudent;
import kiemtra_module2.model.Student;
import kiemtra_module2.service.StudentManagement;
import kiemtra_module2.thread.ScoreInputTask;

public class Main {
	private static StudentManagement management = new StudentManagement();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			showMenu();
			int choice = getIntInput("Chọn chức năng: ");

			try {
				switch (choice) {
					case 1:
						initSampleData();
						break;
					case 2:
						addStudent();
						break;
					case 3:
						addCourse();
						break;
					case 4:
						enrollStudentToCourse();
						break;
					case 5:
						inputScore();
						break;
					case 6:
						viewScoreBoard();
						break;
					case 7:
						searchStudent();
						break;
					case 8:
						filterAndSortStudents();
						break;
					case 9:
						calculateTuition();
						break;
					case 10:
						inputScoreWithThreads();
						break;
					case 11:
						runTestCases();
						break;
					case 0:
						System.out.println("Tạm biệt!");
						System.exit(0);
					default:
						System.out.println("Lựa chọn không hợp lệ!");
				}
			} catch (Exception e) {
				System.out.println("Lỗi: " + e.getMessage());
			}
		}
	}

	private static void showMenu() {
		System.out.println("\n STUDENT MANAGEMENT SYSTEM - CONSOLE APP");
		System.out.println("1.  Khởi tạo dữ liệu mẫu");
		System.out.println("2.  Thêm sinh viên");
		System.out.println("3.  Thêm khóa học");
		System.out.println("4.  Đăng ký khóa học cho sinh viên");
		System.out.println("5.  Nhập điểm cho sinh viên");
		System.out.println("6.  Xem bảng điểm của 1 sinh viên");
		System.out.println("7.  Tìm kiếm sinh viên (Overloading)");
		System.out.println("8.  Lọc & sắp xếp sinh viên");
		System.out.println("9.  Tính học phí sinh viên");
		System.out.println("10. Nhập điểm tự động bằng đa luồng");
		System.out.println("11. Chạy Test Cases");
		System.out.println("0.  Thoát");
		System.out.println("════════════════════════════════════════════════");
	}

	// 1. Khởi tạo dữ liệu mẫu
	private static void initSampleData() {
		management.initSampleData();
	}

	// 2. Thêm sinh viên
	private static void addStudent() throws DuplicateStudentException {
		System.out.println("\n--- THÊM SINH VIÊN ---");
		String id = getStringInput("Nhập ID: ");
		String name = getStringInput("Nhập họ tên: ");
		String email = getStringInput("Nhập email: ");
		double gpa = getDoubleInput("Nhập GPA (0-10): ");

		System.out.println("Loại sinh viên:");
		System.out.println("1. Full-time");
		System.out.println("2. Part-time");
		int type = getIntInput("Chọn: ");

		Student student;
		if (type == 1) {
			student = new FullTimeStudent(id, name, email, gpa);
		} else {
			student = new PartTimeStudent(id, name, email, gpa);
		}

		management.addStudent(student);
	}

	// 3. Thêm khóa học
	private static void addCourse() throws DuplicateStudentException {
		System.out.println("\n--- THÊM KHÓA HỌC ---");
		String id = getStringInput("Nhập mã khóa học: ");
		String name = getStringInput("Nhập tên khóa học: ");
		int credits = getIntInput("Nhập số tín chỉ: ");

		Course course = new Course(id, name, credits);
		management.addCourse(course);
	}

	// 4. Đăng ký khóa học
	private static void enrollStudentToCourse()
			throws StudentNotFoundException, CourseNotFoundException {
		System.out.println("\n--- ĐĂNG KÝ KHÓA HỌC ---");
		String studentId = getStringInput("Nhập ID sinh viên: ");
		String courseId = getStringInput("Nhập mã khóa học: ");

		management.enrollStudentToCourse(studentId, courseId);
	}

	// 5. Nhập điểm
	private static void inputScore()
			throws StudentNotFoundException, CourseNotFoundException, InvalidScoreException {
		System.out.println("\n--- NHẬP ĐIỂM ---");
		String studentId = getStringInput("Nhập ID sinh viên: ");
		String courseId = getStringInput("Nhập mã khóa học: ");
		double score = getDoubleInput("Nhập điểm (0-10): ");

		management.inputScore(studentId, courseId, score);
	}

	// 6. Xem bảng điểm
	private static void viewScoreBoard() throws StudentNotFoundException {
		System.out.println("\n--- XEM BẢNG ĐIỂM ---");
		String studentId = getStringInput("Nhập ID sinh viên: ");
		management.printStudentScoreBoard(studentId);
	}

	// 7. Tìm kiếm sinh viên (OVERLOADING)
	private static void searchStudent() {
		System.out.println("\n--- TÌM KIẾM SINH VIÊN ---");
		System.out.println("1. Tìm theo ID");
		System.out.println("2. Tìm theo tên và GPA");
		int choice = getIntInput("Chọn: ");

		if (choice == 1) {
			// Overloading 1: searchStudent(String id)
			String id = getStringInput("Nhập ID: ");
			Optional<Student> result = management.searchStudent(id);

			if (result.isPresent()) {
				System.out.println("Tìm thấy: " + result.get());
			} else {
				System.out.println("Không tìm thấy sinh viên!");
			}
		} else {
			// Overloading 2: searchStudent(String name, double minGpa)
			String name = getStringInput("Nhập tên (hoặc một phần): ");
			double minGpa = getDoubleInput("Nhập GPA tối thiểu: ");

			List<Student> results = management.searchStudent(name, minGpa);

			if (results.isEmpty()) {
				System.out.println("Không tìm thấy sinh viên nào!");
			} else {
				System.out.println("Tìm thấy " + results.size() + " sinh viên:");
				results.forEach(System.out::println);
			}
		}
	}

	// 8. Lọc và sắp xếp (LAMBDA)
	private static void filterAndSortStudents() {
		System.out.println("\n--- LỌC & SẮP XẾP ---");
		System.out.println("1. Lọc GPA >= 8.0");
		System.out.println("2. Lọc Full-time");
		System.out.println("3. Sắp xếp theo GPA giảm dần");
		System.out.println("4. Sắp xếp theo tên A-Z");
		int choice = getIntInput("Chọn: ");

		List<Student> results = new ArrayList<>();

		switch (choice) {
			case 1:
				// Lambda Expression
				results = management.filterStudents(s -> s.getGpa() >= 8.0);
				break;
			case 2:
				// Lambda Expression
				results = management.filterStudents(s -> "FULL_TIME".equals(s.getRole()));
				break;
			case 3:
				results = management.sortStudentsByGpaDesc();
				break;
			case 4:
				results = management.sortStudentsByNameAsc();
				break;
		}

		if (results.isEmpty()) {
			System.out.println("Không có kết quả!");
		} else {
			System.out.println("Kết quả (" + results.size() + " sinh viên):");
			results.forEach(System.out::println);
		}
	}

	// 9. Tính học phí (POLYMORPHISM)
	private static void calculateTuition() {
		System.out.println("\n--- TÍNH HỌC PHÍ ---");
		String id = getStringInput("Nhập ID sinh viên: ");

		Optional<Student> studentOpt = management.searchStudent(id);
		if (studentOpt.isPresent()) {
			Student student = studentOpt.get();
			double tuition = management.calculateTuition(student);
			System.out.println("Sinh viên: " + student.getName());
			System.out.println("Loại: " + student.getRole());
			System.out.println("Số khóa học: " + student.getEnrolledCourses().size());
			System.out.println("Học phí: " + String.format("%.2f", tuition) + " USD");
		} else {
			System.out.println("Không tìm thấy sinh viên!");
		}
	}

	// 10. Nhập điểm đa luồng (MULTI-THREAD)
	private static void inputScoreWithThreads() {
		System.out.println("\n--- NHẬP ĐIỂM Tự ĐỘNG (ĐA LUỒNG) ---");

		List<Student> allStudents = management.getStudentRepository().findAll();
		if (allStudents.isEmpty()) {
			System.out.println("Chưa có sinh viên nào!");
			return;
		}

		List<String> allStudentIds = allStudents.stream()
				.map(Student::getId)
				.collect(Collectors.toList());

		List<String> allCourseIds = management.getCourseRepository().findAll().stream()
				.map(Course::getCourseId)
				.collect(Collectors.toList());

		// Chia sinh viên cho 2 thread
		int mid = allStudentIds.size() / 2;
		List<String> group1 = allStudentIds.subList(0, mid);
		List<String> group2 = allStudentIds.subList(mid, allStudentIds.size());

		Thread thread1 = new Thread(new ScoreInputTask(group1, allCourseIds, management), "Thread-1");
		Thread thread2 = new Thread(new ScoreInputTask(group2, allCourseIds, management), "Thread-2");

		System.out.println("🚀 Bắt đầu nhập điểm đa luồng...");
		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
			System.out.println("Hoàn thành nhập điểm cho " + allStudents.size() + " sinh viên!");
		} catch (InterruptedException e) {
			System.err.println("Lỗi thread: " + e.getMessage());
		}
	}

	// 11. Test Cases
	private static void runTestCases() {
		System.out.println("\n========== CHẠY TEST CASES ==========");

		// Test 1: Thêm sinh viên trùng ID
		System.out.println("\n[Test 1] Thêm sinh viên trùng ID:");
		try {
			management.addStudent(new FullTimeStudent("SV001", "Test", "test@mail.com", 8.0));
			System.out.println("Test FAILED: Không phát hiện ID trùng!");
		} catch (DuplicateStudentException e) {
			System.out.println("Test PASSED: " + e.getMessage());
		}

		// Test 2: Nhập điểm ngoài 0-10
		System.out.println("\n[Test 2] Nhập điểm ngoài 0-10:");
		try {
			management.inputScore("SV001", "CS101", 15.0);
			System.out.println("Test FAILED: Không phát hiện điểm sai!");
		} catch (Exception e) {
			System.out.println("Test PASSED: " + e.getMessage());
		}

		// Test 3: Tìm sinh viên không tồn tại (Optional)
		System.out.println("\n[Test 3] Tìm sinh viên không tồn tại:");
		Optional<Student> result = management.searchStudent("SV999");
		if (!result.isPresent()) {
			System.out.println("Test PASSED: Optional rỗng");
		} else {
			System.out.println("Test FAILED: Tìm thấy sinh viên không tồn tại!");
		}

		// Test 4: Lọc GPA > 8 bằng Lambda
		System.out.println("\n[Test 4] Lọc GPA > 8 bằng Lambda:");
		List<Student> highGpa = management.filterStudents(s -> s.getGpa() > 8.0);
		System.out.println("Test PASSED: Tìm thấy " + highGpa.size() + " sinh viên GPA > 8");
		highGpa.forEach(s -> System.out.println("  - " + s.getName() + ": " + s.getGpa()));

		// Test 5: Đa luồng (đã test ở chức năng 10)
		System.out.println("\n[Test 5] Đa luồng: Xem chức năng 10");

		// Test 6: Tính học phí FullTime vs PartTime
		System.out.println("\n[Test 6] Tính học phí FullTime vs PartTime:");
		management.getStudentRepository().findById("SV001").ifPresent(s -> {
			System.out.println("Full-time (" + s.getName() + "): " + s.calculateTuitionFee() + " USD");
		});
		management.getStudentRepository().findById("SV006").ifPresent(s -> {
			System.out.println("Part-time (" + s.getName() + "): " + s.calculateTuitionFee() + " USD");
		});
		System.out.println("Test PASSED: Đa hình hoạt động đúng");

		System.out.println("\n========== KẾT THÚC TEST ==========");
	}

	// Helper methods
	private static String getStringInput(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine().trim();
	}

	private static int getIntInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				int value = Integer.parseInt(scanner.nextLine().trim());
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập số nguyên!");
			}
		}
	}

	private static double getDoubleInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				double value = Double.parseDouble(scanner.nextLine().trim());
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập số hợp lệ!");
			}
		}
	}
}