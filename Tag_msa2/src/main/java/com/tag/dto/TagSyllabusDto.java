package com.tag.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagSyllabusDto {
    @NotNull(message = "Integer Value is Needed")
    private Integer syllabusId;
    @NotNull(message = "Tag id cannot be null")
    private Integer tagId;
    @NotNull(message = "status cannot be null")
    private Boolean status;
}
