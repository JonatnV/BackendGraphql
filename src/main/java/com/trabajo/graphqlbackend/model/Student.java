package com.trabajo.graphqlbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "students")
public class Student {

    @Id
    private String id;
    private String name;
    private String career;
    private Integer semester;
    private Double gpa;

    public Student() {}

    public Student(String id, String name, String career, Integer semester, Double gpa){
        this.id = id; this.name = name; this.career = career; this.semester = semester; this.gpa = gpa;
    }

    // getters & setters...
    public String getId(){return id;}
    public void setId(String id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getCareer(){return career;}
    public void setCareer(String career){this.career=career;}
    public Integer getSemester(){return semester;}
    public void setSemester(Integer semester){this.semester=semester;}
    public Double getGpa(){return gpa;}
    public void setGpa(Double gpa){this.gpa=gpa;}
}
