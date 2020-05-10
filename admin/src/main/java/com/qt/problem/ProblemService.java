package com.qt.problem;

import com.qt.domain.Problem;
import com.qt.problem.dto.ProblemInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class ProblemService {

    private static final String LOCAL_PROBLEM_STORAGE = "/Users/hyogeon/IdeaProjects/judger-main-server/admin/src/main/resources/static/problems/";

    private final ProblemRepository problemRepository;
    private final ModelMapper modelMapper;

    public ProblemService(ProblemRepository problemRepository, ModelMapper modelMapper) {
        this.problemRepository = problemRepository;
        this.modelMapper = modelMapper;
    }

    public Long save(MultipartFile file) throws IOException {
        String identifier = saveFile(file);

        Problem problem = new Problem(file.getOriginalFilename(), identifier, 1d, 1d);
        return problemRepository.save(problem).getId();
    }

    @Transactional(readOnly = true)
    public ProblemInfo findById(Long id) {
        return problemRepository
                .findById(id)
                .map(problem -> modelMapper.map(problem, ProblemInfo.class))
                .orElseThrow(ProblemNotFoundException::new);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String identifier = UUID.randomUUID().toString();
        File dest = new File(LOCAL_PROBLEM_STORAGE + identifier);
        file.transferTo(dest);
        return identifier;
    }
}
