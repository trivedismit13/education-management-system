package com.example.education.controller;

import com.example.education.dto.CourseRequestDTO;
import com.example.education.dto.CourseStudentsDTO;
import com.example.education.entity.Course;
import com.example.education.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping
    public Course addCourse(@RequestBody CourseRequestDTO dto){
        return courseService.addCourse(dto);
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable Long id){
        courseService.deleteCourseById(id);
    }

    @GetMapping("/{id}/students")
    public CourseStudentsDTO getStudentsForCourse(@PathVariable Long id) {
        return courseService.getStudentsForCourse(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id,
                               @RequestBody CourseRequestDTO dto) {
        return courseService.updateCourse(id, dto);
    }

}
