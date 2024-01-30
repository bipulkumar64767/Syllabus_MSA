package com.tag.repositories;

import com.tag.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface TagRepository extends JpaRepository<Tag,Integer>, Serializable {
}
