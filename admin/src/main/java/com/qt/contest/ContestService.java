package com.qt.contest;

import com.qt.contest.dto.ContestInfo;
import com.qt.domain.Contest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContestService {

    private final ContestRepository contestRepository;
    private final ModelMapper modelMapper;

    public ContestService(ContestRepository contestRepository, ModelMapper modelMapper) {
        this.contestRepository = contestRepository;
        this.modelMapper = modelMapper;
    }

    public Long save(ContestInfo contestInfo) {
        Contest contest = contestInfo.toEntity();
        return contestRepository.save(contest).getId();
    }

    @Transactional(readOnly = true)
    public ContestInfo findById(Long id) {
        Contest contest = contestRepository.findById(id).orElseThrow(NotFoundContestException::new);
        return modelMapper.map(contest, ContestInfo.class);
    }
}
