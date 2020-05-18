package com.qt.contest;

import com.qt.domain.contest.dto.ContestInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contests")
public class ContestController {

    private final ContestService contestService;
    private final ContestProblemRegistrationService contestProblemRegistrationService;

    public ContestController(ContestService contestService, ContestProblemRegistrationService contestProblemRegistrationService) {
        this.contestService = contestService;
        this.contestProblemRegistrationService = contestProblemRegistrationService;
    }

    @PostMapping
    public ResponseEntity createContest(@ModelAttribute ContestInfo contestInfo) {
        Long id = contestService.save(contestInfo);
        return ResponseEntity.created(URI.create("/contests/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContestInfo> showContest(@PathVariable Long id) {
        ContestInfo contestInfo = contestService.findById(id);
        return ResponseEntity.ok(contestInfo);
    }

    @PostMapping("/{id}")
    public ResponseEntity updateContest(@PathVariable Long id, @ModelAttribute ContestInfo contestInfo) {
        contestService.updateContest(id, contestInfo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContest(@PathVariable Long id) {
        contestService.deleteContest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{contestId}/problems")
    public ResponseEntity addProblems(@PathVariable Long contestId, @RequestParam List<Long> problemIds) {
        contestProblemRegistrationService.register(contestId, problemIds);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity eventErrorHandler(NotFoundContestException exception) {
        return ResponseEntity.notFound().build();
    }
}
