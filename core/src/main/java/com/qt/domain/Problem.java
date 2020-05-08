package com.qt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Problem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Contest contest;

    @NotNull
    @Lob
    private String content;

    private Double timeLimit;

    private Double memoryLimit;

    public Problem(@NotNull Contest contest, @NotNull String content, Double timeLimit, Double memoryLimit) {
        this.contest = contest;
        this.content = content;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }
}
