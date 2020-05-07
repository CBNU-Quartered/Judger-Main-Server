package com.qt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Builder
    public Student(@NotNull String universityCode, @NotNull Long studentId, @NotNull String name, @NotNull @Email String email, @NotNull String phoneNumber) {
        this.universityCode = universityCode;
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String universityCode;

    @NotNull
    @Column(unique = true)
    private Long studentId;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

}
