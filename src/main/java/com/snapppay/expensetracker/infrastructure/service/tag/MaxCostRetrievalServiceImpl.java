package com.snapppay.expensetracker.infrastructure.service.tag;


import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.service.tag.MaxCostRetrievalService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.MaxCostRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaxCostRetrievalServiceImpl implements MaxCostRetrievalService {

  private final MaxCostRepository maxCostRepository;
  private final ModelMapper modelMapper;


  @Override
  public List<MaxCost> getAll(UUID id) {

    List<MaxCostEntity> maxCostEntities = maxCostRepository.findByTagId(id);

    if (maxCostEntities.isEmpty()) {
      return List.of();
    }

    return maxCostEntities.stream()
        .map(this::mapToDomain)
        .toList();
  }


  private MaxCost mapToDomain(MaxCostEntity maxCostEntity) {
    return modelMapper.map(maxCostEntity, MaxCost.class);
  }

}
