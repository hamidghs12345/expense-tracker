package com.snapppay.expensetracker.infrastructure.service.tag;

import com.snapppay.expensetracker.domain.model.tag.Tag;
import com.snapppay.expensetracker.domain.model.tag.TagFilter;
import com.snapppay.expensetracker.domain.service.tag.TagRetrievalService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.TagEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.TagRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagRetrievalServiceImpl implements TagRetrievalService {

  private final TagRepository tagRepository;
  private final ModelMapper modelMapper;

  @Override
  public Page<Tag> getAll(TagFilter filter) {
    PageRequest pageable = PageRequest.of(filter.page(), filter.size(),
        Sort.by("createdAt").descending());

    return tagRepository.getAll(filter.userId(), filter.name(), filter.fromDate(),
            filter.toDate(),
            pageable)
        .map(this::mapToDomain);
  }

  private Tag mapToDomain(TagEntity tagEntity) {
    return modelMapper.map(tagEntity, Tag.class);
  }

}
