package com.qt.question;

import com.qt.AcceptanceTestUtils;
import com.qt.domain.contest.dto.ContestInfo;
import com.qt.domain.student.dto.StudentInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    private String contestId;

    @BeforeEach
    @DisplayName("콘테스트 질문 등록 테스트")
    void createContest() {

        StudentInfo studentInfo = StudentInfo.builder()
                .studentId(2014l)
                .name("s1")
                .email("a@b.c")
                .phoneNumber("1234")
                .universityCode("cbnu")
                .build();

        ContestInfo contestInfo = ContestInfo.builder()
                .name("contest 1")
                .description("easy contest")
                .activeTime(LocalDateTime.now())
                .inActiveTime(LocalDateTime.now())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .freezeTime(LocalDateTime.now())
                .unFreezeTime(LocalDateTime.now())
                .build();

        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/questions")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromFormData("contest", String.valueOf(1L))
                        .with("student", String.valueOf(1L))
                        .with("activeTime", String.valueOf(LocalDateTime.now()))
                        .with("problemNumber", String.valueOf(1L))
                        .with("content", "test")
                        .with("response", " ")
                        .with("createTime", String.valueOf(LocalDateTime.now())))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().valueMatches("location", "/questions/[1-9]+[0-9]*");

        contestId = AcceptanceTestUtils.extractDomainIdFromCreatedResourceAddress(responseSpec);
    }

    @Test
    @DisplayName("질문 조회 테스트")
    void showQuestion() {
        webTestClient.get()
                .uri("/questions/" + contestId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.content").isEqualTo("질문이 있어요");
    }
}