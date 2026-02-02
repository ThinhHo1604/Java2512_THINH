package kiemtra_module2.thread;

import java.util.List;
import java.util.Random;

import kiemtra_module2.exception.InvalidScoreException;
import kiemtra_module2.model.Course;
import kiemtra_module2.service.StudentManagement;

public class ScoreInputTask implements Runnable {
	private List<String> studentIds;
	private List<String> courseIds;
	private StudentManagement management;

	public ScoreInputTask(List<String> studentIds, List<String> courseIds, StudentManagement management) {
		this.studentIds = studentIds;
		this.courseIds = courseIds;
		this.management = management;
	}

	@Override
    public void run() {
        Random random = new Random();
        String threadName = Thread.currentThread().getName();

        for (String studentId : studentIds) {
            management.getStudentRepository().findById(studentId).ifPresent(student -> {
                for (Course course : student.getEnrolledCourses()) {
                    try {
                        double score = Math.round(random.nextDouble() * 100) / 10.0; // 0.0 - 10.0
                        management.safeInputScore(studentId, course.getCourseId(), score);
                        System.out.println("[" + threadName + "] Nhập điểm cho " 
                                + student.getName() + " - " + course.getCourseName() 
                                + ": " + score);
                        
                        // Giả lập thời gian xử lý
                        Thread.sleep(100);
                    } catch (InvalidScoreException | InterruptedException e) {
                        System.err.println("[" + threadName + "] Lỗi: " + e.getMessage());
                    }
                }
            });
        }

        System.out.println("[" + threadName + "] Hoàn thành!");
    }
}