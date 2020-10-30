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

import com.demo.rest.project.training.dao.CourseDAO;
import com.demo.rest.project.training.dao.TeacherDAO;
import com.demo.rest.project.training.models.Course;
import com.demo.rest.project.training.models.Teacher;

@Component
@Path("/teachers")
public class TeacherController {
	
	@Autowired
	TeacherDAO teacherDAO;
	
	@Autowired
	CourseDAO courseDAO;
	
	/************************************ CREATE ************************************/
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTeacherAccount(Teacher teacher) {
		int returnValue = teacherDAO.createTeacherAccount(teacher);
		
		if(returnValue == 0) {
			return Response.status(400).entity("Teacher Account Creation Failed").build();
		} else {
			return Response.status(201).entity("Account Successfully created").build();
		}
	}
	
	/************************************ READ ************************************/
	
	/**
	 * Get all list of teachers
	 * @return Response with status code in JSON format
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Teacher> getTeachersList() {
		return teacherDAO.getTeachersList();
	}
	
	@GET
	@Path("{employeeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmployeeId(@PathParam("employeeId") String employeeId) {
		Teacher teacher = teacherDAO.getUserByEmployeeId(employeeId);
		
		if(teacher == null) {
			return Response.status(404).entity("Employee Account not found").build();
		} else {
			return Response.status(200).entity(teacher).build();
		}
	}
	
	/************************************ UPDATE ************************************/
	
	/**
	 * 
	 * @param employeeId
	 * @param teacher
	 * @return
	 */
	
	@PUT
	@Path("{employeeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserByEmployeeId(@PathParam("employeeId") String employeeId, Teacher teacher) {
		Teacher findTeacher = teacherDAO.getUserByEmployeeId(employeeId);
	
		if(findTeacher == null) {
			return Response.status(404).entity("Teacher Account Not Found").build();
		} else {
			int returnValue = teacherDAO.updateUserByEmployeeId(employeeId, teacher);
			if(returnValue == 1) {
				return Response.status(200).entity("Updated Successfully!").build();
			} else {
				return Response.status(404).entity("Update Request Failed").build();
			}
		}
	}
	
	/**
	 * 
	 * @param employeeId
	 * @param teacher
	 * @return
	 */
	
	@PUT
	@Path("/assign-course/{employeeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignCourseOnEmployee(@PathParam("employeeId") String employeeId,Teacher teacher) {
		Course findCourseId =  courseDAO.findCourseByid(teacher.getCourseId());
		if(findCourseId == null) {
			return Response.status(404).entity("Course Does Not Exist").build();
		} else {
			int returnValue = teacherDAO.assignCourseOnEmployee(employeeId, teacher);
			if(returnValue == 1) {
				return Response.status(200).entity("Updated Successfully!").build();
			} else {
				return Response.status(404).entity("Employee ID Not Found").build();
			}
		}
	}
	
	/************************************ DELETE ************************************/
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	
	@DELETE
	@Path("{employeeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserByEmployeeId(@PathParam("employeeId") String employeeId) {
		int returnValue = teacherDAO.deleteUserByEmployeeId(employeeId);
		
		if(returnValue == 1) {
			return Response.status(200).entity("User with the employeeId " + employeeId + " has been deleted").build();
		} else {
			return Response.status(404).entity("Employee ID Not Found").build();
		}
	}
}
