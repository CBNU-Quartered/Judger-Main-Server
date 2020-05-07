package com.qt.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class Problem {

    @Builder
    public Problem(@NotNull Contest contest, @NotNull String content, Double timeLimit, Double memoryLimit) {
        this.contest = contest;
        this.content = content;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Contest contest;

    @NotNull
    @Lob
    private String content;

    private Double timeLimit;

    private Double memoryLimit;
}
