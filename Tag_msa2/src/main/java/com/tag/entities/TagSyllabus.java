package com.tag.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class TagSyllabus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mappingId;
    @Column(nullable = false)
    private Integer syllabusId;
    @Column(nullable = false)
    private Integer tagId;
    private Boolean status;
}
