package com.qt.problem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProblemRequestInfo {
    @NonNull
    private Double timeLimit;

    @NonNull
    private Double memoryLimit;

    public ProblemRequestInfo(Double timeLimit, Double memoryLimit) {
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }
}
