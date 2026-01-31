package com.snapppay.expensetracker.domain.usecase.tag;

import com.snapppay.expensetracker.domain.model.tag.Tag;
import com.snapppay.expensetracker.domain.model.tag.TagFilter;
import com.snapppay.expensetracker.domain.service.tag.TagRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagRetrievalUseCase {

  private final TagRetrievalService tagRetrievalService;

  public Page<Tag> getAll(TagFilter tagFilter) {
    return tagRetrievalService.getAll(tagFilter);
  }
}