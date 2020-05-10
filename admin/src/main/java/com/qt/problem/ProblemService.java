package com.qt.problem;

import com.qt.domain.Problem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class ProblemService {

    private static final String LOCAL_PROBLEM_STORAGE = "/Users/hyogeon/IdeaProjects/judger-main-server/admin/src/main/resources/static/";
    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Long save(MultipartFile file) throws IOException {
        String identifier = saveFile(file);

        Problem problem = new Problem(file.getOriginalFilename(), identifier, 1d, 1d);
        return problemRepository.save(problem).getId();
    }

    @Transactional(readOnly = true)
    public Problem findById(Long id) {
        return problemRepository
                .findById(id)
                .orElseThrow(ProblemNotFoundException::new);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String identifier = UUID.randomUUID().toString();
        File dest = new File(LOCAL_PROBLEM_STORAGE + identifier);
        file.transferTo(dest);
        return identifier;
    }
}
