package com.snapppay.expensetracker.api.controller.tag;

import com.snapppay.expensetracker.api.controller.AbstractController;
import com.snapppay.expensetracker.api.dto.tag.MaxCostCreationRequest;
import com.snapppay.expensetracker.api.dto.tag.MaxCostDto;
import com.snapppay.expensetracker.api.dto.tag.mapper.MaxCostMapper;
import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import com.snapppay.expensetracker.domain.usecase.tag.MaxCostCreationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Max Cost")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/max-costs")
public class MaxCostCreationController extends AbstractController {

  private final MaxCostCreationUseCase maxCostCreationUseCase;
  private final MaxCostMapper maxCostMapper;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER')")
  public ResponseEntity<List<MaxCostDto>> create(
      @RequestBody MaxCostCreationRequest request
  ) {

    List<MaxCost> MaxCosts = maxCostCreationUseCase.create(
        request.tagId(), request.maxCostReqs());

    return ResponseEntity.ok(maxCostMapper.toDtos(MaxCosts));
  }

}
