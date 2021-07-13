package com.example.students.manager.converter;

import com.example.students.manager.domain.Student;
import com.example.students.manager.dto.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public StudentDto entityToDto(Student student) {

        return modelMapper.map(student, StudentDto.class);
    }

    public List<StudentDto> entityToDtoList(List<Student> students) {
        return students.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Student dtoToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    public List<Student> dtoToEntityList(List<StudentDto> studentsDto) {
        return studentsDto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
