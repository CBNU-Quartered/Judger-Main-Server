package com.qt.domain;

import lombok.Builder;

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
    private String universityCode;

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
