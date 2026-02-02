package kiemtra_module2.model;

public class FullTimeStudent extends Student {
	private static final double PRICE_PER_CREDIT = 300.0;
	
	public FullTimeStudent(String id, String name, String email, double gpa) {
		super(id, name, email, gpa);
	}

	@Override
	public String getRole() {
		return "FULL_TIME";
	}

	@Override
	public double calculateTuitionFee() {
		int totalCredits = getEnrolledCourses().stream()
				.mapToInt(Course::getCredits)
				.sum();
		return totalCredits * PRICE_PER_CREDIT;
	}
}
