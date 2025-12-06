package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Submission;

public interface SubmissionRepo extends JpaRepository<Submission, Integer>{

}
