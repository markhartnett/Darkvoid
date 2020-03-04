package com.hackstreetboys.darkvoid.database;

import com.hackstreetboys.darkvoid.data.Module;

import com.hackstreetboys.darkvoid.data.ModuleEnrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleEnrolmentRepository extends JpaRepository<ModuleEnrolment, Integer> {
}
