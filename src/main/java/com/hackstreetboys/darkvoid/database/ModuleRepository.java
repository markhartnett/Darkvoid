package com.hackstreetboys.darkvoid.database;

import com.hackstreetboys.darkvoid.data.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {
}
