package com.example.students.manager;

import com.example.students.manager.converter.StudentConverter;
import com.example.students.manager.dto.StudentDto;
import com.example.students.manager.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students") // students
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping() // fara path
    public List<StudentDto> getAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping() // fara path
    public StudentDto addStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.addStudent(studentDto);
    }

    @PutMapping("/{id}") // id param
    public StudentDto updateStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/{id}") // fara delete
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }
}
