package com.example.education.service;

import com.example.education.dto.CourseSimpleDTO;
import com.example.education.entity.Course;
import com.example.education.entity.Student;
import com.example.education.repository.CourseRepository;
import com.example.education.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("student not found"));
    }

    public void deleteStudentById(Long id){
        if(!studentRepository.existsById(id)){
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }



    public Student enrollStudent(Long studentId, Long courseId) {


        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));


        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));


        if (student.getCourses().contains(course)) {
            throw new RuntimeException("Student already enrolled in this course");
        }


        if (course.getStudents().size() >= course.getMaxCapacity()) {
            throw new RuntimeException("Course capacity full");
        }
        student.getCourses().add(course);
        return studentRepository.save(student);
    }



    public Student dropCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!student.getCourses().contains(course)) {
            throw new RuntimeException("Student not enrolled in this course");
        }
        student.getCourses().remove(course);
        course.getStudents().remove(student);

        return studentRepository.save(student);
    }



    public Student updateStudent(Long id, Student updatedStudent) {


        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());

        return studentRepository.save(student);
    }




    public List<CourseSimpleDTO> getCoursesOfStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getCourses()
                .stream()
                .map(course -> new CourseSimpleDTO(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        course.getMaxCapacity()
                ))
                .toList();
    }




}
