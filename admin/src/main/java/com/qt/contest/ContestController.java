package com.qt.contest;

import com.qt.domain.contest.dto.ContestInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/contests")
public class ContestController {

    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
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
}
