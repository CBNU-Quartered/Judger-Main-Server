package com.qt.contest;

import com.qt.AcceptanceTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContestAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    private String contestId;

    @BeforeEach
    @DisplayName("콘테스트 등록 테스트")
    void setUp() {
        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/contests")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromFormData("name", "contest1")
                        .with("description", "easy contests")
                        .with("activeTime", String.valueOf(LocalDateTime.now()))
                        .with("inActiveTime", String.valueOf(LocalDateTime.now()))
                        .with("startTime", String.valueOf(LocalDateTime.now()))
                        .with("endTime", String.valueOf(LocalDateTime.now()))
                        .with("freezeTime", String.valueOf(LocalDateTime.now()))
                        .with("unFreezeTime", String.valueOf(LocalDateTime.now())))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().valueMatches("location", "/contests/[1-9]+[0-9]*");

        contestId = AcceptanceTestUtils.extractDomainIdFromCreatedResourceAddress(responseSpec);
    }

    @Test
    @DisplayName("콘테스트 조회 테스트")
    void showContest() {
        webTestClient.get()
                .uri("/contests/" + contestId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("contest1");
    }
}
