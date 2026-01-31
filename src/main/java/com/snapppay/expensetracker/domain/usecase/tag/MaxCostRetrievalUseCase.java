package com.snapppay.expensetracker.domain.usecase.tag;

import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.service.tag.MaxCostRetrievalService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaxCostRetrievalUseCase {

  private final MaxCostRetrievalService maxCostRetrievalService;

  public List<MaxCost> getAll(UUID id) {
    return maxCostRetrievalService.getAll(id);
  }
}