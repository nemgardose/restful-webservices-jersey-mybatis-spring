package com.demo.rest.project.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.rest.project.training.models.Course;
import com.demo.rest.project.training.models.Teacher;

public interface CourseDAO {
	
	int createCourse(Course course);
	
	List<Course> getCourses();
	Course getCourseByCode(String courseCode);
	List<Teacher> getAssignedTeacher(@Param("courseId") long courseId);
	
	int updateCourseByCode(@Param("courseCode") String courseCode, @Param("course") Course course);
	
	int deleteCourseByCode(String code);
	
	Course findCourseByid(long courseId);
}
