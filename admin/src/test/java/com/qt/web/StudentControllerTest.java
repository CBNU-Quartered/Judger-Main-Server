package com.qt.web;

import com.qt.student.StudentRepository;
import com.qt.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    @DisplayName("모든 학생 조회")
    void findAllStudents() {
        Student student1 = Student.builder()
                .studentId(2014l)
                .name("s1")
                .email("a@b.c")
                .phoneNumber("1234")
                .universityCode("cbnu")
                .build();

        Student student2 = Student.builder()
                .studentId(2015l)
                .name("s2")
                .email("a@b.c")
                .phoneNumber("1234")
                .universityCode("cbnu")
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);

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