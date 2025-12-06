package com.example.demo.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Submission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer submissionId;
	private LocalDate submittedDate;
	private String grade;

	@PrePersist
	protected void setSubmittedDate() {
		this.submittedDate = LocalDate.now();
	}

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Student student;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Assignment assignment;

	public Integer getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Integer submissionId) {
		this.submissionId = submissionId;
	}

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}
