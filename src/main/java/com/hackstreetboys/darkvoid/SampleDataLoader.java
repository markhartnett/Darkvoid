package com.hackstreetboys.darkvoid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hackstreetboys.darkvoid.model.*;
import com.hackstreetboys.darkvoid.model.Module;
import com.hackstreetboys.darkvoid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class SampleDataLoader implements ApplicationRunner {
    private final int NUMBER_OF_STUDENTS = 30;
    private final int NUMBER_OF_STAFF = 5;
    private final int NUMBER_OF_MODULES = 7;
    private final List<String> USERNAMES = new ArrayList<>();
    @Autowired private StudentRepository students;
    @Autowired private StaffRepository staffRepository;
    @Autowired private ModuleRepository modules;
    @Autowired private ModuleEnrolmentRepository enrolment;

    private final String[] FIRST_NAMES_MALE = {"John", "Andrew", "Michael", "Bill", "Heindrich", "Wilhelm", "Louis",
            "Clint", "Colin", "Alan", "Alen", "Conor", "Connor", "Hugh", "Mark", "James", "Carlos", "Ben", "Kyle",
            "Janusz", "Stanislav", "Dimitri", "Vladimir", "Serhiy", "Satish", "Rajesh", "Ivan"};
    private final String[] FIRST_NAMES_FEMALE = {"Alice", "Mary", "May", "Theresa", "Joanna", "Ciara", "Amy", "Celine",
            "Barbara", "Celina", "Alexandra", "Niamh", "Una", "Margaret"};
    private final String[] LAST_NAMES = {"Chapman", "Collins", "Murphy", "Powers", "Thatcher", "Ivanovich", "Stambolic",
            "Nowak", "O'Conor", "O'Brien", "Kennedy", "Carter", "O'Rourke", "O'Leary", "Costello", "Kowalski", "Kaczynski",
            "de Gaulle", "Smith"};
    private final String[] PASSWORDS = {"123456", "123456789", "qwerty", "password", "1234567", "12345678", "12345",
            "1234567890", "11111111", "123123", "123321", "1q2w3e4r5t", "iloveyou", "1234", "666666", "654321", "555555",
            "gfhjkm", "abc123", "liverpool", "qwerty123", "qwertyuiop", "michael", "alenisachap", "computer", "admin",
            "memes", "monkey", "elephant", "giraffe", "password1"};
    private final String[] NATIONALITIES = {"Irish", "Polish", "British", "French", "German", "Spanish", "Italian",
            "Indian", "Russian", "Ukrainian", "Chinese", "Serbian", "Croatian", "Bosnian", "Hungarian", "Romanian",
            "Austrian"};
    private final String[] MODULE_CODES = {"COMP47660","COMP47650","COMP40020","COMP40660","COMP47390","COMP47480","COMP47680"};
    private final String[] MODULE_NAMES = {"Secure Software Engineering","Deep Learning","Human Language Technologies",
            "Advances in Wireless Networking","Mobile App Dev - Cocoa Touch","Contemporary Software Development","Human Computer Interaction"};
    private final String[] MODULE_TOPICS = {"Spring, SQL, SQL Injections","Forward Prop, Back Prop, Classification", "Natural Language Processing, Parse Trees, Lexical Diversity", "Networks, Connections, Ports", "IOS basics, Graphics, Swift", "Testing, OOP, UML", "Cognition, Usability and user experience, Stats"};
    private final String[] GRADES = {"A+","A","A-","B+","A","A-","B+","B","A","A-","B+","A","A-","B+","B","B-","C+","C","C-","B","B-","C+","C","C-","B","B-","C+","C","C-","B","B-","C+","C","C-","D+","D","D-","D+","D","D-","E","F"};

    public void run(ApplicationArguments args) throws Exception{
        for (int i = 0; i < NUMBER_OF_STUDENTS; i++){
            Student student;
            if (i==0)
                student  = new Student("Jakub", "Gajewski", "TheFlyingPolak", BCrypt.hashpw("pASSw0rd!", BCrypt.gensalt()), "0874206969", "jakub.gajewski@ucdconnect.ie", "Male", "Polish", 0, 254);
            else
                student = generateRandomStudent();

            // Checks for validity of data are skipped when creating sample students/staff
            students.save(student);
        }

        for (int i = 0; i < NUMBER_OF_STAFF; i++){
            Staff staff;
            if (i == 0)
                staff = new Staff("Mel", "O'Cinneide", "Male", "Irish", "meloc420", "Des!gnPattern$69");
            else
                staff = generateRandomStaff();

            staffRepository.save(staff);
        }

        for (int i = 0; i < NUMBER_OF_MODULES; i++){
            Module module = new Module(MODULE_CODES[i],MODULE_NAMES[i],staffRepository.findAll().get(i % NUMBER_OF_STAFF),MODULE_TOPICS[i],0,20);
            modules.save(module);
        }

        List<Module> availableModules = modules.findAll();

        for(Student student:students.findAll()){
            for(int i = 0; i < 3; i++){
                Random random = new Random();
                int x = random.nextInt(availableModules.size());
                Module module = availableModules.get(x);

                module.setNumberOfStudents(module.getNumberOfStudents()+1);
                modules.save(module);
                if(module.getNumberOfStudents()>=module.getMaxStudents()){
                    availableModules.remove(module);
                }

                ModuleEnrolment moduleEnrolment = new ModuleEnrolment(module,student);
                moduleEnrolment.setGrade(getRandom(GRADES));
                enrolment.save(moduleEnrolment);
            }
        }

    }

    private Student generateRandomStudent(){
        String firstName;
        String gender;
        if (new Random().nextDouble() >= 0.5) {
            firstName = getRandom(FIRST_NAMES_FEMALE);
            gender = "Female";
        }
        else {
            firstName = getRandom(FIRST_NAMES_MALE);
            gender = "Male";
        }
        String lastName = getRandom(LAST_NAMES);
        String email = generateEmail(firstName, lastName, "springfielduni.com");
        String username = generateUsername(firstName, lastName);
        String password = BCrypt.hashpw(getRandom(PASSWORDS), BCrypt.gensalt());
        String phoneNumber = generatePhoneNumber();
        String nationality = getRandom(NATIONALITIES);
        return new Student(firstName, lastName, username, password, phoneNumber, email, gender, nationality, 0, 254);
    }

    private Staff generateRandomStaff(){
        String firstName;
        String gender;
        if (new Random().nextDouble() >= 0.5) {
            firstName = getRandom(FIRST_NAMES_MALE);
            gender = "Male";
        }
        else {
            firstName = getRandom(FIRST_NAMES_FEMALE);
            gender = "Female";
        }
        String lastName = getRandom(LAST_NAMES);
        String username = generateUsername(firstName, lastName);
        String password = BCrypt.hashpw(getRandom(PASSWORDS), BCrypt.gensalt());
        String nationality = getRandom(NATIONALITIES);
        return new Staff(firstName, lastName, gender, nationality, username, password);
    }

    private String getRandom(String[] array){
        int random = new Random().nextInt(array.length);
        return array[random];
    }

    private String generateEmail(String firstName, String lastName, String domain){
        firstName = firstName.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
        lastName = lastName.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
        return firstName + "." + lastName + "@" + domain;
    }

    private String generateUsername(String firstName, String lastName){
        firstName = firstName.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
        lastName = lastName.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
        String username = "";
        do{
            username = firstName + lastName + (new Random().nextInt(1000));
        } while (USERNAMES.contains(username));
        USERNAMES.add(username);
        return username;
    }

    private String generatePhoneNumber(){
        String number = "";
        Random r = new Random();
        double random = r.nextDouble();
        if (random < 0.25) number += "083";
        else if (random >= 0.25 && random < 0.5) number += "085";
        else if (random >= 0.5 && random < 0.75) number += "087";
        else number += "089";

        for (int i = 0; i < 7; i++){
            number += r.nextInt(10);
        }
        return number;
    }
}
