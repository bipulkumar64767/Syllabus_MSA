package com.boot.repositories;

import com.boot.entities.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusRepo extends JpaRepository<Syllabus ,Integer>
{
    @Query(value = "select * from syllabus_table where s_expiry =true",nativeQuery = true)
    List<Syllabus> getAllInactiveSyllabus();
}
