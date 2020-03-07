package com.hackstreetboys.darkvoid.repository;

import com.hackstreetboys.darkvoid.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {
}
