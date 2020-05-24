package com.qt.domain.contest;

import com.qt.domain.user.User;
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

    public ContestApplication(@NotNull Contest contest, @NotNull User user, @NotNull LocalDateTime createTime, @NotNull Boolean isApproved) {
        this.contest = contest;
        this.user = user;
        this.createTime = createTime;
        this.isApproved = isApproved;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Contest contest;

    @NotNull
    @CreatedBy
    @ManyToOne
    private User user;

    @NotNull
    @CreatedDate
    private LocalDateTime createTime;

    @NotNull
    private Boolean isApproved = false;

}
