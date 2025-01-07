package com.sjprogramming.restapi.springbootrestapiproject.controller;

import java.util.*;

// import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sjprogramming.restapi.springbootrestapiproject.entity.Student;
import com.sjprogramming.restapi.springbootrestapiproject.repository.StudentRepository;


@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	//get all the students 
	//localhost:8080/students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		 List<Student> students = repo.findAll();
		  return students;
	}
	
	//localhost:8080/students/1
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		
		return student;
		
	}
	
	@PostMapping("/student/add")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student newStudent = repo.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
		
	}
	
	@PutMapping("/student/update/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
    // Check if the student with the given ID exists
    Optional<Student> optionalStudent = repo.findById(id);

    if (optionalStudent.isPresent()) {
        Student existingStudent = optionalStudent.get();

        // Update the existing student's details
        existingStudent.setName(student.getName());
        existingStudent.setPercentage(student.getPercentage());
        existingStudent.setBranch(student.getBranch());

        // Save the updated student back to the repository
        Student updatedStudent = repo.save(existingStudent);

        // Return the updated student with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedStudent);
    } else {
        // If the student is not found, return HTTP status 404 (Not Found)
        return ResponseEntity.notFound().build();
    }
}
	@DeleteMapping("/student/delete/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
	}

}
