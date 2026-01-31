package com.snapppay.expensetracker.infrastructure.service.tag;

import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.model.tag.MaxCostInfo;
import com.snapppay.expensetracker.domain.service.tag.MaxCostCreationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.MaxCostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaxCostCreationServiceImpl implements MaxCostCreationService {

  private final ModelMapper modelMapper;
  private final MaxCostRepository maxCostRepository;

  @Override
  public MaxCost create(MaxCostInfo maxCostInfo) {

    MaxCostEntity maxCostEntity = MaxCostEntity.builder()
        .tagId(maxCostInfo.getTagId())
        .amount(maxCostInfo.getAmount())
        .period(maxCostInfo.getPeriod())
        .build();

    maxCostRepository.save(maxCostEntity);

    return modelMapper.map(maxCostEntity, MaxCost.class);
  }

}
