package com.example.education.service;

import com.example.education.dto.CourseRequestDTO;
import com.example.education.dto.CourseStudentsDTO;
import com.example.education.dto.StudentSimpleDTO;
import com.example.education.entity.Course;
import com.example.education.entity.Educator;
import com.example.education.repository.CourseRepository;
import com.example.education.repository.EducatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EducatorRepository educatorRepository;

    public CourseService(CourseRepository courseRepository, EducatorRepository educatorRepository){
        this.courseRepository = courseRepository;
        this.educatorRepository = educatorRepository;
    }

    public Course addCourse(CourseRequestDTO dto){

        Course course = new Course();

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setMaxCapacity(dto.getMaxCapacity());


        if(dto.getEducatorId() != null){
            Educator educator = educatorRepository.findById(dto.getEducatorId())
                    .orElseThrow(() -> new RuntimeException("Educator not found"));

            course.setEducator(educator);
        }

        return courseRepository.save(course);

    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteCourseById(Long id){
        if(!courseRepository.existsById(id)){
            throw new RuntimeException("Course not found");
        }

        courseRepository.deleteById(id);
    }

    public CourseStudentsDTO getStudentsForCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<StudentSimpleDTO> studentDTOs = course.getStudents()
                .stream()
                .map(student -> new StudentSimpleDTO(
                        student.getId(),
                        student.getName(),
                        student.getAge()
                ))
                .toList();

        return new CourseStudentsDTO(
                course.getId(),
                course.getTitle(),
                studentDTOs
        );
    }


    public Course updateCourse(Long id, CourseRequestDTO dto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));


        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setMaxCapacity(dto.getMaxCapacity());


        if (dto.getEducatorId() != null) {

            Educator educator = educatorRepository.findById(dto.getEducatorId())
                    .orElseThrow(() -> new RuntimeException("Educator not found"));

            course.setEducator(educator);
        }


        return courseRepository.save(course);
    }


}
