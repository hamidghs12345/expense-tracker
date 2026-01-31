package com.snapppay.expensetracker.domain.service.tag;


import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.model.tag.MaxCostInfo;

public interface MaxCostCreationService {

  MaxCost create(MaxCostInfo maxCostInfo);
}
