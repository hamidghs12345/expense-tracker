package com.snapppay.expensetracker.api.controller.expense;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.domain.usecase.expense.ExpenseModificationUseCase;
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
@Tag(name = "Expense")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/expenses")
public class ExpenseModificationController extends AbstractController {

  private final ExpenseModificationUseCase expenseModificationUseCase;

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<Boolean> delete(
      @PathVariable("id") UUID id
  ) {
    expenseModificationUseCase.delete(id, getCurrentUserId());

    return ResponseEntity.ok(true);
  }

}
