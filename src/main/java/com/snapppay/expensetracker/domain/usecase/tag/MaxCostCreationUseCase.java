package com.snapppay.expensetracker.domain.usecase.tag;

import com.snapppay.expensetracker.api.dto.tag.MaxCostReq;
import com.snapppay.expensetracker.domain.error.Error.BadRequestException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.model.tag.MaxCostInfo;
import com.snapppay.expensetracker.domain.model.tag.Tag;
import com.snapppay.expensetracker.domain.service.tag.MaxCostCreationService;
import com.snapppay.expensetracker.domain.service.tag.TagRetrievalService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MaxCostCreationUseCase {

  private final MaxCostCreationService maxCostCreationService;
  private final TagRetrievalService tagRetrievalService;

  @Transactional
  public List<MaxCost> create(UUID id, List<MaxCostReq> maxCosts) {
    checkInputValidity(id, maxCosts);

    Tag tag = tagRetrievalService.get(id);

    List<MaxCost> maxCostList = new ArrayList<>();

    maxCosts.forEach(mc -> {

      MaxCostInfo maxCostInfo = MaxCostInfo.builder()
          .tagId(tag.getId())
          .amount(mc.getAmount())
          .period(mc.getPeriod())
          .build();

      MaxCost maxCost = maxCostCreationService.create(maxCostInfo);
      maxCostList.add(maxCost);
    });

    return maxCostList;
  }

  private void checkInputValidity(UUID id, List<MaxCostReq> maxCosts) {
    if (id == null) {
      throw new BadRequestException(ErrorEnum.REQUIRED_FIELD);
    }

    if (maxCosts.isEmpty()) {
      throw new BadRequestException(ErrorEnum.REQUIRED_FIELD);
    }


  }
}
