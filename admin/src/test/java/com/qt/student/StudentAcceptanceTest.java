package com.qt.student;

import com.qt.student.StudentRepository;
import com.qt.domain.Student;
import com.qt.student.dto.StudentInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    @DisplayName("모든 학생 조회")
    void findAllStudents() {
        StudentInfo studentInfo1 = StudentInfo.builder()
                .studentId(2014l)
                .name("s1")
                .email("a@b.c")
                .phoneNumber("1234")
                .universityCode("cbnu")
                .build();

        StudentInfo studentInfo2 = StudentInfo.builder()
                .studentId(2015l)
                .name("s2")
                .email("a@b.c")
                .phoneNumber("1234")
                .universityCode("cbnu")
                .build();

        studentRepository.save(studentInfo1.toEntity());
        studentRepository.save(studentInfo2.toEntity());

        webTestClient.get()
                .uri("/students")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.length()")
                .isEqualTo(2);
    }
}