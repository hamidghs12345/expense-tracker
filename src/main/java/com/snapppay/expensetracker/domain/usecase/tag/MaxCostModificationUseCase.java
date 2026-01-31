package com.snapppay.expensetracker.domain.usecase.tag;

import com.snapppay.expensetracker.domain.error.Error.NotFoundException;
import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.service.UserRetrievalService;
import com.snapppay.expensetracker.domain.service.tag.MaxCostModificationService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaxCostModificationUseCase {

  private final UserRetrievalService userRetrievalService;
  private final MaxCostModificationService maxCostModificationService;

  public void delete(UUID id, Long userId) {

    User user = userRetrievalService.findUserById(userId);

    if (user == null) {
      throw new NotFoundException();
    }

    maxCostModificationService.delete(id);
  }
}
