package com.qt.problem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class ProblemInfo {
    @NotNull
    private String name;

    private Double timeLimit;

    private Double memoryLimit;

    @Builder
    public ProblemInfo(@NotNull String name, Double timeLimit, Double memoryLimit) {
        this.name = name;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }
}
