package com.qt.contest.apply;

import com.qt.domain.contest.ContestApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestApplicationRepository extends JpaRepository<ContestApplication, Long> {
}
