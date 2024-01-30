package com.boot.controllers;

import com.boot.configurations.LoggerConfig;
import com.boot.dto.SyllabusDto;
import com.boot.entities.Syllabus;
import com.boot.repositories.SyllabusRepo;
import com.boot.services.SyllabusService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController("/syllabus")
public class SyllabusController {
    // Creating logger of our raw class
    private Logger logger = LoggerConfig.getLogger(SyllabusController.class);

    @Autowired
   SyllabusService syllabusService;

//Adding a Syllabus Done using dto inorder to provide security to database.
    @PostMapping("/addSyllabus")
    public ResponseEntity<String> addSyllabus(@RequestBody SyllabusDto syllabus){
        return syllabusService.addSyllabus(syllabus);
    }

//Deleting a Syllabus by id
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteSyllabusByID(@PathVariable int id) {
        syllabusService.deleteSyllabus(id);
        return new ResponseEntity<>("Data Deleted..",HttpStatus.OK);
    }

//Retrieve a Syllabus by id
    @GetMapping("/findById/{id}")
    public ResponseEntity<Syllabus> findSyllabusById(@PathVariable int id) {
        return syllabusService.findBySyllabusId(id);
    }


//Retrieving all active Syllabus
    @GetMapping("/findAllSyllabus")
    public ResponseEntity<List<Syllabus>> findAllSyllabus() {
        return syllabusService.getAllSyllabus();
    }

//Getting All Inactive Syllabus
    @GetMapping("/inactive/findAllInactive")
    public ResponseEntity<?> findAllInActiveSyllabus() {
           return  syllabusService.getAllInactiveSyllabus();
    }

//Updating Syllabus  by checking the updated field
    @PutMapping("/updateById/{id}")
    public ResponseEntity<Syllabus> updateSyllabus(@PathVariable int id, @RequestBody SyllabusDto syllabus) {
        System.out.println(syllabus);

        return syllabusService.updateSyllabus(id, syllabus);
    }

//Boolean checking the Syllabus is present or not
    @GetMapping("/checkSyllabus/{id}")
    public Boolean checkSyllabusIdPresence(@PathVariable int id) {
        return syllabusService.checkId(id);
    }
}
