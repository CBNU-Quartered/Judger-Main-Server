package com.qt.domain.question.dto;

import com.qt.domain.contest.Contest;
import com.qt.domain.question.Question;
import com.qt.domain.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class QuestionInfo {

    @NotNull
    private Contest contest;

    @CreatedBy
    private Student student;

    @NotNull
    private Integer problemNumber;

    @NotNull
    private String content;

    private String response;

    @CreatedDate
    private LocalDateTime createTime;

    public QuestionInfo(@NotNull Contest contest, @NotNull Student student, @NotNull Integer problemNumber, @NotNull String content, String response, LocalDateTime createTime) {
        this.contest = contest;
        this.student = student;
        this.problemNumber = problemNumber;
        this.content = content;
        this.response = response;
        this.createTime = createTime;
    }

    public Question toEntity() {
        return new Question(contest, student, problemNumber, content, response, createTime);
    }
}
