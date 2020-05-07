package com.qt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@NoArgsConstructor
public class Contest {

    @Builder
    public Contest(@NotNull String name, String description, @NotNull LocalDateTime activeTime, @NotNull LocalDateTime inActiveTime, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime, @NotNull LocalDateTime freezeTime, @NotNull LocalDateTime unFreezeTime, Map<Student, Boolean> applicants) {
        this.name = name;
        this.description = description;
        this.activeTime = activeTime;
        this.inActiveTime = inActiveTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.freezeTime = freezeTime;
        this.unFreezeTime = unFreezeTime;
        this.applicants = applicants;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @Lob
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

    @ElementCollection
    @CollectionTable(name = "APPLYING",
            joinColumns = {@JoinColumn(name = "CONTEST_ID")})
    @Column(name = "APPROVED")
    @MapKeyJoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Map<Student, Boolean> applicants;

}
