package com.example.students.manager;

import com.example.students.manager.dto.StudentDto;
import com.example.students.manager.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private final StudentDto studentDto1 = new StudentDto();
    private final StudentDto studentDto2 = new StudentDto();

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

    private StudentDto dtoInit() {
        StudentDto studentDto3 = new StudentDto();
        studentDto3.setId(1L);
        studentDto3.setName("Vlad Bogdan");
        studentDto3.setEmail("bogdan.v@outlook.com");
        studentDto3.setPhone("0755056731");
        studentDto3.setImageUrl("https://bootdey.com/img/Content/avatar/avatar2.png");
        studentDto3.setSpecialization("UBB AC");

        return studentDto3;
    }

    @Test
    @SneakyThrows
    public void addStudent() throws Exception {
        this.initDto();
        StudentDto studentDto = this.dtoInit();
        when(studentService.addStudent(any(StudentDto.class))).thenReturn(studentDto);
        String basePath = "/students";
        mockMvc.perform(post(basePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(studentDto.getName())))
                .andExpect(jsonPath("$.specialization", is(studentDto.getSpecialization())))
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getAllStudents() throws Exception {
        this.initDto();
        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList.add(studentDto1);
        studentDtoList.add(studentDto2);

        when(studentService.findAllStudents()).thenReturn(studentDtoList);
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(studentDto1.getName())))
                .andExpect(jsonPath("$[0].specialization", is(studentDto1.getSpecialization())))
                .andExpect(jsonPath("$[1].name", is(studentDto2.getName())))
                .andExpect(jsonPath("$[1].specialization", is(studentDto2.getSpecialization())));

        verify(studentService).findAllStudents();
    }

    @Test
    @SneakyThrows
    void getStudentById() throws Exception {
        this.initDto();
        when(studentService.getStudentById(1L)).thenReturn(studentDto1);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(studentDto1.getName())))
                .andExpect(jsonPath("$.specialization", is(studentDto1.getSpecialization())))
                .andReturn();
        verify(studentService).getStudentById(1L);

    }

    @Test
    @SneakyThrows
    void updateStudent() throws Exception {
        this.initDto();
        when(studentService.updateStudent(studentDto2)).thenReturn(studentDto2);
        mockMvc.perform(MockMvcRequestBuilders.put("/students/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDto2)))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void deleteStudent() throws Exception {
        this.initDto();
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/students/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
