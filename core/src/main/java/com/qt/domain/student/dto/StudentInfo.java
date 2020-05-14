package com.qt.domain.student.dto;

import com.qt.domain.student.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class StudentInfo {

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

    @Builder
    public StudentInfo(@NotNull String universityCode, @NotNull Long studentId, @NotNull String name, @NotNull @Email String email, @NotNull String phoneNumber) {
        this.universityCode = universityCode;
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Student toEntity() {
        return new Student(universityCode, studentId, name, email, phoneNumber);
    }
}
