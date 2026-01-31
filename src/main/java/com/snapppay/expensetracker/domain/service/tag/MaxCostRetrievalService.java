package com.snapppay.expensetracker.domain.service.tag;


import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import java.util.List;
import java.util.UUID;

public interface MaxCostRetrievalService {

  List<MaxCost> getAll(UUID id);

}
