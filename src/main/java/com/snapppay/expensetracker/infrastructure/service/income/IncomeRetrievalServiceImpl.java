package com.snapppay.expensetracker.infrastructure.service.income;

import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeFilter;
import com.snapppay.expensetracker.domain.service.income.IncomeRetrievalService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.income.IncomeEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeRetrievalServiceImpl implements IncomeRetrievalService {

  private final IncomeRepository incomeRepository;
  private final ModelMapper modelMapper;

  @Override
  public Page<Income> getAll(IncomeFilter filter) {
    PageRequest pageable = PageRequest.of(filter.page(), filter.size(),
        Sort.by("createdAt").descending());

    return incomeRepository.getAll(filter.userId(), filter.source(), filter.fromDate(),
            filter.toDate(),
            pageable)
        .map(this::mapToDomain);
  }

  private Income mapToDomain(IncomeEntity incomeEntity) {
    return modelMapper.map(incomeEntity, Income.class);
  }

}
