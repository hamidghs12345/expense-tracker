package com.snapppay.expensetracker.api.controller.tag;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.domain.usecase.tag.MaxCostModificationUseCase;
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
@Tag(name = "Max Cost")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/max-costs")
public class MaxCostModificationController extends AbstractController {

  private final MaxCostModificationUseCase maxCostModificationUseCase;

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<Boolean> delete(
      @PathVariable("id") UUID id
  ) {
    maxCostModificationUseCase.delete(id, getCurrentUserId());

    return ResponseEntity.ok(true);
  }

}
