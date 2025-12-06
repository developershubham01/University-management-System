package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer assignmentId;
	private String assignmentTitle;
	private LocalDate dueDate;
	

	@PrePersist
	protected void setDueDate() {
		this.dueDate = LocalDate.now().plusDays(7);
	}

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Course course;

	@JsonIgnore
	@OneToMany(mappedBy = "assignment")
	private List<Submission> submission;

	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getAssignmentTitle() {
		return assignmentTitle;
	}

	public void setAssignmentTitle(String assignmentTitle) {
		this.assignmentTitle = assignmentTitle;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Submission> getSubmission() {
		return submission;
	}

	public void setSubmission(List<Submission> submission) {
		this.submission = submission;
	}

}
