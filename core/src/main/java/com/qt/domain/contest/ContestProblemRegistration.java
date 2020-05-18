package com.qt.domain.contest;

import com.qt.domain.problem.Problem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ContestProblemRegistration {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private final Contest contest;

    @ManyToOne
    private final Problem problem;

    public ContestProblemRegistration(Contest contest, Problem problem) {
        this.contest = contest;
        this.problem = problem;
    }
}
