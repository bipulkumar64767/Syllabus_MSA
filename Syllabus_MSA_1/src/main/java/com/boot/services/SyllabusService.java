package com.boot.services;

import com.boot.configurations.LoggerConfig;
import com.boot.dto.SyllabusDto;
import com.boot.entities.Syllabus;
import com.boot.repositories.SyllabusRepo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SyllabusService {
    private Logger logger = LoggerConfig.getLogger(SyllabusService.class);

    @Autowired
    SyllabusRepo syllabusRepo;

    //Adding a Syllabus by getting from dto and setting to entity.
    public ResponseEntity < String > addSyllabus(SyllabusDto syllabusDto) {
        String methodName = "addSyllabus()";
        logger.info(methodName + " is called");

        if (syllabusDto.getsExpiry() == null || syllabusDto.getsMedium() == null || syllabusDto.getsName() == null) {
            return new ResponseEntity < > ("Some fields are not provided Or may be error in DataTypes", HttpStatus.NOT_ACCEPTABLE);
        } else {
            Syllabus syllabus = new Syllabus();

            syllabus.setsName(syllabusDto.getsName());
            syllabus.setsMedium(syllabusDto.getsMedium());
            syllabus.setsStatus(syllabusDto.getsStatus());
            syllabus.setsDesc(syllabusDto.getsDesc());
            syllabus.setsExpiry(syllabusDto.getsExpiry());
            syllabusRepo.save(syllabus);
            return new ResponseEntity < > ("Syllabus Added with id = " + syllabus.getsId(), HttpStatus.OK);

        }
    }

    //Deleting a Syllabus by getting id from dto.
    @CacheEvict(value = "syllabus", key = "#id")
    public void deleteSyllabus(int id) {
        syllabusRepo.deleteById(id);
    }

    //finding Syllabus by SyllabusId
    public ResponseEntity < Syllabus > findBySyllabusId(int id) {
        if (!syllabusRepo.existsById(id)) {
            return new ResponseEntity < > (new Syllabus(), HttpStatus.NOT_FOUND);
        } else {
            Optional < Syllabus > optionalSyllabus = syllabusRepo.findById(id);
            if (optionalSyllabus.isPresent()) {
                return new ResponseEntity < > (optionalSyllabus.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity < > (optionalSyllabus.get(), HttpStatus.NOT_FOUND);
            }
        }
    }

    //finding All Syllabus
    public ResponseEntity < List < Syllabus >> getAllSyllabus() {
        ArrayList < Syllabus > syllabusArrayList = new ArrayList < > (syllabusRepo.findAll());
        if (!syllabusArrayList.isEmpty()) {
            return new ResponseEntity < > (syllabusArrayList, HttpStatus.OK);
        } else {
            return new ResponseEntity < > (syllabusArrayList, HttpStatus.NOT_FOUND);
        }

    }

    // getting Inactive Syllabus
    public ResponseEntity < ? > getAllInactiveSyllabus() {
        List < Syllabus > inActiveSyllabus = new ArrayList < > (syllabusRepo.getAllInactiveSyllabus());
        if (inActiveSyllabus.isEmpty()) {
            return new ResponseEntity < > ("No InActive Syllabus are Found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity < > (inActiveSyllabus, HttpStatus.OK);
        }
    }

    //Updating Syllabus with Adding validations if the input of updation is null then we will add exixing data.
    public ResponseEntity < Syllabus > updateSyllabus(int id, SyllabusDto syllabus) {
        Syllabus updatedsyllabus = new Syllabus();

        Syllabus existingSyllabus = syllabusRepo.findById(id).get();
        if (syllabus.getsId() == null) {
            updatedsyllabus.setsId(existingSyllabus.getsId());
        }
        if (syllabus.getsName() == null) {
            updatedsyllabus.setsName(existingSyllabus.getsName());
        } else {
            updatedsyllabus.setsName(syllabus.getsName());
        }
        if (syllabus.getsMedium() == null) {
            updatedsyllabus.setsMedium(existingSyllabus.getsMedium());
        } else {
            updatedsyllabus.setsMedium(syllabus.getsMedium());
        }
        if (syllabus.getsStatus() == null) {
            updatedsyllabus.setsStatus(existingSyllabus.getsStatus());
        } else {
            updatedsyllabus.setsStatus(syllabus.getsStatus());
        }
        if (syllabus.getsDesc() == null) {
            updatedsyllabus.setsDesc(existingSyllabus.getsDesc());
        } else {
            updatedsyllabus.setsDesc(syllabus.getsDesc());
        }
        if (syllabus.getsExpiry() == null) {
            updatedsyllabus.setsExpiry(existingSyllabus.getsExpiry());
        } else {
            updatedsyllabus.setsExpiry(syllabus.getsExpiry());
        }
        syllabusRepo.save(updatedsyllabus);
        return new ResponseEntity < > (updatedsyllabus, HttpStatus.OK);

    }
    public boolean checkId(int id) {
        return syllabusRepo.existsById(id);
    }
}