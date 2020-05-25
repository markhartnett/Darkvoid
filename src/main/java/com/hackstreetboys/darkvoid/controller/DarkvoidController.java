package com.hackstreetboys.darkvoid.controller;

import com.hackstreetboys.darkvoid.DarkvoidApplication;
import com.hackstreetboys.darkvoid.POJO.*;
import com.hackstreetboys.darkvoid.model.*;
import com.hackstreetboys.darkvoid.model.Module;
import com.hackstreetboys.darkvoid.repository.*;
import com.hackstreetboys.darkvoid.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired private PasswordEncoder passwordEncoder;

    // ===============================================================
    // POST mappings
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) throws InvalidInputException{
        log.info("New student: " + student.getUsername());
        if ( student.getPhoneNumber().matches("[^0-9]") &&
                student.getPhoneNumber().length() < 6 &&
                student.getPhoneNumber().length() > 13 &&
                !student.getEmail().contains("@") &&
                !student.getEmail().substring(student.getEmail().length() - 4).equals(".com") ) {
            throw new InvalidInputException();
        }
        student.setPassword(BCrypt.hashpw(student.getPassword(), BCrypt.gensalt()));
        return studentRepository.save(student);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/staff")
    public Staff createStaff(@Valid @RequestBody Staff staff){
        log.info("New staff: " + staff.getUsername());
        staff.setPassword(BCrypt.hashpw(staff.getPassword(), BCrypt.gensalt()));
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
        for (Student student:studentRepository.findAll()) {
            if(student.getUsername().equals(username) && BCrypt.checkpw(password,student.getPassword())){
                log.info("Student user: " + username + ", logged in");
                log.info(student.toString());
                System.out.println(student.getID());
                return student.getID();
            }
        }
        log.info("Login details were not of that of a student member");
        return -1;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/login/staff")
    public int checkLoginStaff(@RequestBody LoginEntity requestBody){
        String username = requestBody.getUsername();
        String password = requestBody.getPassword();
        for (Staff staff:staffRepository.findAll()) {
            if(staff.getUsername().equals(username) && BCrypt.checkpw(password,staff.getPassword())){
                log.info("Staff user: " + username + ", logged in");
                return staff.getID();
            }
        }
        log.info("Login details were not of that of a staff member");
        return -1;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/enrol")
    public ModuleEnrolment enrolStudent(@RequestBody Enrolment enrolment) throws ModuleNotFoundException, StudentNotFoundException {
        int studentId = enrolment.getStudentId();
        String moduleId = enrolment.getModuleId();
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ModuleNotFoundException(moduleId));
        module.setNumberOfStudents(module.getNumberOfStudents()+1);
        moduleRepository.save(module);

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));

        return createModuleEnrolment(new ModuleEnrolment(module,student,""));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/cancelEnrolment")
    public void cancelEnrolment(@RequestBody Enrolment enrolment) throws ModuleNotFoundException, StudentNotFoundException, ModuleEnrolmentNotFoundException {
        int studentId = enrolment.getStudentId();
        String moduleId = enrolment.getModuleId();
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
    @PostMapping("/studentDropout")
    public void studentDropOut(@RequestBody Id idNum) throws StudentNotFoundException, ModuleEnrolmentNotFoundException, ModuleNotFoundException {
        int id = idNum.getId();
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        for (ModuleEnrolment moduleEnrolment:moduleEnrolmentRepository.findAll()) {
            if(moduleEnrolment.getStudent().equals(student)){

                cancelEnrolment(new Enrolment(id,moduleEnrolment.getModule().getModuleId()));
            }
        }

        log.info("Student: " + student.getUsername() + " dropped out ");
        deleteStudent(student.getStudentId());
    }


    // ===============================================================
    // DELETE mappings
    public ResponseEntity<?> deleteStudent(Integer id) throws StudentNotFoundException{
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteStaff(Integer id) throws StaffNotFoundException{
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException(id));
        staffRepository.delete(staff);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteModule(String id) throws ModuleNotFoundException{
        Module module = moduleRepository.findById(id).orElseThrow(() -> new ModuleNotFoundException(id));
        moduleRepository.delete(module);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteModuleEnrolment(int id) throws ModuleEnrolmentNotFoundException{
        ModuleEnrolment moduleEnrolment = moduleEnrolmentRepository.findById(id).orElseThrow(() -> new ModuleEnrolmentNotFoundException(id));
        moduleEnrolmentRepository.delete(moduleEnrolment);
        return ResponseEntity.ok().build();
    }
    // ===============================================================
    // New mappings
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/students/nationalities")
    public List<String> getStudentNationalities(){
        List<String> nationalities = new ArrayList<>();
        for (Student student: studentRepository.findAll()) {
            nationalities.add(student.getNationality());
        }
        return nationalities;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/staff/nationalities")
    public List<String> getStaffNationalities(){
        List<String> nationalities = new ArrayList<>();
        for (Staff staff: staffRepository.findAll()) {
            nationalities.add(staff.getNationality());
        }
        return nationalities;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/students/genders")
    public List<String> getStudentGenders(){
        List<String> genders = new ArrayList<>();
        for (Student student: studentRepository.findAll()) {
            genders.add(student.getGender());
        }
        return genders;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/staff/genders")
    public List<String> getStaffGenders(){
        List<String> genders = new ArrayList<>();
        for (Staff staff: staffRepository.findAll()) {
            genders.add(staff.getGender());
        }
        return genders;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/students/grades")
    public List<String> getStudentGrades(){
        List<String> grades = new ArrayList<>();
        for (ModuleEnrolment moduleEnrolment: moduleEnrolmentRepository.findAll()) {
            grades.add(moduleEnrolment.getGrade());
        }
        return grades;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/isNewUsername")
    public int isNewUsername(@RequestBody Username username){
        for (Student student : studentRepository.findAll()) {
            if(student.getUsername().equals(username.getUsername())){
                return 0;
            }
        }
        for (Staff staff :  staffRepository.findAll()){
            if(staff.getUsername().equals(username.getUsername())){
                return 0;
            }
        }
        return 1;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/studentsId")
    public Student getStudentById(@RequestBody Id idNum) throws StudentNotFoundException{
        int id = idNum.getId();
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/staffId")
    public Staff getStaffById(@RequestBody Id idNum) throws StaffNotFoundException{
        int id = idNum.getId();
        return staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/modulesId")
    public Module getModuleById(@RequestBody ModuleCode moduleCode) throws ModuleNotFoundException{
        String id = moduleCode.getId();
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/moduleEnrolmentsId")
    public List<List<String>> getModulesAndGradesByStudentId(@RequestBody Id idNum){
        int id = idNum.getId();
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

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/myModules")
    public List<Module> getStaffModulesById(@RequestBody Id idNum){
        int id = idNum.getId();
        List<Module> staffModules = new ArrayList<>();

        for(Module module : moduleRepository.findAll()){
            if(module.getCoordinator().getStaffId().equals(id)){
                staffModules.add(module);
                log.info(module.getModuleName());
            }
        }

        return staffModules;
    }
}
