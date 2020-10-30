package com.demo.rest.project.training.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.rest.project.training.dao.StudentDAO;
import com.demo.rest.project.training.models.Student;

@Component
@Path("/students")
public class StudentController {
	
	@Autowired
	StudentDAO studentDAO;
	
	/************************************ CREATE ************************************/
	
	/**
	 * Create new student account
	 * @param student
	 * @return Response with status code message in JSON format
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createStudentAccount(Student student) {
		
		/*TO DO: Add validations if student number and email is already existing*/
		
		int returnValue = studentDAO.createStudentAccount(student);

		if(returnValue == 0) {
			System.out.println("Return Value: " + returnValue);
			return Response.status(400).entity("Student Account Creation Failed").build();
		} else {
			System.out.println("Return Value: " + returnValue);
			return Response.status(201).entity("Student Account Successfully created").build();
		}
	}
	
	/************************************ READ ************************************/
	
	/**
	 * Get a list of all students
	 * @return Response with status code and message or student entity in JSON format
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentList() {
		return studentDAO.getStudentList();
	}
	
	/**
	 * Get user by student number
	 * @param studentNumber
	 * @return Response with status code and message or student entity in JSON format
	 */
	@GET
	@Path("{studentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByStudentNumber(@PathParam("studentNumber") String studentNumber) {
		Student student = studentDAO.getUserByStudentNumber(studentNumber);
		if(student == null) {
			return Response.status(404).entity("Student Account Not Found").build();
		} else {
			return Response.status(200).entity(student).build();
		}
	}
	
	/************************************ UPDATE ************************************/
	
	/**
	 * Update user account by student number
	 * @param studentNumber
	 * @param student
	 * @return Response with status code and message in JSON format
	 */
	@PUT
	@Path("{studentNumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserByStudentNumber(@PathParam("studentNumber") String studentNumber, Student student) {
		Student findStudent = studentDAO.getUserByStudentNumber(studentNumber);
		if(findStudent == null) {
			return Response.status(404).entity("Student Account Not Found").build();
		} else {
			int returnValue = studentDAO.updateUserByStudentNumber(studentNumber, student);
			if(returnValue == 1) {
				return Response.status(200).entity("Updated Successfully!").build();
			} else {
				return Response.status(404).entity("Update Request Failed").build();
			}
		}
	}
	
	/************************************ DELETE ************************************/
	
	/**
	 * Delete user account by student number
	 * @param studentNumber
	 * @return
	 */
	@DELETE
	@Path("{studentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserByStudentNumber(@PathParam("studentNumber") String studentNumber) {
		int returnValue = studentDAO.deleteUserByStudentNumber(studentNumber);
		
		if(returnValue == 1) {
			return Response.status(200).entity("User with studentNumber" + studentNumber + "has been deleted").build();
		} else {
			return Response.status(404).entity("Student Number Not Found").build();
		}	
	}
}
