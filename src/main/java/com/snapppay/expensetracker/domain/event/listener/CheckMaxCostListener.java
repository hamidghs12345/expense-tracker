package com.snapppay.expensetracker.domain.event.listener;

import com.snapppay.expensetracker.domain.event.CheckMaxCostEvent;
import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.service.expense.ExpenseRetrievalService;
import com.snapppay.expensetracker.domain.service.tag.MaxCostCalculationService;
import com.snapppay.expensetracker.domain.service.tag.MaxCostRetrievalService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckMaxCostListener {

  private final ExpenseRetrievalService expenseRetrievalService;
  private final MaxCostRetrievalService maxCostRetrievalService;
  private final MaxCostCalculationService maxCostCalculationService;

  @Async
  @EventListener(CheckMaxCostEvent.class)
  public void userCreditLeverage(CheckMaxCostEvent event) {

    Expense ex = expenseRetrievalService.get(
        event.getExId());

    List<MaxCost> maxCosts = maxCostRetrievalService.getAll(
        ex.getTagId());

    if (maxCosts.isEmpty()) {
      return;
    }

    for (MaxCost mc : maxCosts) {

      BigDecimal total = maxCostCalculationService
          .calculateTotalForPeriod(ex.getTagId(), mc.getPeriod());

      if (total.compareTo(mc.getAmount()) > 0) {

        // TODO: Notify the user that their
        //  spending has exceeded the max allowed
       }
    }
  }
}
