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

    @GetMapping("/{id}")
    public ResponseEntity<Problem> show(@PathVariable Long id) {
//        Problem problem = problemService.findById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity upload(@RequestParam MultipartFile file) throws IOException {
        Problem problem = problemService.save(file);
        return ResponseEntity.created(URI.create("/problems/" + 1l)).build();
    }
}
