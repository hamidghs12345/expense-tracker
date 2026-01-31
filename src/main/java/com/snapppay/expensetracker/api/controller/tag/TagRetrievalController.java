package com.snapppay.expensetracker.api.controller.tag;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.tag.TagDto;
import com.snapppay.expensetracker.api.dto.tag.mapper.TagMapper;
import com.snapppay.expensetracker.domain.model.tag.TagFilter;
import com.snapppay.expensetracker.domain.usecase.tag.TagRetrievalUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Tag")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/tags")
public class TagRetrievalController extends AbstractController {

  private final TagRetrievalUseCase tagRetrievalUseCase;
  private final TagMapper tagMapper;

  @GetMapping("/me")
  @PreAuthorize("hasAnyAuthority('USER')")
  public Page<TagDto> getTags(
      @RequestParam(required = false) String name,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdFromDate,
      @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdToDate
  ) {
    ZonedDateTime fromZonedDateTime = null;
    ZonedDateTime toZonedDateTime = null;

    if (createdFromDate != null) {
      fromZonedDateTime = createdFromDate.atStartOfDay().atZone(ZoneId.of("Z"));
    }

    if (createdToDate != null) {
      toZonedDateTime = createdToDate.atTime(LocalTime.MAX).atZone(ZoneId.of("Z"));
    }

    TagFilter tagFilter = TagFilter.builder()
        .userId(getCurrentUserId())
        .name(name)
        .fromDate(fromZonedDateTime)
        .toDate(toZonedDateTime)
        .page(page)
        .size(size)
        .build();

    Page<com.snapppay.expensetracker.domain.model.tag.Tag> tags = tagRetrievalUseCase.getAll(tagFilter);

    return tags.map(tagMapper::toDto);
  }

}
