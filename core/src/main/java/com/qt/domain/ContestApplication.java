package com.qt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ContestApplication {

    @Builder
    public ContestApplication(@NotNull Contest contest, @NotNull Student student, @NotNull LocalDateTime createTime, @NotNull Boolean isApproved) {
        this.contest = contest;
        this.student = student;
        this.createTime = createTime;
        this.isApproved = isApproved;
    }

    @Id @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Contest contest;

    @NotNull
    @CreatedBy
    @ManyToOne
    private Student student;

    @NotNull
    @CreatedDate
    private LocalDateTime createTime;

    @NotNull
    private Boolean isApproved = false;

}
