package com.example.demo.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.demo.Exception.AssignmentServiceException;
import com.example.demo.Exception.CourseServiceException;
import com.example.demo.Exception.ProfessorServiceException;
import com.example.demo.Exception.StudentServiceException;
import com.example.demo.Exception.SubmissionServiceException;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(exception = StudentServiceException.class)
	public ResponseEntity<String> StudentExceptionHandler(StudentServiceException studentServiceException) {
		return new ResponseEntity<String>(studentServiceException.getErrorMessage(),
				studentServiceException.getHttpStatus());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(exception = Exception.class)
	public ResponseEntity<String> GlobalExceptionHandler(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(exception = ProfessorServiceException.class)
	public ResponseEntity<String> ProfessorExceptionHandler(ProfessorServiceException professException) {
		return new ResponseEntity<String>(professException.getErrorMessage(), professException.getHttpStatus());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(exception = AssignmentServiceException.class)
	public ResponseEntity<String> AssignmentExceptionHandler(AssignmentServiceException assException) {
		return new ResponseEntity<String>(assException.getErrorMessage(), assException.getHttpStatus());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(exception = SubmissionServiceException.class)
	public ResponseEntity<String> SubmissionExceptionHandler(SubmissionServiceException subException) {
		return new ResponseEntity<String>(subException.getErrorMessage(), subException.getHttpStatus());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(exception = CourseServiceException.class)
	public ResponseEntity<String> CourseExceptionHandler(CourseServiceException coursException) {
		return new ResponseEntity<String>(coursException.getErrorMessage(), coursException.getHttpStatus());
	}

}
