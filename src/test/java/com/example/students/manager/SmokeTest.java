package com.example.students.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class SmokeTest {
    @Autowired
    private StudentController controller;

    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }
}
