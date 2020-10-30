package com.demo.rest.project.training.models;

public class StudentCourse {
	
	private Long courseId;
	private Long studentId;
	
	public StudentCourse() {

	}

	public StudentCourse(Long courseId, Long studentId) {
		super();
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
