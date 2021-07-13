package com.example.students_manager.service;

import com.example.students_manager.domain.Student;
import com.example.students_manager.exception.UserNotFoundException;
import com.example.students_manager.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // CREATE
    public Student addStudent(Student student) {
        student.setStudentCode(UUID.randomUUID().toString());
        return studentRepo.save(student);
    }

    // READ
    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    public Student findStudentById(Long id) {
        return studentRepo.findStudentById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + "was not found"));
    }

    // UPDATE
    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    // DELETE
    public void deleteStudent(Long id) {
        studentRepo.deleteStudentById(id);
    }

}
