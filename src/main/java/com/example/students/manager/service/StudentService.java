package com.example.students.manager.service;

import com.example.students.manager.converter.StudentConverter;
import com.example.students.manager.dto.StudentDto;
import com.example.students.manager.repo.StudentRepo;
import com.example.students.manager.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentService {
    private final StudentRepo studentRepo;
    private final StudentConverter studentConverter;

    @Autowired
    public StudentService(StudentRepo studentRepo, StudentConverter studentConverter) {
        this.studentRepo = studentRepo;
        this.studentConverter = studentConverter;
    }

    // CREATE
    public StudentDto addStudent(StudentDto studentDto) {
        // convert dto to entity
        Student student = this.studentConverter.dtoToEntity(studentDto);
        student.setStudentCode(UUID.randomUUID().toString());
        // save
        student = studentRepo.save(student);
        // return dto
        return this.studentConverter.entityToDto(student);
    }

    // READ
    public List<StudentDto> findAllStudents() {
        return this.studentConverter.entityToDtoList(studentRepo.findAll());
    }

    // UPDATE
    public StudentDto updateStudent(StudentDto student) {
        Student forUpdate = this.studentConverter.dtoToEntity(student);
        return this.studentConverter.entityToDto(studentRepo.save(forUpdate));
    }

    // DELETE
    public void deleteStudent(Long id) {
        studentRepo.deleteStudentById(id);
    }

    // GET BY ID
    public StudentDto getStudentById(Long id) {
        Student student = studentRepo.getById(id);
        return this.studentConverter.entityToDto(student);
    }
}
