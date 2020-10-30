package com.demo.rest.project.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.rest.project.training.models.Teacher;

public interface TeacherDAO {
	
	int createTeacherAccount(Teacher teacher);
	
	List<Teacher> getTeachersList();
	Teacher getUserByEmployeeId(String employeeId);
	
	int updateUserByEmployeeId(@Param("employeeId") String employeeId, @Param("teacher") Teacher teacher);
	int assignCourseOnEmployee(@Param("employeeId") String employeeId, @Param("teacher") Teacher teacher);
	
	int deleteUserByEmployeeId(String employeeId);
	
	
	
}
