package com.qt.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Builder
public class Contest {

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
    private Map<Student, Boolean> applicants = new HashMap<>();

}
