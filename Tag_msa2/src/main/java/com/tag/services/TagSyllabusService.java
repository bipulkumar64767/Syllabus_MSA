package com.tag.services;

import com.tag.configurations.LoggerConfig;
import com.tag.constants.ConstantMessages;
import com.tag.dto.SyllabusDto;
import com.tag.dto.TagSyllabusDto;
import com.tag.entities.Tag;
import com.tag.entities.TagSyllabus;
import com.tag.repositories.TagRepository;
import com.tag.repositories.TagSyllabusRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagSyllabusService {
    Logger logger = LoggerConfig.getLogger(TagSyllabusService.class);

    @Autowired
    TagSyllabusRepository tagSyllabusRepository;

    @Autowired
    SyllabusFeign syllabusFeign;

    @Autowired
    TagServices tagServices;

    @Autowired
    TagRepository tagRepository;

    // Adding Syllabus-Tag relation if Syllabus is Currently Active.
    public ResponseEntity < ? > addSyllabusTags(TagSyllabusDto tagSyllabusdto) {
        String methodName = "addSyllabus()";
        logger.info(methodName + " is called");
        TagSyllabus tagSyllabus = new TagSyllabus();
        int syllabusId = tagSyllabusdto.getSyllabusId();
        int TagId = tagSyllabusdto.getTagId();
        Boolean tagstatus = tagServices.getTagById(TagId).getTagStatus();
        if (!syllabusFeign.checkSyllabusIdPresence(syllabusId)) {
            return new ResponseEntity < > (ConstantMessages.SYLLABUS_ERROR, HttpStatus.BAD_REQUEST);
        } else if (syllabusFeign.findSyllabusById(syllabusId).getBody().getSExpiry()) {
            return new ResponseEntity < > (ConstantMessages.SYLLABUS_ERROR, HttpStatus.BAD_REQUEST);
        } else if (tagstatus == null) {
            return new ResponseEntity < > (ConstantMessages.TAG_ERROR, HttpStatus.NOT_FOUND);
        } else if (!tagstatus) {
            return new ResponseEntity < > (ConstantMessages.TAG_ERROR, HttpStatus.NOT_FOUND);
        } else {
            tagSyllabus.setTagId(tagSyllabusdto.getTagId());
            tagSyllabus.setSyllabusId(tagSyllabusdto.getSyllabusId());
            tagSyllabus.setStatus(tagSyllabusdto.getStatus());
            tagSyllabusRepository.save(tagSyllabus);
            return new ResponseEntity < > (tagSyllabus, HttpStatus.OK);
        }
    }

    // fetching all Syllabus tag
    public ResponseEntity < List < TagSyllabus >> getAllSyllabusTags() {
        String methodName = "getAllSyllabusTags()";
        logger.info(methodName + " is called");
        List < TagSyllabus > tagSyllabusList = tagSyllabusRepository.findAll();
        if (!tagSyllabusList.isEmpty()) {
            return new ResponseEntity < > (tagSyllabusList, HttpStatus.OK);
        } else {
            return new ResponseEntity < > (new ArrayList < > (), HttpStatus.NOT_FOUND);
        }
    }

    // fetching SyllabusTag by its MappingId
    public ResponseEntity < TagSyllabus > getSyllabusTagById(int id) {
        String methodName = "getSyllabusTagById()";
        logger.info(methodName + " is called");
        TagSyllabus MappingLists = tagSyllabusRepository.findAllMappingsByMappingIDs(id);
        if (MappingLists == null) {
            return new ResponseEntity < > (new TagSyllabus(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity < > (MappingLists, HttpStatus.OK);
        }
    }

    //fetching List of Syllabus by its Tag id
    public ResponseEntity < ArrayList < SyllabusDto >> findSyllabusListByTagId(int tagid) {
        String methodName = "findSyllabusListByTagId()";
        logger.info(methodName + " is called");
        ArrayList < Integer > syllabusList = (ArrayList < Integer > ) tagSyllabusRepository.findAllSyllabusBytagID(tagid);
        if (syllabusList.isEmpty()) {
            return new ResponseEntity < > (new ArrayList < > (), HttpStatus.NOT_FOUND);
        } else {
            ArrayList < SyllabusDto > syllabusLists = new ArrayList < > ();
            for (Integer i: syllabusList) {
                SyllabusDto instance1 = syllabusFeign.findSyllabusById(i).getBody();
                syllabusLists.add(instance1);
            }
            return new ResponseEntity < > (syllabusLists, HttpStatus.OK);
        }
    }

    // Finding TagDetails By Syllabus Id
    public ResponseEntity < ArrayList < Tag >> findTagListBySyllabusId(int syllabusId) {
        String methodName = "findTagListBySyllabusId()";
        logger.info(methodName + " is called");
        ArrayList < Integer > tagLists = (ArrayList < Integer > ) tagSyllabusRepository.findAllTagsBySyllabusID(syllabusId);
        if (tagLists.isEmpty()) {
            return new ResponseEntity < > (new ArrayList < > (), HttpStatus.NOT_FOUND);
        } else {
            ArrayList < Tag > tagObjets = new ArrayList < > ();
            for (Integer i: tagLists) {
                Tag tag = tagServices.getTagById(i);
                tagObjets.add(tag);
            }
            return new ResponseEntity < > (tagObjets, HttpStatus.OK);
        }
    }
}