package com.qt.problem;

import com.qt.domain.problem.Problem;
import com.qt.domain.problem.dto.FileInfo;
import com.qt.domain.problem.dto.ProblemInfo;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProblemService {
    private static final String LOCAL_PROBLEM_STORAGE_PATH = "/Users/hyogeon/IdeaProjects/judger-main-server/admin/src/main/resources/static/problems/";

    private static final String FILE_PATH = "file:";
    private static final String TEST_CASE_PATH = "/tc";
    private static final String TEST_CASE_INPUT_PATH = "/in";
    private static final String TEST_CASE_OUTPUT_PATH = "/out";

    private final ProblemRepository problemRepository;
    private final ModelMapper modelMapper;
    private final ResourceLoader resourceLoader;

    public ProblemService(ProblemRepository problemRepository, ModelMapper modelMapper, ResourceLoader resourceLoader) {
        this.problemRepository = problemRepository;
        this.modelMapper = modelMapper;
        this.resourceLoader = resourceLoader;
    }

    public List<ProblemInfo> findAll() {
        return problemRepository.findAll().stream()
                .map(problem -> modelMapper.map(problem, ProblemInfo.class))
                .collect(Collectors.toList());
    }

    public Long save(ProblemInfo problemInfo, MultipartFile file) throws IOException {
        String identifier = saveProblemFile(file);

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
        Resource resource = resourceLoader.getResource(FILE_PATH + LOCAL_PROBLEM_STORAGE_PATH + identifier + "/" + problem.getName());

        return FileInfo.builder()
                .contentDisposition(problem.getName())
                .contentLength(String.valueOf(resource.getFile().length()))
                .resource(resource)
                .build();
    }

    public Long updateProblem(Long id, ProblemInfo problemInfo, MultipartFile file) throws IOException {
        Problem problem = deleteProblemFile(id);

        String identifier = saveProblemFile(file);
        return problem.updateTo(identifier, problemInfo);
    }

    public void deleteProblem(Long id) throws IOException {
        Problem problem = deleteProblemFile(id);
        problemRepository.delete(problem);
    }

    public void registerTestcase(Long id, List<MultipartFile> in, List<MultipartFile> out) throws IOException {
        String identifier = problemRepository.findById(id).orElseThrow(NotFoundProblemException::new).getIdentifier();
        String testcaseDirectory = LOCAL_PROBLEM_STORAGE_PATH + identifier + TEST_CASE_PATH;
        new File(testcaseDirectory).mkdir();

        String inputDirectory = testcaseDirectory + TEST_CASE_INPUT_PATH;
        String outputDirectory = testcaseDirectory + TEST_CASE_OUTPUT_PATH;
        new File(inputDirectory).mkdir();
        new File(outputDirectory).mkdir();

        for (MultipartFile input : in) {
            input.transferTo(new File(inputDirectory + "/" + input.getOriginalFilename()));
        }

        for (MultipartFile output : out) {
            output.transferTo(new File(outputDirectory + "/" + output.getOriginalFilename()));
        }
    }

    private String saveProblemFile(MultipartFile file) throws IOException {
        String identifier = UUID.randomUUID().toString();
        String directory = LOCAL_PROBLEM_STORAGE_PATH + identifier;
        new File(directory).mkdir();

        File dest = new File(directory + "/" + file.getOriginalFilename());
        file.transferTo(dest);
        return identifier;
    }

    private Problem deleteProblemFile(Long id) throws IOException {
        Problem problem = problemRepository.findById(id).orElseThrow(NotFoundProblemException::new);
        Resource resource = resourceLoader.getResource(FILE_PATH + LOCAL_PROBLEM_STORAGE_PATH + problem.getIdentifier());
        resource.getFile().delete();
        return problem;
    }
}
