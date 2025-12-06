package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.CourseDto;
import com.example.demo.Entity.Course;
import com.example.demo.Service.CourseService;

@RestController
public class CourseController {

	@Autowired
	CourseService courseService;

	@PostMapping("addcourse/{professorId}")
	public ResponseEntity<String>addCourse(@RequestBody CourseDto courseDto,@PathVariable Integer professorId) {
		courseService.addCourse(courseDto,professorId);
		return new ResponseEntity<String>("course saved", HttpStatus.CREATED);
	}

	@GetMapping("assign/{studentId}/{courseId}")
	public ResponseEntity<Course> assignStudent(@PathVariable Integer studentId, @PathVariable Integer courseId) {
		courseService.assignStudent(studentId, courseId);
		return new ResponseEntity<Course>(HttpStatus.OK);
	}

}
