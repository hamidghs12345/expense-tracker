package com.snapppay.expensetracker.api.controller.expense;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.expense.ExpenseCreationRequest;
import com.snapppay.expensetracker.api.dto.expense.ExpenseDto;
import com.snapppay.expensetracker.api.dto.expense.mapper.ExpenseMapper;
import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseInfo;
import com.snapppay.expensetracker.domain.usecase.expense.ExpenseCreationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Expense")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/expenses")
public class ExpenseCreationController extends AbstractController {

  private final ExpenseCreationUseCase expenseCreationUseCase;
  private final ExpenseMapper expenseMapper;


  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<ExpenseDto> create(
      @RequestBody ExpenseCreationRequest request
  ) {
    ExpenseInfo expenseInfo = ExpenseInfo.builder()
        .tagId(request.tagId())
        .amount(request.amount())
        .build();

    Expense ex = expenseCreationUseCase.create(expenseInfo);

    return ResponseEntity.ok(expenseMapper.toDto(ex));
  }

}
