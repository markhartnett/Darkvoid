package com.hackstreetboys.darkvoid;

import com.hackstreetboys.darkvoid.data.*;
import com.hackstreetboys.darkvoid.data.Module;
import com.hackstreetboys.darkvoid.database.*;
import com.hackstreetboys.darkvoid.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DarkvoidController {
    @Autowired StudentRepository studentRepository;
    @Autowired StaffRepository staffRepository;
    @Autowired ModuleRepository moduleRepository;

    // ===============================================================
    // GET mappings
    @GetMapping("/students")
    public List<Student> getAllStudents() { return studentRepository.findAll(); }
    @GetMapping("/staff")
    public List<Staff> getAllStaff() { return staffRepository.findAll(); }
    @GetMapping("/modules")
    public List<Module> getAllModules() { return moduleRepository.findAll(); }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable(value = "id") Integer id) throws StudentNotFoundException{
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @GetMapping("/staff/{id}")
    public Staff getStaffById(@PathVariable(value = "id") Integer id) throws StaffNotFoundException{
        return staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
    }

    @GetMapping("/modules/{code}")
    public Module getStudentById(@PathVariable(value = "code") String code) throws ModuleNotFoundException{
        return moduleRepository.findById(code)
                .orElseThrow(() -> new ModuleNotFoundException(code));
    }

    // ===============================================================
    // POST mappings
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student){
        return studentRepository.save(student);
    }

    @PostMapping("/staff")
    public Staff createStaff(@Valid @RequestBody Staff staff){
        return staffRepository.save(staff);
    }

    @PostMapping("/module")
    public Module createModule(@Valid @RequestBody Module module){
        return moduleRepository.save(module);
    }

    // ===============================================================
    // PUT mappings
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable(value = "id") Integer id,
                                 @Valid @RequestBody Student studentDetails) throws StudentNotFoundException{
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setUsername(studentDetails.getUsername());
        student.setPassword(studentDetails.getPassword());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        student.setEmail(studentDetails.getEmail());
        student.setGender(studentDetails.getGender());
        student.setNationality(studentDetails.getNationality());
        student.setFeesdue(studentDetails.getFeesdue());
        student.setFeespaid(studentDetails.getFeespaid());
        return studentRepository.save(student);
    }

    @PutMapping("/staff/{id}")
    public Staff updateStaff(@PathVariable(value = "id") Integer id,
                             @Valid @RequestBody Staff staffDetails) throws StaffNotFoundException{
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException(id));
        staff.setFirstName(staffDetails.getFirstName());
        staff.setLastName(staffDetails.getLastName());
        staff.setGender(staffDetails.getGender());
        staff.setNationality(staffDetails.getNationality());
        staff.setUsername(staffDetails.getUsername());
        staff.setPassword(staffDetails.getPassword());
        return staffRepository.save(staff);
    }

    @PutMapping("/module/{code}")
    public Module updateModule(@PathVariable(value = "code") String code,
                               @Valid @RequestBody Module moduleDetails) throws ModuleNotFoundException{
        Module module = moduleRepository.findById(code).orElseThrow(() -> new ModuleNotFoundException(code));
        module.setModuleName(moduleDetails.getModuleName());
        module.setCoordinator(moduleDetails.getCoordinator());
        module.setTopics(moduleDetails.getTopics());
        return moduleRepository.save(module);
    }

    // ===============================================================
    // DELETE mappings
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Integer id) throws StudentNotFoundException{
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable(value = "id") Integer id) throws StaffNotFoundException{
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException(id));
        staffRepository.delete(staff);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/module/{code}")
    public ResponseEntity<?> deleteModule(@PathVariable(value = "code") String code) throws ModuleNotFoundException{
        Module module = moduleRepository.findById(code).orElseThrow(() -> new ModuleNotFoundException(code));
        moduleRepository.delete(module);
        return ResponseEntity.ok().build();
    }
}
