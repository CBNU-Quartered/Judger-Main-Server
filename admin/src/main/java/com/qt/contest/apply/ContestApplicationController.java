package com.qt.contest.apply;

import com.qt.domain.contest.ContestApplication;
import com.qt.domain.contest.dto.ContestInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{contestId}/apply")
    public ResponseEntity<List<ContestApplicationInfo>> showContestApplications(@PathVariable Long contestId) {
        List<ContestApplicationInfo> contestApplicationInfos = contestApplicationService.findAllByContestId(contestId);
        return ResponseEntity.ok(contestApplicationInfos);
    }
}
