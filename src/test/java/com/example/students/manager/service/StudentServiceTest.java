package com.example.students.manager.service;

import com.example.students.manager.converter.StudentConverter;
import com.example.students.manager.domain.Student;
import com.example.students.manager.dto.StudentDto;
import com.example.students.manager.repo.StudentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepo studentRepo;

    @Mock
    StudentConverter studentConverter;

    @InjectMocks
    StudentService studentService;

    Student student1 = new Student(1L, "Vlad Bogdan", "bogdan.v@outlook.com", "UBB AC", "0755056731",
            "https://bootdey.com/img/Content/avatar/avatar2.png", UUID.randomUUID().toString());
    Student student2 = new Student(1L, "Maria Cornea", "maria.c@outlook.com", "UBB FMI", "0755075231",
            "https://bootdey.com/img/Content/avatar/avatar5.png", UUID.randomUUID().toString());
    StudentDto studentDto1 = new StudentDto();
    StudentDto studentDto2 = new StudentDto();

    private void initDto() {
        studentDto1.setId(1L);
        studentDto1.setName("Vlad Bogdan");
        studentDto1.setEmail("bogdan.v@outlook.com");
        studentDto1.setPhone("0755056731");
        studentDto1.setImageUrl("https://bootdey.com/img/Content/avatar/avatar2.png");
        studentDto1.setSpecialization("UBB AC");

        studentDto2.setId(1L);
        studentDto2.setName("Maria Cornea");
        studentDto2.setEmail("maria.c@outlook.com");
        studentDto2.setPhone("0755075231");
        studentDto2.setImageUrl("https://bootdey.com/img/Content/avatar/avatar5.png");
        studentDto2.setSpecialization("UBB FMI");
    }

    private void mocking() {
        this.initDto();
        lenient().when(studentConverter.entityToDto(student1)).thenReturn(studentDto1);
        lenient().when(studentConverter.entityToDto(student2)).thenReturn(studentDto2);
        lenient().when(studentConverter.dtoToEntity(studentDto1)).thenReturn(student1);
        lenient().when(studentConverter.dtoToEntity(studentDto2)).thenReturn(student2);
    }

    @Test
    public void addStudent() {
        this.mocking();
        when(studentRepo.save(student1)).thenReturn(student1);
        StudentDto result = studentService.addStudent(studentDto1);
        assertEquals(result, studentDto1);
        verify(studentRepo).save(student1);
    }

    @Test
    public void updateStudent() {
        this.mocking();
        when(studentRepo.save(student1)).thenReturn(student1);
        StudentDto result = studentService.addStudent(studentDto1);
        when(studentRepo.save(student2)).thenReturn(student2);
        StudentDto resultUpdate = studentService.updateStudent(studentDto2);
        assertEquals(studentDto2, resultUpdate);
        verify(studentRepo).save(student2);
    }

    @Test
    public void findAllStudents() {
        this.mocking();

        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList.add(studentDto1);
        List<Student> studentList = studentDtoList.stream()
                .map(studentDto1 -> studentConverter.dtoToEntity(studentDto1))
                .collect(Collectors.toList());
        lenient().when(studentConverter.entityToDtoList(studentList)).thenReturn(studentDtoList);
        lenient().when(studentRepo.findAll()).thenReturn(studentList);
        List<StudentDto> result = studentService.findAllStudents();
        assertEquals(studentDtoList.size(), result.size());
        verify(studentRepo).findAll();
    }

    @Test
    public void getStudentById() {
        this.mocking();

        when(studentRepo.getById(1L)).thenReturn(student1);

        StudentDto studentDto = studentService.getStudentById(1L);
        assertEquals(studentDto1, studentDto);
        verify(studentRepo).getById(1L);
    }

    @Test
    public void deleteStudent() {
        studentService.deleteStudent(1L);
        verify(studentRepo, atLeastOnce()).deleteStudentById(1L);
    }
}
