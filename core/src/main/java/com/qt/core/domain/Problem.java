package com.qt.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Problem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Contest contest;
}
