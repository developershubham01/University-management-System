package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {

}
