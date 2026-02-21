package com.example.education.controller;

import com.example.education.entity.Educator;
import com.example.education.service.EducatorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educators")
@CrossOrigin(origins = "http://localhost:5173")
public class EducatorController {


    private final EducatorService educatorService;

    public EducatorController(EducatorService educatorService) {
        this.educatorService = educatorService;
    }

    @PostMapping
    public Educator addEducator(@RequestBody Educator educator){
        return educatorService.addEducator(educator);
    }

    @GetMapping
    public List<Educator> getAllEducators(){
        return educatorService.getAllEducators();
    }

    @GetMapping("/{id}")
    public Educator getEducatorById(@PathVariable Long id){
        return educatorService.getEducatorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEducatorById(@PathVariable Long id){
        educatorService.deleteEducatorById(id);
    }

    @PutMapping("/{id}")
    public Educator updateEducator(@PathVariable Long id,
                                   @RequestBody Educator educator) {
        return educatorService.updateEducator(id, educator);
    }

}
