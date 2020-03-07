package com.hackstreetboys.darkvoid.repository;

import com.hackstreetboys.darkvoid.model.ModuleEnrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleEnrolmentRepository extends JpaRepository<ModuleEnrolment, Integer> {
}
