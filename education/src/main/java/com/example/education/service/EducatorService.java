package com.example.education.service;

import com.example.education.entity.Educator;
import com.example.education.repository.EducatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducatorService {

    private final EducatorRepository educatorRepository;

    public EducatorService(EducatorRepository educatorRepository){
        this.educatorRepository = educatorRepository;
    }

    public Educator addEducator(Educator educator){
        return educatorRepository.save(educator);
    }

    public List<Educator> getAllEducators(){
        return educatorRepository.findAll();
    }

    public Educator getEducatorById(Long id){
        return educatorRepository.findById(id).orElse(null);
    }

    public void deleteEducatorById(Long id){
        if(!educatorRepository.existsById(id)){
            throw new RuntimeException("Educator doesn't exists!");
        }
        educatorRepository.deleteById(id);
    }

    public Educator updateEducator(Long id, Educator updatedEducator) {

        Educator educator = educatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Educator not found"));

        educator.setName(updatedEducator.getName());
        educator.setSpecialization(updatedEducator.getSpecialization());

        return educatorRepository.save(educator);
    }

}
