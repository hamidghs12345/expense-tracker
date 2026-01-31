package com.snapppay.expensetracker.api.controller.income;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.domain.usecase.income.IncomeModificationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Income")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/incomes")
public class IncomeModificationController extends AbstractController {

  private final IncomeModificationUseCase incomeModificationUseCase;

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<Boolean> delete(
      @PathVariable("id") UUID id
  ) {
    incomeModificationUseCase.delete(id, getCurrentUserId());

    return ResponseEntity.ok(true);
  }

}
