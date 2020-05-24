package com.qt.contest.apply;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/contests")
public class ContestApplicationController {

    private final ContestApplicationService contestApplicationService;

    public ContestApplicationController(ContestApplicationService contestApplicationService) {
        this.contestApplicationService = contestApplicationService;
    }

    @PostMapping("/{contestId}/apply/{userId}")
    public ResponseEntity applyContest(@PathVariable Long contestId, @PathVariable Long userId) {
        contestApplicationService.apply(contestId, userId);
        return ResponseEntity.created(URI.create("/contests")).build();
    }
}
