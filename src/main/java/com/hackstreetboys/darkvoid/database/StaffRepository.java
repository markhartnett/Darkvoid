package com.hackstreetboys.darkvoid.database;

import com.hackstreetboys.darkvoid.data.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
