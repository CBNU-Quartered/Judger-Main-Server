package com.qt.problem;

import com.qt.domain.Problem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Transactional
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Transactional(readOnly = true)
    public Problem findById(Long id) {
        return problemRepository
                .findById(id)
                .orElseThrow(ProblemNotFoundException::new);
    }

    public Problem save(MultipartFile file) throws IOException {
        File dest = new File("/Users/hyogeon/IdeaProjects/judger-main-server/admin/src/main/resources/static/" + file.getOriginalFilename());
        file.transferTo(dest);
        return null;
    }
}
