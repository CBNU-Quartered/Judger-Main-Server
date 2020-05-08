package com.qt.contest.dto;

import com.qt.domain.Contest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ContestInfo {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private LocalDateTime activeTime;

    @NotNull
    private LocalDateTime inActiveTime;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    private LocalDateTime freezeTime;

    @NotNull
    private LocalDateTime unFreezeTime;

    @Builder
    public ContestInfo(@NotNull String name, String description, @NotNull LocalDateTime activeTime, @NotNull LocalDateTime inActiveTime, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime, @NotNull LocalDateTime freezeTime, @NotNull LocalDateTime unFreezeTime) {
        this.name = name;
        this.description = description;
        this.activeTime = activeTime;
        this.inActiveTime = inActiveTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.freezeTime = freezeTime;
        this.unFreezeTime = unFreezeTime;
    }

    public Contest toEntity() {
        return new Contest(name, description, activeTime, inActiveTime, startTime, endTime, freezeTime, unFreezeTime);
    }
}
