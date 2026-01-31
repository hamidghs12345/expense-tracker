package com.snapppay.expensetracker.infrastructure.service.tag;

import com.snapppay.expensetracker.domain.error.Error.UnauthorizedException;
import com.snapppay.expensetracker.domain.model.tag.Tag;
import com.snapppay.expensetracker.domain.model.tag.TagInfo;
import com.snapppay.expensetracker.domain.service.tag.TagCreationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.UserEntity;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.TagEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.TagRepository;
import com.snapppay.expensetracker.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagCreationServiceImpl implements TagCreationService {

  private final ModelMapper modelMapper;
  private final UserRepository userRepository;
  private final TagRepository tagRepository;

  @Override
  public Tag create(TagInfo tagInfo) {

    UserEntity userEntity = userRepository.findById(tagInfo.userId())
        .orElseThrow(UnauthorizedException::new);

    TagEntity tagEntity = TagEntity.builder()
        .name(tagInfo.name())
        .userId(userEntity.getId())
        .build();

    tagRepository.save(tagEntity);

    return modelMapper.map(tagEntity, Tag.class);
  }

}
