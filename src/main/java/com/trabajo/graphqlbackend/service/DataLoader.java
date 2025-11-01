package com.trabajo.graphqlbackend.service;

import com.trabajo.graphqlbackend.model.Student;
import com.trabajo.graphqlbackend.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@Component
public class DataLoader {

    private final StudentRepository studentRepo;
    public DataLoader(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        if(studentRepo.count() == 0) {
            studentRepo.save(new Student("s1","Ana Gómez","Ingeniería de Sistemas",5,4.2));
            studentRepo.save(new Student("s2","Luis Rojas","Medicina",3,3.8));
            studentRepo.save(new Student("s3","Sara Ruiz","Derecho",7,4.5));
            studentRepo.save(new Student("s4","Kevin López","Ingeniería Industrial",6,3.6));
            studentRepo.save(new Student("s5","Diana Pérez","Psicología",2,4.0));
        }
    }
}
