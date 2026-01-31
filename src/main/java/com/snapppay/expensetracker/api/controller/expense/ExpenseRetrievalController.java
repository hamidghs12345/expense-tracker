package com.snapppay.expensetracker.api.controller.expense;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.expense.ExpenseDto;
import com.snapppay.expensetracker.api.dto.expense.mapper.ExpenseMapper;
import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseFilter;
import com.snapppay.expensetracker.domain.usecase.expense.ExpenseRetrievalUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Expense")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/expenses")
public class ExpenseRetrievalController extends AbstractController {

  private final ExpenseRetrievalUseCase expenseRetrievalUseCase;
  private final ExpenseMapper expenseMapper;

  @GetMapping("/tags/{tag-id}")
  @PreAuthorize("hasAnyAuthority('USER')")
  public Page<ExpenseDto> getExpenses(
      @PathVariable("tag-id") UUID tagId,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdFromDate,
      @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdToDate) {

    ZonedDateTime fromZonedDateTime = null;
    ZonedDateTime toZonedDateTime = null;

    if (createdFromDate != null) {
      fromZonedDateTime = createdFromDate.atStartOfDay().atZone(ZoneId.of("Z"));
    }

    if (createdToDate != null) {
      toZonedDateTime = createdToDate.atTime(LocalTime.MAX).atZone(ZoneId.of("Z"));
    }

    ExpenseFilter expenseFilter = ExpenseFilter.builder()
        .tagId(tagId)
        .fromDate(fromZonedDateTime)
        .toDate(toZonedDateTime)
        .page(page)
        .size(size)
        .build();

    Page<Expense> exs = expenseRetrievalUseCase.getAll(
        expenseFilter);

    return exs.map(expenseMapper::toDto);
  }

}
