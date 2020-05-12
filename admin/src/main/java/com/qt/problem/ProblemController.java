package com.qt.problem;

import com.qt.problem.dto.FileInfo;
import com.qt.problem.dto.ProblemRequestInfo;
import com.qt.problem.dto.ProblemResponseInfo;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public ResponseEntity uploadFile(@ModelAttribute ProblemRequestInfo problemRequestInfo, @RequestParam MultipartFile file) throws IOException {
        Long problemId = problemService.save(problemRequestInfo, file);
        return ResponseEntity.created(URI.create("/problems/" + problemId)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemResponseInfo> showProblem(@PathVariable Long id) {
        ProblemResponseInfo problemResponseInfo = problemService.findById(id);
        return ResponseEntity.ok(problemResponseInfo);
    }

    @GetMapping("/{id}/files")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {
        FileInfo fileInfo = problemService.findFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .header(HttpHeaders.CONTENT_LENGTH, fileInfo.getContentLength())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .body(fileInfo.getResource());
    }
}
