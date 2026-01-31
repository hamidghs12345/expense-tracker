package com.snapppay.expensetracker.infrastructure.service.expense;

import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseFilter;
import com.snapppay.expensetracker.domain.service.expense.ExpenseRetrievalService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.ExpenseEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseRetrievalServiceImpl implements ExpenseRetrievalService {

  private final ExpenseRepository expenseRepository;
  private final ModelMapper modelMapper;


  @Override
  public Page<Expense> getAll(ExpenseFilter filter) {

    PageRequest pageable = PageRequest.of(filter.page(), filter.size(),
        Sort.by("createdAt").descending());

    return expenseRepository.getAll(filter.tagId(), filter.fromDate(),
            filter.toDate(),
            pageable)
        .map(this::mapToDomain);

  }

  private Expense mapToDomain(ExpenseEntity expenseEntity) {
    return modelMapper.map(expenseEntity, Expense.class);
  }
}
