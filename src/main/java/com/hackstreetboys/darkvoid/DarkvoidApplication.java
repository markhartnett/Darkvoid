package com.hackstreetboys.darkvoid;

import com.hackstreetboys.darkvoid.data.ModuleEnrolment;
import com.hackstreetboys.darkvoid.data.Staff;
import com.hackstreetboys.darkvoid.data.Student;
import com.hackstreetboys.darkvoid.data.Module;
import com.hackstreetboys.darkvoid.database.ModuleEnrolmentRepository;
import com.hackstreetboys.darkvoid.database.ModuleRepository;
import com.hackstreetboys.darkvoid.database.StaffRepository;
import com.hackstreetboys.darkvoid.database.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DarkvoidApplication {
    private static final Logger log = LoggerFactory.getLogger(DarkvoidApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DarkvoidApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(StudentRepository studentRepository, StaffRepository staffRepository, ModuleRepository moduleRepository, ModuleEnrolmentRepository moduleEnrolmentRepository) {
//        return (args) -> {
//            // save a new student
//            Student mark = new Student("Mark", "Hartnett","markhartnett","password","0871234567","mark.hartnett@ucdconnect.ie","m","Irish",3000,1500);
//            studentRepository.save(mark);
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Student student : studentRepository.findAll()) {
//                log.info(student.toString());
//            }
//            log.info("");
//
//
//            Staff lilliana = new Staff("Lilliana","Pasquala","f","Italian","lil123","123456");
//            staffRepository.save(lilliana);
//
//            log.info("Staff found with findAll():");
//            log.info("-------------------------------");
//            for (Staff staff : staffRepository.findAll()) {
//                log.info(staff.toString());
//            }
//            log.info("");
//
//            Module secure = new Module("comp123","secure",lilliana,"webdesign,sqlinjections",23,30);
//            moduleRepository.save(secure);
//
//            log.info("Modules found with findAll():");
//            log.info("-------------------------------");
//            for (Module module : moduleRepository.findAll()) {
//                log.info(module.toString());
//            }
//            log.info("");
//
//            ModuleEnrolment markEnrolment = new ModuleEnrolment(secure,mark,"A+");
//            moduleEnrolmentRepository.save(markEnrolment);
//
//            log.info("Module Enrolments found with findAll():");
//            log.info("-------------------------------");
//            for (ModuleEnrolment moduleEnrolment : moduleEnrolmentRepository.findAll()) {
//                log.info(moduleEnrolment.toString());
//            }
//            log.info("");
//
//        };
//    }
}
