package com.qt.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class Testcase {

    @Builder
    public Testcase(String input, String output, Problem problem) {
        this.input = input;
        this.output = output;
        this.problem = problem;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String input;

    private String output;

    @ManyToOne
    private Problem problem;
}
