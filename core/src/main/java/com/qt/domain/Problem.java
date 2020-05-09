package com.qt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Problem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Contest contest;

    @NotNull
    @Lob
    private String content;

    private Double timeLimit;

    private Double memoryLimit;

    @CreatedDate
    private LocalDateTime createTime;

    public Problem(@NotNull String name, @NotNull Contest contest, @NotNull String content, Double timeLimit, Double memoryLimit, LocalDateTime createTime) {
        this.name = name;
        this.contest = contest;
        this.content = content;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.createTime = createTime;
    }
}
