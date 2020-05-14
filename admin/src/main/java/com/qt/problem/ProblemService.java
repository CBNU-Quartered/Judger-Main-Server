package com.qt.problem;

import com.qt.domain.Problem;
import com.qt.problem.dto.FileInfo;
import com.qt.problem.dto.ProblemInfo;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
    private static final String FILE_PATH = "file:";

    private final ProblemRepository problemRepository;
    private final ModelMapper modelMapper;
    private final ResourceLoader resourceLoader;

    public ProblemService(ProblemRepository problemRepository, ModelMapper modelMapper, ResourceLoader resourceLoader) {
        this.problemRepository = problemRepository;
        this.modelMapper = modelMapper;
        this.resourceLoader = resourceLoader;
    }

    public Long save(ProblemInfo problemInfo, MultipartFile file) throws IOException {
        String identifier = saveFile(file);

        Problem problem = new Problem(problemInfo.getName(), identifier, problemInfo.getTimeLimit(), problemInfo.getMemoryLimit());
        return problemRepository.save(problem).getId();
    }

    @Transactional(readOnly = true)
    public ProblemInfo findById(Long id) {
        return problemRepository
                .findById(id)
                .map(problem -> modelMapper.map(problem, ProblemInfo.class))
                .orElseThrow(NotFoundProblemException::new);
    }

    @Transactional(readOnly = true)
    public FileInfo findFile(Long id) throws IOException {
        Problem problem = problemRepository.findById(id).orElseThrow(NotFoundProblemException::new);
        String identifier = problem.getIdentifier();
        Resource resource = resourceLoader.getResource(FILE_PATH + LOCAL_PROBLEM_STORAGE + identifier);

        return FileInfo.builder()
                .contentDisposition(problem.getName())
                .contentLength(String.valueOf(resource.getFile().length()))
                .resource(resource)
                .build();
    }

    public Long updateProblem(Long id, ProblemInfo problemInfo, MultipartFile file) throws IOException {
        Problem problem = deleteFile(id);

        String identifier = saveFile(file);
        return problem.updateTo(problemInfo.getName(), identifier, problem.getTimeLimit(), problemInfo.getMemoryLimit());
    }

    public void deleteProblem(Long id) throws IOException {
        Problem problem = deleteFile(id);
        problemRepository.delete(problem);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String identifier = UUID.randomUUID().toString();

        File dest = new File(LOCAL_PROBLEM_STORAGE + identifier);
        file.transferTo(dest);
        return identifier;
    }

    private Problem deleteFile(Long id) throws IOException {
        Problem problem = problemRepository.findById(id).orElseThrow(NotFoundProblemException::new);
        Resource resource = resourceLoader.getResource(FILE_PATH + LOCAL_PROBLEM_STORAGE + problem.getIdentifier());
        resource.getFile().delete();
        return problem;
    }
}
