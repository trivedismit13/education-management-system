package com.example.education.dto;

import java.util.List;

public class CourseStudentsDTO {

    private Long courseId;
    private String title;
    private List<StudentSimpleDTO> students;

    public CourseStudentsDTO(){
    }

    public CourseStudentsDTO(Long courseId, String title, List<StudentSimpleDTO> students) {
        this.courseId = courseId;
        this.title = title;
        this.students = students;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public List<StudentSimpleDTO> getStudents() {
        return students;
    }
}
