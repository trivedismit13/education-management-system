package com.example.education.dto;

public class StudentSimpleDTO {

    private Long id;
    private String name;
    private int age;

    public StudentSimpleDTO(){

    }
    public StudentSimpleDTO(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
}
