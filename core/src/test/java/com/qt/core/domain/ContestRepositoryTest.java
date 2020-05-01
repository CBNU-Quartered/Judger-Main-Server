package com.qt.core.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContestRepositoryTest {

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Spring Data JPA를 통한 테스트")
    @Rollback(false)
    public void name() {
        Student student1 = Student.builder()
                .name("s1")
                .studentId(11l)
                .email("e@e.e")
                .phoneNumber("123")
                .usniversityCode("cbnu")
                .build();

        Student student2 = Student.builder()
                .name("s2")
                .studentId(22l)
                .email("e@e.e")
                .phoneNumber("123")
                .usniversityCode("cbnu")
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);

        Map applicant = new HashMap<Student, Boolean>() {
            {
                put(student1, true);
                put(student2, false);
            }
        };

        Contest contest = Contest.builder()
                .name("a")
                .description("aaa")
                .activeTime(LocalDateTime.now())
                .inActiveTime(LocalDateTime.now())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .freezeTime(LocalDateTime.now())
                .unFreezeTime(LocalDateTime.now())
                .applicants(applicant)
                .build();

        contestRepository.save(contest);
        contestRepository.delete(contest);

        System.out.println(contest);
    }
}