package kiemtra_module2.model;

import java.util.Objects;

public class Course {
	private String courseId;
	private String courseName;
	private int credits;

	public Course(String courseId, String courseName, int credits) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.credits = credits;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getCredits() {
		return credits;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Course course = (Course) obj;
		return Objects.equals(courseId, course.courseId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId);
	}

	@Override
	public String toString() {
		return "Course{" +
				"courseId='" + courseId + '\'' +
				", courseName='" + courseName + '\'' +
				", credits=" + credits +
				'}';
	}
}
