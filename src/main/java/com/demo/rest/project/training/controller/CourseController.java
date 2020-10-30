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
import com.demo.rest.project.training.models.Course;
import com.demo.rest.project.training.models.Teacher;

@Component
@Path("/courses")
public class CourseController {
	
	@Autowired
	CourseDAO courseDAO;
	
	/************************************ CREATE ************************************/
	
	/** TO DO: Check if the Course is already existing**/
	
	/**
	 * Create new course
	 * @param course
	 * @return Response with status code message in JSON format
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCourse(Course course) {
		
		int returnValue = courseDAO.createCourse(course);
		
		if(returnValue == 0) {
			return Response.status(400).entity("Course Creation Failed").build();
		} else {
			return Response.status(201).entity("Course successfully created").build();
		}
	}
	
	/************************************ READ ************************************/
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses() {
		return courseDAO.getCourses();
	}
	
	@GET
	@Path("{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCourseByCode(@PathParam("courseCode") String courseCode) {
		Course course = courseDAO.getCourseByCode(courseCode);
		
		if(course == null) {
			return Response.status(404).entity("Course not found").build();
		} else {
			return Response.status(200).entity(course).build();
		}
	}
	
	@GET
	@Path("/assigned/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignedTeacher(@PathParam("courseId") long courseId) {
		
		//TODO: add validations in courseId if its existing
		
		List<Teacher> teacher = courseDAO.getAssignedTeacher(courseId);
		
		if(teacher == null) {
			return Response.status(404).entity("Assigned professor not found").build();
		} else {
			return Response.status(200).entity(teacher).build();
		}
	}
	
	/************************************ UPDATE ************************************/
	
	/** TO DO: Check if the Course is already existing**/
	
	@PUT
	@Path("{courseCode}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCourseByCode(@PathParam("courseCode") String courseCode, Course course) {
		Course findCourse = courseDAO.getCourseByCode(courseCode);
		if(findCourse == null) {
			return Response.status(404).entity("Course Not Found").build();
		} else {
			int returnValue = courseDAO.updateCourseByCode(courseCode, course);
			if(returnValue == 1) {
				return Response.status(200).entity("Updated Successfully!").build();
			} else {
				return Response.status(404).entity("Update Request Failed").build();
			}
		}
	}

	/************************************ DELETE ************************************/
	
	@DELETE
	@Path("{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourseByCode(@PathParam("courseCode") String courseCode) {
		int returnValue = courseDAO.deleteCourseByCode(courseCode);
		
		if(returnValue == 1) {
			return Response.status(200).entity("Course with " + courseCode + " code has been deleted").build();
		} else {
			return Response.status(404).entity("Course Code Not Found").build();
		}	
	}	
}
