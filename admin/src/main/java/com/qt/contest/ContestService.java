package com.qt.contest;

import com.qt.domain.Contest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContestService {

    private final ContestRepository contestRepository;

    public ContestService(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public Contest save(Contest contest) {
        return contestRepository.save(contest);
    }
}
