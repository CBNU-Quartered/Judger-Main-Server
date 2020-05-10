package com.qt.problem;

import com.qt.domain.Problem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/problems")
public class ProblemController {


    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam MultipartFile file) throws IOException {
        Long problemId = problemService.save(file);
        return ResponseEntity.created(URI.create("/problems/" + problemId)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Problem> showProblem(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
