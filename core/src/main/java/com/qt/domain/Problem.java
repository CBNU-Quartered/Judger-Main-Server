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

    @ManyToOne
    private Contest contest;

    private Double timeLimit;

    private Double memoryLimit;

    @CreatedDate
    private LocalDateTime createTime;

    public Problem(@NotNull String name, Contest contest, Double timeLimit, Double memoryLimit) {
        this.name = name;
        this.contest = contest;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }
}
