package kiemtra_module2.filter;

import kiemtra_module2.model.Student;

@FunctionalInterface
public interface StudentFilter {
	boolean filter(Student s);
}