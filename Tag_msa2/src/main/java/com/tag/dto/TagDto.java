package com.tag.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Data
public class TagDto implements Serializable {
    private Integer tagId;
    @NotNull
    private String tagName;
    private String tagDesc;
    @NotNull
    private Boolean tagStatus;
}
