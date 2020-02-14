package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Module {
    private @Id @GeneratedValue String moduleCode;
    private @NotBlank String moduleName;
    private @OneToOne Staff coordinator;
    private @OneToMany List<Student> students;

    public Module() {}

    public String getModuleCode() { return moduleCode; }
    public String getModuleName() { return moduleName; }
    public Staff getCoordinator() { return coordinator; }
    public List<Student> getStudents() { return students; }

    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }
    public void setCoordinator(Staff coordinator) { this.coordinator = coordinator; }
    public void setStudents(List<Student> students) { this.students = students; }
}
