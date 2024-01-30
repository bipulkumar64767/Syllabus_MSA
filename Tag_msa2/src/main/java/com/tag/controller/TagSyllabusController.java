package com.tag.controller;

import com.tag.configurations.LoggerConfig;
import com.tag.dto.SyllabusDto;
import com.tag.dto.TagSyllabusDto;
import com.tag.entities.Tag;
import com.tag.entities.TagSyllabus;
import com.tag.services.TagSyllabusService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@Slf4j
@RestController("/syllabusTag")
public class TagSyllabusController {

    Logger logger = LoggerConfig.getLogger(TagSyllabusController.class);
    @Autowired
    TagSyllabusService tagSyllabusService;

    //Adding a SyllabusTag Mapping id in which Syllabus Status is in Active State.
    @PostMapping("/addMapping")
    public ResponseEntity < ? > addSyllabusTag(@Valid @RequestBody TagSyllabusDto tagSyllabusdto) {
        String methodName = "addSyllabus()";
        log.error(methodName + " is called");
        log.info(tagSyllabusdto.toString());
        return tagSyllabusService.addSyllabusTags(tagSyllabusdto);
    }

    //finding all SyllabusTags mapping data.
    @GetMapping("/findAllMappings")
    public ResponseEntity < List < TagSyllabus >> findAllTagSyllabus() {
        String methodName = "findAllTagSyllabus()";
        logger.info(methodName + " is called");
        return tagSyllabusService.getAllSyllabusTags();
    }

    // finding Mapping detail by MappingId.
    @GetMapping("/findMappingDetailByMappingId/{id}")
    public ResponseEntity < TagSyllabus > findSyllabusTagById(@PathVariable int id) {
        String methodName = "findSyllabusTagById()";
        logger.info(methodName + " is called");
        return tagSyllabusService.getSyllabusTagById(id);
    }

    //fetching List of SyllabusId by its Tag id
    @GetMapping("/findSyllabusListByTagId/{tagId}")
    public ResponseEntity < ArrayList < SyllabusDto >> findAllSyllabusByTagId(@PathVariable int tagId) {
        String methodName = "findSyllabusByTagId()";
        logger.info(methodName + " is called");
        return tagSyllabusService.findSyllabusListByTagId(tagId);
    }

    //fetching List of TagsDetails BY syllabus id
    @GetMapping("/findTagDetailsBySyllabusId/{syllabusId}")
    public ResponseEntity < ArrayList < Tag >> findTagNamesBySyllabusId(@PathVariable int syllabusId) {
        String methodName = "findTagNamesBySyllabusId()";
        logger.info(methodName + " is called");
        return tagSyllabusService.findTagListBySyllabusId(syllabusId);
    }
}