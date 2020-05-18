package com.qt.contest;

import com.qt.domain.contest.Contest;
import com.qt.domain.contest.ContestProblemRegistration;
import com.qt.problem.ContestProblemRegistrationRepository;
import com.qt.problem.NotFoundProblemException;
import com.qt.problem.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestProblemRegistrationService {

    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;
    private final ContestProblemRegistrationRepository contestProblemRegistrationRepository;

    public ContestProblemRegistrationService(ContestRepository contestRepository, ProblemRepository problemRepository, ContestProblemRegistrationRepository contestProblemRegistrationRepository) {
        this.contestRepository = contestRepository;
        this.problemRepository = problemRepository;
        this.contestProblemRegistrationRepository = contestProblemRegistrationRepository;
    }

    public void register(Long contestId, List<Long> problemIds) {
        Contest contest = contestRepository.findById(contestId).orElseThrow(NotFoundContestException::new);

        problemIds.stream()
                .map(id -> problemRepository.findById(id).orElseThrow(NotFoundProblemException::new))
                .forEach(problem -> contestProblemRegistrationRepository.save(new ContestProblemRegistration(contest, problem)));
    }
}
