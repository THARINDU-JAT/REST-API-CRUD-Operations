package com.sjprogramming.restapi.springbootrestapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjprogramming.restapi.springbootrestapiproject.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
