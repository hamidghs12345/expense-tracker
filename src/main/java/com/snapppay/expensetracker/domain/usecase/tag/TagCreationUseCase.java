package com.snapppay.expensetracker.domain.usecase.tag;

import com.snapppay.expensetracker.domain.error.Error.BadRequestException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.tag.Tag;
import com.snapppay.expensetracker.domain.model.tag.TagInfo;
import com.snapppay.expensetracker.domain.service.tag.TagCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagCreationUseCase {

  private final TagCreationService tagCreationService;

  public Tag create(TagInfo tagInfo) {

    checkInputValidity(tagInfo.name());

    return tagCreationService.create(tagInfo);
  }

  private void checkInputValidity(String name) {
    if (name == null) {
      throw new BadRequestException(ErrorEnum.REQUIRED_FIELD);
    }
  }
}
