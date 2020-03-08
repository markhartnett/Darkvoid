package com.hackstreetboys.darkvoid.repository;

import com.hackstreetboys.darkvoid.model.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
