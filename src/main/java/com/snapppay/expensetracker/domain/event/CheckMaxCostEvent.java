package com.snapppay.expensetracker.domain.event;


import java.util.UUID;
import lombok.Data;

@Data(staticConstructor = "of")
public class CheckMaxCostEvent {

  private final UUID exId;
}
