package com.qt.problem.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class ProblemResponseInfo {

    @NotNull
    private String name;

    @NonNull
    private Double timeLimit;

    @NonNull
    private Double memoryLimit;

    @Builder
    public ProblemResponseInfo(@NotNull String name, Double timeLimit, Double memoryLimit) {
        this.name = name;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }
}
