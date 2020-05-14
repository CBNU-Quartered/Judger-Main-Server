package com.qt.domain.question;

import com.qt.domain.student.Student;
import com.qt.domain.contest.Contest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Contest contest;

    @CreatedBy
    @ManyToOne
    private Student student;

    @NotNull
    private Integer problemNumber;

    @NotNull
    @Lob
    private String content;

    @Lob
    private String response;

    @CreatedDate
    private LocalDateTime createTime;

    public Question(@NotNull Contest contest, Student student, @NotNull Integer problemNumber, @NotNull String content, String response, LocalDateTime createTime) {
        this.contest = contest;
        this.student = student;
        this.problemNumber = problemNumber;
        this.content = content;
        this.response = response;
        this.createTime = createTime;
    }
}
