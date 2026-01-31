package com.snapppay.expensetracker.api.controller.income;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.income.IncomeCreationRequest;
import com.snapppay.expensetracker.api.dto.income.IncomeDto;
import com.snapppay.expensetracker.api.dto.income.mapper.IncomeMapper;
import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeInfo;
import com.snapppay.expensetracker.domain.usecase.income.IncomeCreationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Income")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/incomes")
public class IncomeCreationController extends AbstractController {

  private final IncomeCreationUseCase incomeCreationUseCase;
  private final IncomeMapper incomeMapper;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<IncomeDto> create(
      @RequestBody IncomeCreationRequest request
  ) {
    IncomeInfo info = IncomeInfo.builder()
        .userId(getCurrentUserId())
        .source(request.source())
        .amount(request.amount())
        .build();

    Income income = incomeCreationUseCase.create(info);

    return ResponseEntity.ok(incomeMapper.toDto(income));
  }
}

