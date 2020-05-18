package com.qt.problem;

import com.qt.domain.contest.ContestProblemRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestProblemRegistrationRepository extends JpaRepository<ContestProblemRegistration, Long> {
}
