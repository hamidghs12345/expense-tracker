package com.snapppay.expensetracker.api.controller.tag;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.tag.TagCreationRequest;
import com.snapppay.expensetracker.api.dto.tag.TagDto;
import com.snapppay.expensetracker.api.dto.tag.mapper.TagMapper;
import com.snapppay.expensetracker.domain.model.tag.TagInfo;
import com.snapppay.expensetracker.domain.usecase.tag.TagCreationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Tag")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/tags")
public class TagCreationController extends AbstractController {

  private final TagCreationUseCase tagCreationUseCase;
  private final TagMapper tagMapper;


  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<TagDto> create(
      @RequestBody TagCreationRequest request
  ) {
    TagInfo tagInfo = TagInfo.builder()
        .userId(getCurrentUserId())
        .name(request.name())
        .build();

    com.snapppay.expensetracker.domain.model.tag.Tag tag = tagCreationUseCase.create(tagInfo);

    return ResponseEntity.ok(tagMapper.toDto(tag));
  }

}
