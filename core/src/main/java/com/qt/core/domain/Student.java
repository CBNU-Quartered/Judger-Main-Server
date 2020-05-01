package com.qt.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Builder
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String usniversityCode;

    @NotNull
    private Long studentId;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

}
