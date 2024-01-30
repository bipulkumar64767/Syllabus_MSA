package com.tag.services;

import com.tag.configurations.LoggerConfig;
import com.tag.dto.TagDto;
import com.tag.entities.Tag;
import com.tag.repositories.TagRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagServices {
    private Logger logger = LoggerConfig.getLogger(TagServices.class);
    @Autowired
    TagRepository tagRepository;

    // Adding a new Tag
    public ResponseEntity < Tag > addNewTag(TagDto newTag) {
        String methodName = "addNewTag()";
        logger.info(methodName + " is called");
        if (newTag.getTagName() == null || newTag.getTagStatus() == null) {
            return new ResponseEntity < > (new Tag(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            Tag tag = new Tag();
            tag.setTagName(newTag.getTagName());
            tag.setTagDesc(newTag.getTagDesc());
            tag.setTagStatus(newTag.getTagStatus());

            return new ResponseEntity < > (tagRepository.save(tag), HttpStatus.OK);
        }
    }

    // Deleting a Tag
    public ResponseEntity < String > deleteTag(int id) {
        String methodName = "deleteTag()";
        logger.info(methodName + " is called");
        if (tagRepository.findById(id).isPresent()) {
            tagRepository.deleteById(id);
            return new ResponseEntity < > ("Tag Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity < > ("Tag with this Id is Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // Finding a Tag By Id
    @Cacheable(cacheNames = "tag", key = "#id")
    public Tag getTagById(int id) {
        String methodName = "getTagById()";
        logger.info(methodName + " is called");
        Optional < Tag > optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            return optionalTag.get();
        } else {
            return new Tag();
        }
    }

    // Finding All Tags
    public ResponseEntity < List < Tag >> findingAllTag() {
        String methodName = "findingAllTag()";
        logger.info(methodName + " is called");
        ArrayList < Tag > tagList = (ArrayList < Tag > ) tagRepository.findAll();

        return new ResponseEntity < > (tagList, HttpStatus.OK);
    }

    //updating a Tag By its Id
    public ResponseEntity < ? > updateATag(int id, TagDto tagDto) {
        String methodName = "updateATag()";
        logger.info(methodName + " is called");
        if (!tagRepository.existsById(id)) {
            return new ResponseEntity < > ("Invalid Id Provided..", HttpStatus.NOT_FOUND);
        } else {
            Tag tag = new Tag();
            Tag existingTag = tagRepository.findById(id).get();
            if (tagDto.getTagId() == null) {
                tag.setTagId(existingTag.getTagId());
            } else {
                tag.setTagId(tagDto.getTagId());
            }
            if (tagDto.getTagStatus() == null) {
                tag.setTagStatus(existingTag.getTagStatus());
            } else {
                tag.setTagStatus(tagDto.getTagStatus());
            }
            if (tagDto.getTagName() == null) {
                tag.setTagName(existingTag.getTagName());
            } else {
                tag.setTagName(tagDto.getTagName());
            }
            if (tagDto.getTagDesc() == null) {
                tag.setTagDesc(existingTag.getTagDesc());
            } else {
                tag.setTagDesc(tagDto.getTagDesc());
            }
            tagRepository.save(tag);
            return new ResponseEntity < > (tag, HttpStatus.OK);
        }
    }

    //Getting All Inactive Tags
    public ResponseEntity < List < Tag >> findAllInactiveTag() {
        String methodName = "findAllInactiveTag()";
        logger.info(methodName + " is called");
        List < Tag > tagList = tagRepository.findAll();
        List < Tag > finalList = new ArrayList < > ();
        for (Tag tag: tagList) {
            if (!tag.getTagStatus()) {
                finalList.add(tag);
            }
        }
        if (finalList.isEmpty()) {
            return new ResponseEntity < > (new ArrayList < > (), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity < > (finalList, HttpStatus.OK);
        }
    }
}