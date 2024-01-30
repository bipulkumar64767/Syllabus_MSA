package com.tag.services;

import com.tag.dto.SyllabusDto;
import com.tag.exceptions.ClientNotActiveException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "SYLLABUS-MSA")
public interface SyllabusFeign {
    @GetMapping("/syllabus/findAllSyllabus")
    ResponseEntity<List<SyllabusDto>> findAllSyllabus()throws ClientNotActiveException;

    @GetMapping("/syllabus/inactive/findAllInactive")
    ResponseEntity<?> findAllInActiveSyllabus()throws ClientNotActiveException;

    @GetMapping("/syllabus/findById/{id}")
    ResponseEntity<SyllabusDto> findSyllabusById(@PathVariable int id)throws ClientNotActiveException;

    @GetMapping("/syllabus/checkSyllabus/{id}")
    Boolean checkSyllabusIdPresence(@PathVariable int id)throws ClientNotActiveException;
}
