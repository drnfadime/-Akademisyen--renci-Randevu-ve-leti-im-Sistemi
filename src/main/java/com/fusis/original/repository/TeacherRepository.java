package com.fusis.original.repository;

import com.fusis.original.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {


    Optional<Teacher> findByEmail(String email);


    boolean existsByEmail(String email);







    
}