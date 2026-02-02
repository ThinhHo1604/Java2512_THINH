package kiemtra_module2.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import kiemtra_module2.exception.CourseNotFoundException;
import kiemtra_module2.exception.DuplicateStudentException;
import kiemtra_module2.exception.InvalidScoreException;
import kiemtra_module2.exception.StudentNotFoundException;
import kiemtra_module2.filter.StudentFilter;
import kiemtra_module2.model.Course;
import kiemtra_module2.model.FullTimeStudent;
import kiemtra_module2.model.PartTimeStudent;
import kiemtra_module2.model.Student;
import kiemtra_module2.repository.Repository;

public class StudentManagement {
	private Repository<Student> studentRepository;
	private Repository<Course> courseRepository;
	private Map<String, Map<String, Double>> scoreBoard;

	public StudentManagement() {
		this.studentRepository = new Repository<>();
		this.courseRepository = new Repository<>();
		this.scoreBoard = new HashMap<>();
	}

	// Khởi tạo dữ liệu mẫu
	public void initSampleData() {
		// Tạo 5 khóa học
		Course[] courses = {
				new Course("CS101", "Java Programming", 3),
				new Course("CS102", "Data Structures", 4),
				new Course("CS103", "Database", 3),
				new Course("CS104", "Web Development", 4),
				new Course("CS105", "Mobile App", 3)
		};

		for (Course course : courses) {
			courseRepository.add(course.getCourseId(), course);
		}

		// Tạo 10 sinh viên
		Student[] students = {
				new FullTimeStudent("SV001", "Nguyen Van A", "a@email.com", 8.5),
				new FullTimeStudent("SV002", "Tran Thi B", "b@email.com", 7.8),
				new FullTimeStudent("SV003", "Le Van C", "c@email.com", 9.0),
				new FullTimeStudent("SV004", "Pham Thi D", "d@email.com", 7.5),
				new FullTimeStudent("SV005", "Hoang Van E", "e@email.com", 8.2),
				new PartTimeStudent("SV006", "Vo Thi F", "f@email.com", 7.0),
				new PartTimeStudent("SV007", "Dang Van G", "g@email.com", 8.8),
				new PartTimeStudent("SV008", "Bui Thi H", "h@email.com", 6.5),
				new PartTimeStudent("SV009", "Do Van I", "i@email.com", 9.2),
				new PartTimeStudent("SV010", "Nguyen Thi K", "k@email.com", 7.9)
		};

		Random random = new Random();
		for (Student student : students) {
			studentRepository.add(student.getId(), student);

			// Đăng ký ngẫu nhiên 2-3 khóa học
			int numCourses = 2 + random.nextInt(2); // 2 hoặc 3
			Set<Integer> selectedIndexes = new HashSet<>();

			while (selectedIndexes.size() < numCourses) {
				selectedIndexes.add(random.nextInt(courses.length));
			}

			for (int idx : selectedIndexes) {
				student.enrollCourse(courses[idx]);
			}
		}

		System.out.println("Đã khởi tạo " + students.length + " sinh viên và " + courses.length + " khóa học!");
	}

	// Thêm sinh viên
	public void addStudent(Student student) throws DuplicateStudentException {
		if (studentRepository.exists(student.getId())) {
			throw new DuplicateStudentException("Sinh viên với ID " + student.getId() + " đã tồn tại!");
		}
		studentRepository.add(student.getId(), student);
		System.out.println("Thêm sinh viên thành công!");
	}

	// Thêm khóa học
	public void addCourse(Course course) throws DuplicateStudentException {
		if (courseRepository.exists(course.getCourseId())) {
			throw new DuplicateStudentException("Khóa học với ID " + course.getCourseId() + " đã tồn tại!");
		}
		courseRepository.add(course.getCourseId(), course);
		System.out.println("Thêm khóa học thành công!");
	}

	// Đăng ký khóa học cho sinh viên
	public void enrollStudentToCourse(String studentId, String courseId)
			throws StudentNotFoundException, CourseNotFoundException {
		Optional<Student> studentOpt = studentRepository.findById(studentId);
		if (!studentOpt.isPresent()) {
			throw new StudentNotFoundException("Không tìm thấy sinh viên ID: " + studentId);
		}

		Optional<Course> courseOpt = courseRepository.findById(courseId);
		if (!courseOpt.isPresent()) {
			throw new CourseNotFoundException("Không tìm thấy khóa học ID: " + courseId);
		}

		Student student = studentOpt.get();
		Course course = courseOpt.get();
		student.enrollCourse(course);
		System.out.println("Đăng ký khóa học thành công!");
	}

	// Nhập điểm cho sinh viên
	public void inputScore(String studentId, String courseId, double score)
			throws StudentNotFoundException, CourseNotFoundException, InvalidScoreException {
		if (!studentRepository.exists(studentId)) {
			throw new StudentNotFoundException("Không tìm thấy sinh viên ID: " + studentId);
		}
		if (!courseRepository.exists(courseId)) {
			throw new CourseNotFoundException("Không tìm thấy khóa học ID: " + courseId);
		}
		if (score < 0 || score > 10) {
			throw new InvalidScoreException("Điểm phải nằm trong khoảng 0-10!");
		}

		scoreBoard.putIfAbsent(studentId, new HashMap<>());
		scoreBoard.get(studentId).put(courseId, score);
		System.out.println("Nhập điểm thành công!");
	}

	// Nhập điểm thread-safe
	public synchronized void safeInputScore(String studentId, String courseId, double score)
			throws InvalidScoreException {
		if (score < 0 || score > 10) {
			throw new InvalidScoreException("Điểm phải nằm trong khoảng 0-10!");
		}

		scoreBoard.putIfAbsent(studentId, new HashMap<>());
		scoreBoard.get(studentId).put(courseId, score);
	}

	// In bảng điểm sinh viên
	public void printStudentScoreBoard(String studentId) throws StudentNotFoundException {
		Optional<Student> studentOpt = studentRepository.findById(studentId);
		if (!studentOpt.isPresent()) {
			throw new StudentNotFoundException("Không tìm thấy sinh viên ID: " + studentId);
		}

		Student student = studentOpt.get();
		System.out.println("Sinh viên: " + student.getName() + " (" + student.getId() + ")");

		Map<String, Double> scores = scoreBoard.get(studentId);
		if (scores == null || scores.isEmpty()) {
			System.out.println("Chưa có điểm!");
			return;
		}

		double total = 0;
		int count = 0;

		for (Map.Entry<String, Double> entry : scores.entrySet()) {
			Optional<Course> courseOpt = courseRepository.findById(entry.getKey());
			String courseName = courseOpt.isPresent() ? courseOpt.get().getCourseName() : "N/A";
			System.out.println(courseName + " (" + entry.getKey() + "): " + entry.getValue());
			total += entry.getValue();
			count++;
		}

		double average = count > 0 ? total / count : 0;
		System.out.println("Điểm trung bình: " + String.format("%.2f", average));	}

	public Optional<Student> searchStudent(String id) {
		return studentRepository.findById(id);
	}

	// OVERLOADING: Tìm sinh viên theo tên và GPA tối thiểu
	public List<Student> searchStudent(String name, double minGpa) {
		return studentRepository.findAll().stream()
				.filter(s -> s.getName().toLowerCase().contains(name.toLowerCase())
						&& s.getGpa() >= minGpa)
				.collect(Collectors.toList());
	}

	// Tính học phí
	public double calculateTuition(Student student) {
		return student.calculateTuitionFee();
	}

	// Lọc sinh viên bằng Lambda
	public List<Student> filterStudents(StudentFilter filter) {
		return studentRepository.findAll().stream()
				.filter(filter::filter)
				.collect(Collectors.toList());
	}

	// Sắp xếp theo GPA giảm dần
	public List<Student> sortStudentsByGpaDesc() {
		return studentRepository.findAll().stream()
				.sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()))
				.collect(Collectors.toList());
	}

	// Sắp xếp theo tên A-Z
	public List<Student> sortStudentsByNameAsc() {
		return studentRepository.findAll().stream()
				.sorted(Comparator.comparing(Student::getName))
				.collect(Collectors.toList());
	}

	// Getter cho repository
	public Repository<Student> getStudentRepository() {
		return studentRepository;
	}

	public Repository<Course> getCourseRepository() {
		return courseRepository;
	}

	public Map<String, Map<String, Double>> getScoreBoard() {
        return scoreBoard;
    }
}