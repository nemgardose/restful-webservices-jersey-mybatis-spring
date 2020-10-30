package com.demo.rest.project.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.rest.project.training.models.Student;

public interface StudentDAO {
	
	int createStudentAccount(Student student);
	
	List<Student> getStudentList();
	Student getUserByStudentNumber(String studentNumber);
	
	int updateUserByStudentNumber(@Param("studentNumber") String studentNumber, @Param("student") Student student);
	
	int deleteUserByStudentNumber(String studentNumber);
	

}
