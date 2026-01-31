package com.snapppay.expensetracker.api.controller.income;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.income.IncomeDto;
import com.snapppay.expensetracker.api.dto.income.mapper.IncomeMapper;
import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeFilter;
import com.snapppay.expensetracker.domain.usecase.income.IncomeRetrievalUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Income")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/incomes")
public class IncomeRetrievalController extends AbstractController {

  private final IncomeRetrievalUseCase incomeRetrievalUseCase;
  private final IncomeMapper incomeMapper;

  @GetMapping("/me")
  @PreAuthorize("hasAnyAuthority('USER')")
  public Page<IncomeDto> getIncomes(
      @RequestParam(required = false) String source,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdFromDate,
      @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdToDate
  ) {
    ZonedDateTime fromZonedDateTime = null;
    ZonedDateTime toZonedDateTime = null;

    if (createdFromDate != null) {
      fromZonedDateTime = createdFromDate.atStartOfDay().atZone(ZoneId.of("Z"));
    }

    if (createdToDate != null) {
      toZonedDateTime = createdToDate.atTime(LocalTime.MAX).atZone(ZoneId.of("Z"));
    }

    IncomeFilter incomeFilter = IncomeFilter.builder()
        .userId(getCurrentUserId())
        .source(source)
        .fromDate(fromZonedDateTime)
        .toDate(toZonedDateTime)
        .page(page)
        .size(size)
        .build();

    Page<Income> incomes = incomeRetrievalUseCase.getAll(incomeFilter);

    return incomes.map(incomeMapper::toDto);
  }

}
