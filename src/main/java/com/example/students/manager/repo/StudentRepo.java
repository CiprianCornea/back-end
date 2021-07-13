package com.example.students.manager.repo;

import com.example.students.manager.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    void deleteStudentById(Long id);
}
