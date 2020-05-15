package com.hackstreetboys.darkvoid.controller;

import com.hackstreetboys.darkvoid.DarkvoidApplication;
import com.hackstreetboys.darkvoid.POJO.LoginEntity;
import com.hackstreetboys.darkvoid.model.*;
import com.hackstreetboys.darkvoid.model.Module;
import com.hackstreetboys.darkvoid.repository.*;
import com.hackstreetboys.darkvoid.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DarkvoidController {
    private static final Logger log = LoggerFactory.getLogger(DarkvoidApplication.class);

    @Autowired StudentRepository studentRepository;
    @Autowired StaffRepository staffRepository;
    @Autowired ModuleRepository moduleRepository;
    @Autowired ModuleEnrolmentRepository moduleEnrolmentRepository;

    // ===============================================================
    // GET mappings
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/students")
    public List<Student> getAllStudents() { return studentRepository.findAll(); }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/staff")
    public List<Staff> getAllStaff() { return staffRepository.findAll(); }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/modules")
    public List<Module> getAllModules() { return moduleRepository.findAll(); }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/moduleEnrolment")
    public List<ModuleEnrolment> getAllModuleEnrolments() { return moduleEnrolmentRepository.findAll(); }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable(value = "id") Integer id) throws StudentNotFoundException{
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/staff/{id}")
    public Staff getStaffById(@PathVariable(value = "id") Integer id) throws StaffNotFoundException{
        return staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/modules/{id}")
    public Module getModuleById(@PathVariable(value = "id") String id) throws ModuleNotFoundException{
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/moduleEnrolments/{id}")
    public List<List<String>> getModulesAndGradesByStudentId(@PathVariable(value = "id") Integer id){
        List<ModuleEnrolment> enrolments = moduleEnrolmentRepository.findAll();

        List<List<String>> modulesAndGrades = new ArrayList<List<String>>();

        for (ModuleEnrolment enrolment:enrolments) {
            if(enrolment.getStudent().getStudentId().equals(id)){
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
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student){
        log.info("New student: " + student.getUsername());
        return studentRepository.save(student);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/staff")
    public Staff createStaff(@Valid @RequestBody Staff staff){
        log.info("New staff: " + staff.getUsername());
        return staffRepository.save(staff);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/module")
    public Module createModule(@Valid @RequestBody Module module){
        log.info("New module: " + module.getModuleName());
        return moduleRepository.save(module);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/moduleEnrolment")
    public ModuleEnrolment createModuleEnrolment(@Valid @RequestBody ModuleEnrolment moduleEnrolment){
        log.info("Student: " + moduleEnrolment.getStudent().getUsername() + ", enrolled in: " + moduleEnrolment.getModule().getModuleName());
        return moduleEnrolmentRepository.save(moduleEnrolment);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/login/student")
    public int checkLoginStudent(@RequestBody LoginEntity requestBody){
        String username = requestBody.getUsername();
        String password = requestBody.getPassword();
        for (Student student:getAllStudents()) {
            if(student.getUsername().equals(username) && student.getPassword().equals(password)){
                log.info("Student user: " + username + ", logged in");
                return student.getID();
            }
        }
        log.info("Login details were not of that of a staff member");
        return -1;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/login/staff")
    public int checkLoginStaff(@RequestBody LoginEntity requestBody){
        String username = requestBody.getUsername();
        String password = requestBody.getPassword();
        for (Staff staff:getAllStaff()) {
            if(staff.getUsername().equals(username) && staff.getPassword().equals(password)){
                log.info("Staff user: " + username + ", logged in");
                return staff.getID();
            }
        }
        log.info("Login details were not of that of a staff member");
        return -1;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/enrol/{studentId}/{moduleId}")
    public ModuleEnrolment enrolStudent(@PathVariable(value = "studentId") Integer studentId, @PathVariable(value = "moduleId") String moduleId) throws ModuleNotFoundException, StudentNotFoundException {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ModuleNotFoundException(moduleId));
        module.setNumberOfStudents(module.getNumberOfStudents()+1);
        moduleRepository.save(module);

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));

        return createModuleEnrolment(new ModuleEnrolment(module,student,""));
    }

    @CrossOrigin(origins = "http://localhost:63342")
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

        log.info("Student: " + student.getUsername() + ", unenrolled from: " + module.getModuleName());

        deleteModuleEnrolment(enrolmentId);
        moduleRepository.save(module);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/studentDropout/{id}")
    public void studentDropOut(@PathVariable(value = "id") Integer id) throws StudentNotFoundException, ModuleEnrolmentNotFoundException, ModuleNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        for (ModuleEnrolment moduleEnrolment:moduleEnrolmentRepository.findAll()) {
            if(moduleEnrolment.getStudent().equals(student)){
                cancelEnrolment(id,moduleEnrolment.getModule().getModuleId());
            }
        }

        log.info("Student: " + student.getUsername() + " dropped out ");
        deleteStudent(student.getStudentId());
    }

    // ===============================================================
    // PUT mappings
    @CrossOrigin(origins = "http://localhost:63342")
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

    @CrossOrigin(origins = "http://localhost:63342")
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

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping("/modules/{id}")
    public Module updateModule(@PathVariable(value = "id") String id,
                               @Valid @RequestBody Module moduleDetails) throws ModuleNotFoundException{
        Module module = moduleRepository.findById(id).orElseThrow(() -> new ModuleNotFoundException(id));
        module.setModuleName(moduleDetails.getModuleName());
        module.setCoordinator(moduleDetails.getCoordinator());
        module.setTopics(moduleDetails.getTopics());
        return moduleRepository.save(module);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping("/moduleEnrolments/{id}")
    public ModuleEnrolment updateModuleEnrolments(@PathVariable(value = "id") Integer id,
                               @Valid @RequestBody ModuleEnrolment moduleEnrolmentDetails) throws ModuleEnrolmentNotFoundException{
        ModuleEnrolment moduleEnrolment = moduleEnrolmentRepository.findById(id).orElseThrow(() -> new ModuleEnrolmentNotFoundException(id));
        moduleEnrolment.setEnrolmentId(moduleEnrolmentDetails.getEnrolmentId());
        moduleEnrolment.setGrade(moduleEnrolmentDetails.getGrade());
        moduleEnrolment.setModule(moduleEnrolmentDetails.getModule());
        moduleEnrolment.setStudent(moduleEnrolmentDetails.getStudent());
        return moduleEnrolmentRepository.save(moduleEnrolment);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping("/changeGrade/{studentId}/{moduleId}/{newGrade}")
    public void changeGrade(@PathVariable(value = "studentId") Integer studentId,@PathVariable(value = "moduleId") String moduleId,@PathVariable(value = "newGrade") String newGrade) throws ModuleEnrolmentNotFoundException{
        for (ModuleEnrolment moduleEnrolment: getAllModuleEnrolments()) {
            if(moduleEnrolment.getStudent().getStudentId().equals(studentId) && moduleEnrolment.getModule().getModuleId().equals(moduleId)){
                log.info("Student: " + moduleEnrolment.getStudent().getStudentId() + ", grade changed in " + moduleEnrolment.getModule().getModuleName() + " to " + newGrade);
                moduleEnrolment.setGrade(newGrade);
                updateModuleEnrolments(moduleEnrolment.getEnrolmentId(),moduleEnrolment);
            }
        }
    }

    // ===============================================================
    // DELETE mappings
    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Integer id) throws StudentNotFoundException{
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable(value = "id") Integer id) throws StaffNotFoundException{
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException(id));
        staffRepository.delete(staff);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/modules/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable(value = "id") String id) throws ModuleNotFoundException{
        Module module = moduleRepository.findById(id).orElseThrow(() -> new ModuleNotFoundException(id));
        moduleRepository.delete(module);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/moduleEnrolments/{id}")
    public ResponseEntity<?> deleteModuleEnrolment(@PathVariable(value = "id") int id) throws ModuleEnrolmentNotFoundException{
        ModuleEnrolment moduleEnrolment = moduleEnrolmentRepository.findById(id).orElseThrow(() -> new ModuleEnrolmentNotFoundException(id));
        moduleEnrolmentRepository.delete(moduleEnrolment);
        return ResponseEntity.ok().build();
    }
}
