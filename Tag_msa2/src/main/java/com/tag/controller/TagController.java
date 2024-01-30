package com.tag.controller;

import com.tag.configurations.LoggerConfig;
import com.tag.constants.ConstantMessages;
import com.tag.dto.TagDto;
import com.tag.entities.Tag;
import com.tag.repositories.TagRepository;
import com.tag.services.TagServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController("/tag")
public class TagController {
    private Logger loger = LoggerConfig.getLogger(TagController.class);

    @Autowired
    TagServices tagServices;
    @Autowired
    TagRepository repo;

    // Inserting a New tag
    @PostMapping("/addNewTag")
    public ResponseEntity < Tag > addTag(@RequestBody TagDto tag) {
        String methodName = "add()";
        loger.info(methodName + " is called");

        return tagServices.addNewTag(tag);
    }

    // Deleting a Tag By Id
    @DeleteMapping("/deleteTagById/{id}")
    public ResponseEntity < String > deleteTagById(@PathVariable int id) {
        String methodName = "deleteTagById()";
        loger.info(methodName + " is called");

        return tagServices.deleteTag(id);
    }

    //Getting a Tag By id
    @GetMapping("/getTagById/{id}")
    public ResponseEntity < ? > getTagById(@PathVariable int id) {
        String methodName = "getTagById()";
        loger.info(methodName + " is called");
        Tag tag = tagServices.getTagById(id);
        if (tag.getTagId() == null) {
            return new ResponseEntity < > (ConstantMessages.INVALID_TAG, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity < > (tag, HttpStatus.OK);
        }
    }

    //Getting All tag
    @GetMapping("/getAllTags")
    public ResponseEntity < List < Tag >> getAllTag() {
        String methodName = "getAllTag()";
        loger.info(methodName + " is called");
        return tagServices.findingAllTag();
    }

    //Updating Tag by tag id
    @PutMapping("/updateTagById/{id}")
    public ResponseEntity < ? > updateTag(@PathVariable int id, @RequestBody TagDto tagDto) {
        String methodName = "updateTag()";
        loger.info(methodName + " is called");
        return tagServices.updateATag(id, tagDto);
    }

    //Getting All Inactive Tags from the DB
    @GetMapping("/getAllInActiveTags/inactive")
    public ResponseEntity < List < Tag >> getAllInactiveTag() {
        String methodName = "getAllInactiveTag()";
        loger.info(methodName + " is called");
        return tagServices.findAllInactiveTag();
    }
}