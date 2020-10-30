package com.demo.rest.project.training.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.rest.project.training.dao.StudentCourseDAO;
import com.demo.rest.project.training.models.Course;
import com.demo.rest.project.training.models.Student;
import com.demo.rest.project.training.models.StudentCourse;

@Component
@Path("/student-course")
public class StudentCourseController {
	
	@Autowired
	StudentCourseDAO studentCourseDAO;
	
	/************************************ READ ************************************/
	@GET
	@Path("/enrolled/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnrolledStudentsByCourseId(@PathParam("courseId") String courseId) {
		List<Course> course = studentCourseDAO.getEnrolledStudentsByCourseId(courseId);
		
		if(course == null) {
			return Response.status(404).entity("Course Not Found").build();
		} else {
			return Response.status(200).entity(course).build();
		}
	}
	
	@GET
	@Path("/enrolled-subjects/{studentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnrolledCoursesByStudentNumber(@PathParam("studentNumber") String studentNumber) {
		List<Student> student = studentCourseDAO.getEnrolledCoursesByStudentNumber(studentNumber);
		
		if(student == null) {
			return Response.status(404).entity("Student Account Not Found").build();
		} else {
			return Response.status(200).entity(student).build();
		}
	}
	
	/************************************ CREATE ************************************/
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourseOnStudent(StudentCourse studentCourse) {
		
		StudentCourse checkIfAlreadyEnrolled = studentCourseDAO.checkIfStudentIsAlreadyEnrolled(studentCourse);
		
		if(checkIfAlreadyEnrolled != null) {
			return Response.status(400).entity("Student is Already Enrolled in this course").build();
		} else {
			int returnValue = studentCourseDAO.addCourseOnStudent(studentCourse);
			
			if(returnValue == 0) {
				System.out.println("Return Value: " + returnValue);
				return Response.status(400).entity("Adding of Course on Student Failed").build();
			} else {
				System.out.println("Return Value: " + returnValue);
				return Response.status(201).entity("Course Added Successfully on Student").build();
			}
		}
	}
}
