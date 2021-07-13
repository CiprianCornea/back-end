package com.example.students_manager;

import com.example.students_manager.converter.StudentConverter;
import com.example.students_manager.domain.Student;
import com.example.students_manager.dto.StudentDto;
import com.example.students_manager.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentConverter studentConverter;

    public StudentController(StudentService studentService, StudentConverter studentConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return new ResponseEntity<>(studentConverter.entityToDtoList(students), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.findStudentById(id);
        return new ResponseEntity<>(studentConverter.entityToDto(student), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(@RequestBody Student student) {
        Student studentToAdd = studentService.addStudent(student);
        return new ResponseEntity<>(studentConverter.entityToDto(studentToAdd), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody Student student) {
        Student newStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(studentConverter.entityToDto(newStudent), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        System.out.println(id);
        studentService.deleteStudent(id);
        //return new ResponseEntity<>(HttpStatus.OK);
    }
}
