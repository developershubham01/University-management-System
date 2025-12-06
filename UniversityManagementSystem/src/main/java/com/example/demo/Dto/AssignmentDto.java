package com.example.demo.Dto;

import com.example.demo.Entity.Course;

public class AssignmentDto {
	private String assignmentTitle;
	private Integer courseId;
	private Course course;
	
	public String getAssignmentTitle() {
		return assignmentTitle;
	}
	public void setAssignmentTitle(String assignmentTitle) {
		this.assignmentTitle = assignmentTitle;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
//	public List<Submission> getSubmission() {
//		return submission;
//	}
//	public void setSubmission(List<Submission> submission) {
//		this.submission = submission;
//	}
//	

}
