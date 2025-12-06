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

import com.example.demo.Dto.ProfessorDto;
import com.example.demo.Entity.Professor;
import com.example.demo.Service.ProfessorService;

@RestController
public class ProfessorController {

	private ProfessorService professorService;

	@Autowired
	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@PostMapping("addprofessor")
	public ResponseEntity<String> addProfessor(@RequestBody ProfessorDto professorDto) {
		professorService.addProfessor(professorDto);
		return new ResponseEntity<String>("professor added", HttpStatus.CREATED);
	}

	@GetMapping("getprofessor/id/{id}")
	public ResponseEntity<Professor> getProfessor(@PathVariable Integer id) {
		Professor professor = professorService.getProfessor(id);
		return new ResponseEntity<Professor>(professor, HttpStatus.OK);
	}

	@GetMapping("getprof/dept/{department}")
	public ResponseEntity<List<Professor>> getProfessor(@PathVariable String department) {
		List<Professor> list = professorService.getProfessorByDepartment(department);
		return new ResponseEntity<List<Professor>>(list, HttpStatus.OK);
	}

}
