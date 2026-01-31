package com.snapppay.expensetracker.api.dto.tag.mapper;

import com.snapppay.expensetracker.api.dto.tag.TagDto;
import com.snapppay.expensetracker.domain.model.tag.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

  public TagDto toDto(Tag tag) {
    return TagDto.builder()
        .id(tag.getId())
        .name(tag.getName())
        .createdAt(tag.getCreatedAt())
        .updatedAt(tag.getUpdatedAt())
        .build();
  }

}
