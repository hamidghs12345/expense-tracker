package com.snapppay.expensetracker.infrastructure.service.tag;

import com.snapppay.expensetracker.domain.error.Error.NotFoundException;
import com.snapppay.expensetracker.domain.service.tag.MaxCostModificationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.income.IncomeEntity;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.MaxCostRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaxCostModificationServiceImpl implements MaxCostModificationService {

  private final MaxCostRepository maxCostRepository;

  @Override
  public void delete(UUID id) {

    MaxCostEntity maxCostEntity = maxCostRepository.findById(
            id)
        .orElseThrow(NotFoundException::new);

    maxCostRepository.delete(maxCostEntity);
  }

}
