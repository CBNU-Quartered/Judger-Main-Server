package com.qt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Problem {

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
