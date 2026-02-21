package com.example.education.repository;

import com.example.education.entity.Educator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducatorRepository extends JpaRepository<Educator, Long> {
}
