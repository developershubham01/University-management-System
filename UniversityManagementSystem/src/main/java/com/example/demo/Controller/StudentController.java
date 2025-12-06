package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.StudentDto;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("addstudent")
	public ResponseEntity<String> addStudent(@RequestBody StudentDto studentDto) {
		studentService.addStudent(studentDto);
		return new ResponseEntity<String>("student data saved", HttpStatus.CREATED);
	}

	@GetMapping("getstudent/id/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
		Student student = studentService.getStudent(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@GetMapping("getstudent/name/{name}")
	public ResponseEntity<List<Student>> getStudent(@PathVariable String name) {
		List<Student> list = studentService.getStudent(name);
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

}
