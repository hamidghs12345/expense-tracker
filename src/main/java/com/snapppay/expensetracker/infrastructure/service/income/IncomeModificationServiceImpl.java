package com.snapppay.expensetracker.infrastructure.service.income;

import com.snapppay.expensetracker.domain.error.Error.NotFoundException;
import com.snapppay.expensetracker.domain.service.income.IncomeModificationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.income.IncomeEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.IncomeRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IncomeModificationServiceImpl implements IncomeModificationService {

  private final IncomeRepository incomeRepository;

  @Override
  @Transactional
  public void delete(UUID id) {

    IncomeEntity incomeEntity = incomeRepository.findById(id)
        .orElseThrow(NotFoundException::new);

    incomeRepository.delete(incomeEntity);
  }

}
