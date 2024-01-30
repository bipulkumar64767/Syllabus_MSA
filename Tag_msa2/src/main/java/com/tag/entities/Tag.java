package com.tag.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tag_table")
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="tagId")
    private Integer tagId;
    @Column(name = "tagName")
    private String tagName;
    @Column(name = "tagDesc")
    private String tagDesc;
    @Column(name = "tagStatus")
    private Boolean tagStatus;
}
