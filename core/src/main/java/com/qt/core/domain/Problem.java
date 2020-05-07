package com.qt.core.domain;

import org.synchronoss.cloud.nio.multipart.Multipart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private Multipart problemPdf;

    private Double timeLimit;

    private Double memoryLimit;
}
