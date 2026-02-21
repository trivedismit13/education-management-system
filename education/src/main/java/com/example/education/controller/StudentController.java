package com.example.education.controller;

import java.util.List;

import com.example.education.dto.CourseSimpleDTO;
import com.example.education.entity.Student;
import com.example.education.service.StudentService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public Student enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId){
        return studentService.enrollStudent(studentId,courseId);
    }

    @DeleteMapping("/{studentId}/drop/{courseId}")
    public Student dropCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        return studentService.dropCourse(studentId, courseId);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @GetMapping("/{id}/courses")
    public List<CourseSimpleDTO> getCoursesOfStudent(@PathVariable Long id) {
        return studentService.getCoursesOfStudent(id);
    }


}
