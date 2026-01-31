package com.snapppay.expensetracker.api.controller.tag;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.tag.MaxCostDto;
import com.snapppay.expensetracker.api.dto.tag.mapper.MaxCostMapper;
import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.usecase.tag.MaxCostRetrievalUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Max Cost")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/max-costs")
public class MaxCostRetrievalController extends AbstractController {

  private final MaxCostRetrievalUseCase maxCostRetrievalUseCase;
  private final MaxCostMapper maxCostMapper;

  @GetMapping("/tags/{tag-id}")
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<List<MaxCostDto>> getMaxCosts(
      @PathVariable("tag-id") UUID tagId) {

    List<MaxCost> maxCosts = maxCostRetrievalUseCase.getAll(
        tagId);

    return ResponseEntity.ok(maxCostMapper.toDtos(maxCosts));
  }

}
