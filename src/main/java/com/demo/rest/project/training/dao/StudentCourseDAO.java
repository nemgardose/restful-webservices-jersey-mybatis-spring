package com.demo.rest.project.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.rest.project.training.models.Course;
import com.demo.rest.project.training.models.Student;
import com.demo.rest.project.training.models.StudentCourse;

public interface StudentCourseDAO {
	
	int addCourseOnStudent(@Param("studentCourse") StudentCourse studentCourse);
	
	List<Course> getEnrolledStudentsByCourseId(@Param("courseId") String courseId);
	List<Student> getEnrolledCoursesByStudentNumber(@Param("studentNumber") String studentNumber);
	
	StudentCourse checkIfStudentIsAlreadyEnrolled(@Param("studentCourse") StudentCourse studentCourse);
}
