package com.example.students.manager;

import com.example.students.manager.converter.StudentConverter;
import com.example.students.manager.dto.StudentDto;
import com.example.students.manager.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService, StudentConverter studentConverter) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<StudentDto> getAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping("/add")
    public StudentDto addStudent(@RequestBody StudentDto studentDto) {
        return studentService.addStudent(studentDto);
    }

    @PutMapping("/update")
    public StudentDto updateStudent(@RequestBody StudentDto student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }
}
