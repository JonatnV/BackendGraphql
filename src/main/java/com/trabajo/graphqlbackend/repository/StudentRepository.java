package com.trabajo.graphqlbackend.repository;

import com.trabajo.graphqlbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student, String> {
}
