package com.hackstreetboys.darkvoid.repository;

import com.hackstreetboys.darkvoid.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
