package kiemtra_module2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Student extends Person {
	protected double gpa;
	protected List<Course> enrolledCourses;

	public Student(String id, String name, String email, double gpa) {
		super(id, name, email);
		this.gpa = gpa;
		this.enrolledCourses = new ArrayList<>();

	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public List<Course> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void enrollCourse(Course course) {
		if (!enrolledCourses.contains(course)) {
			enrolledCourses.add(course);
		}
	}

	public void removeEnrolledCourses(Course course) {
		enrolledCourses.remove(course);
	}

	public double calculateAverageScore(Map<Course, Double> scores) {
		if (scores == null || scores.isEmpty()) {
			return 0;
		}
		return scores.values().stream()
				.mapToDouble(Double::doubleValue)
				.average()
				.orElse(0.0);

	}

	public abstract double calculateTuitionFee();

	@Override
	public String toString() {
		return "Student{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", gpa=" + gpa +
				", role=" + getRole() +
				", courses=" + enrolledCourses.size() +
				'}';
	}
}
