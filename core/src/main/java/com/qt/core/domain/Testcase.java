package com.qt.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Testcase {

    @Id
    @GeneratedValue
    private Long id;

    private String input;

    private String output;

    @ManyToOne
    private Problem problem;
}
