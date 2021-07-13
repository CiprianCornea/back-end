package com.example.students_manager.converter;

import com.example.students_manager.domain.Student;
import com.example.students_manager.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {

    public StudentDto entityToDto(Student student) {
        StudentDto dtoStudent = new StudentDto();
        dtoStudent.setId(student.getId());
        dtoStudent.setName(student.getName());
        dtoStudent.setEmail(student.getEmail());
        dtoStudent.setPhone(student.getPhone());
        dtoStudent.setSpecialization(student.getSpecialization());
        dtoStudent.setImageUrl(student.getImageUrl());

        return dtoStudent;
    }

    public List<StudentDto> entityToDtoList(List<Student> students) {
        return students.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Student dtoToEntity(StudentDto dtoStudent) {
        Student student = new Student();
        student.setId(dtoStudent.getId());
        student.setName(dtoStudent.getName());
        student.setEmail(dtoStudent.getEmail());
        student.setPhone(dtoStudent.getPhone());
        student.setSpecialization(dtoStudent.getSpecialization());
        student.setImageUrl(dtoStudent.getImageUrl());

        return student;
    }

    public List<Student> dtoToEntityList(List<StudentDto> dtoStudents) {
        return dtoStudents.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
