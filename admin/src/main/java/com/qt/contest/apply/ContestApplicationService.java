package com.qt.contest.apply;

import com.qt.contest.ContestRepository;
import com.qt.contest.NotFoundContestException;
import com.qt.contest.regist.ContestProblemRegistrationRepository;
import com.qt.domain.contest.Contest;
import com.qt.domain.contest.ContestApplication;
import com.qt.domain.user.User;
import com.qt.user.NotFoundUserException;
import com.qt.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContestApplicationService {

    private final ContestRepository contestRepository;
    private final UserRepository userRepository;
    private final ContestApplicationRepository contestApplicationRepository;

    public ContestApplicationService(ContestRepository contestRepository, UserRepository userRepository, ContestApplicationRepository contestApplicationRepository) {
        this.contestRepository = contestRepository;
        this.userRepository = userRepository;
        this.contestApplicationRepository = contestApplicationRepository;
    }

    public Long apply(Long contestId, Long userId) {
        Contest contest = contestRepository.findById(contestId).orElseThrow(NotFoundContestException::new);
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        return contestApplicationRepository.save(new ContestApplication(contest, user)).getId();
    }
}
