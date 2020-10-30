package com.demo.rest.project.training;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.demo.rest.project.training.controller.CourseController;
import com.demo.rest.project.training.controller.StudentController;
import com.demo.rest.project.training.controller.StudentCourseController;
import com.demo.rest.project.training.controller.TeacherController;

public class AppConfig extends ResourceConfig {

	public AppConfig() {
		register(JacksonFeature.class);
		register(StudentController.class);
		register(TeacherController.class);
		register(CourseController.class);
		register(StudentCourseController.class);
	}
}
