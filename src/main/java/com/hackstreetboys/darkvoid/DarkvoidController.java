package com.hackstreetboys.darkvoid;

import com.hackstreetboys.darkvoid.data.*;
import com.hackstreetboys.darkvoid.data.Module;
import com.hackstreetboys.darkvoid.database.*;
import com.hackstreetboys.darkvoid.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DarkvoidController {
    @Autowired StudentRepository studentRepository;
    @Autowired StaffRepository staffRepository;
    @Autowired ModuleRepository moduleRepository;
    @Autowired ModuleEnrolmentRepository moduleEnrolmentRepository;

    // ===============================================================
    // GET mappings
    @GetMapping("/students")
    public List<Student> getAllStudents() { return studentRepository.findAll(); }
    @GetMapping("/staff")
    public List<Staff> getAllStaff() { return staffRepository.findAll(); }
    @GetMapping("/modules")
    public List<Module> getAllModules() { return moduleRepository.findAll(); }
    @GetMapping("/moduleEnrolment")
    public List<ModuleEnrolment> getAllModuleEnrolments() { return moduleEnrolmentRepository.findAll(); }

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

    @GetMapping("/modules/{id}")
    public Module getModuleById(@PathVariable(value = "id") String id) throws ModuleNotFoundException{
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
    }

    @GetMapping("/moduleEnrolments/{id}")
    public List<List<String>> getModulesAndGradesByStudentId(@PathVariable(value = "id") Integer id){
        List<ModuleEnrolment> enrolments = moduleEnrolmentRepository.findAll();

        List<List<String>> modulesAndGrades = new ArrayList<List<String>>();

        for (ModuleEnrolment enrolment:enrolments) {
            if(enrolment.getStudent().getStudentId()==id){
                List<String> moduleAndGrade = new ArrayList<>();
                moduleAndGrade.add(enrolment.getModule().getModuleId());
                moduleAndGrade.add(enrolment.getGrade());

                modulesAndGrades.add(moduleAndGrade);
            }
        }

        return modulesAndGrades;
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

    @PostMapping("/moduleEnrolment")
    public ModuleEnrolment createModuleEnrolment(@Valid @RequestBody ModuleEnrolment moduleEnrolment){
        return moduleEnrolmentRepository.save(moduleEnrolment);
    }


    @PostMapping("/enrol/{studentId}/{moduleId}")
    public void enrolStudent(@PathVariable(value = "studentId") Integer studentId, @PathVariable(value = "moduleId") String moduleId) throws ModuleNotFoundException, StudentNotFoundException {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ModuleNotFoundException(moduleId));
        module.setNumberOfStudents(module.getNumberOfStudents()+1);

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));

        createModuleEnrolment(new ModuleEnrolment(module,student,""));
        moduleRepository.save(module);
    }

    @PostMapping("/cancelEnrolment/{studentId}/{moduleId}")
    public void cancelEnrolment(@PathVariable(value = "studentId") Integer studentId, @PathVariable(value = "moduleId") String moduleId) throws ModuleNotFoundException, StudentNotFoundException, ModuleEnrolmentNotFoundException {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ModuleNotFoundException(moduleId));
        module.setNumberOfStudents(module.getNumberOfStudents()-1);

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));

        int enrolmentId = 0;

        for (ModuleEnrolment moduleEnrolment:moduleEnrolmentRepository.findAll()) {
            if(moduleEnrolment.getStudent().equals(student) && moduleEnrolment.getModule().equals(module)){
                enrolmentId = moduleEnrolment.getEnrolmentId();
            }
        }

        deleteModuleEnrolment(enrolmentId);
        moduleRepository.save(module);
    }

    @PostMapping("/students/{id}")
    public void studentDropOut(@PathVariable(value = "id") Integer id) throws StudentNotFoundException, ModuleEnrolmentNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        for (ModuleEnrolment moduleEnrolment:moduleEnrolmentRepository.findAll()) {
            if(moduleEnrolment.getStudent().equals(student)){
                deleteModuleEnrolment(moduleEnrolment.getEnrolmentId());
            }
        }

        deleteStudent(student.getStudentId());
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

    @PutMapping("/modules/{id}")
    public Module updateModule(@PathVariable(value = "id") String id,
                               @Valid @RequestBody Module moduleDetails) throws ModuleNotFoundException{
        Module module = moduleRepository.findById(id).orElseThrow(() -> new ModuleNotFoundException(id));
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

    @DeleteMapping("/modules/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable(value = "id") String id) throws ModuleNotFoundException{
        Module module = moduleRepository.findById(id).orElseThrow(() -> new ModuleNotFoundException(id));
        moduleRepository.delete(module);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/moduleEnrolments/{id}")
    public ResponseEntity<?> deleteModuleEnrolment(@PathVariable(value = "id") int id) throws ModuleEnrolmentNotFoundException{
        ModuleEnrolment moduleEnrolment = moduleEnrolmentRepository.findById(id).orElseThrow(() -> new ModuleEnrolmentNotFoundException(id));
        moduleEnrolmentRepository.delete(moduleEnrolment);
        return ResponseEntity.ok().build();
    }
}
