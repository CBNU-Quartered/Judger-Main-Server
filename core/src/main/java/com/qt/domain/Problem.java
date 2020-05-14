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
    @Column(unique = true)
    private String identifier;

    @ManyToOne
    private Contest contest;

    @NotNull
    private Double timeLimit;

    @NotNull
    private Double memoryLimit;

    @CreatedDate
    private LocalDateTime createTime;

    public Problem(@NotNull String name, @NotNull String identifier, @NotNull Double timeLimit, @NotNull Double memoryLimit) {
        this.name = name;
        this.identifier = identifier;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

    public Long updateTo(@NotNull String name, @NotNull String identifier, @NotNull Double timeLimit, @NotNull Double memoryLimit) {
        this.name = name;
        this.identifier = identifier;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        return id;
    }
}
