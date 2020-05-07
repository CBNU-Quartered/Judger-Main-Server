package com.qt.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositoryForTest extends JpaRepository<Student, Long> {
}
