package com.example.education.dto;

public class CourseSimpleDTO {

    private Long id;
    private String title;
    private String description;
    private int maxCapacity;

    public CourseSimpleDTO() {}

    public CourseSimpleDTO(Long id, String title, String description, int maxCapacity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maxCapacity = maxCapacity;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
}
