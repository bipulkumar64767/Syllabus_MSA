package com.tag.repositories;

import com.tag.constants.ConstantMessages;
import com.tag.dto.SyllabusDto;
import com.tag.dto.TagSyllabusDto;
import com.tag.entities.TagSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagSyllabusRepository extends JpaRepository<TagSyllabus,Integer> {
    @Query(value = "SELECT * FROM tag_syllabus where mapping_id =:id",nativeQuery = true)
    public TagSyllabus findAllMappingsByMappingIDs(int id);

    @Query(value = "SELECT DISTINCT syllabus_id FROM tag_syllabus where tag_id =:id",nativeQuery = ConstantMessages.VALUE_TRUE)
    public List<Integer> findAllSyllabusBytagID(int id);

    @Query(value = "SELECT DISTINCT tag_id FROM tag_syllabus where syllabus_id =:id",nativeQuery = ConstantMessages.VALUE_TRUE)
    public List<Integer> findAllTagsBySyllabusID(int id);
}
