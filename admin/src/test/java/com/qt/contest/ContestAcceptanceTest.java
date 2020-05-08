package com.qt.contest;

import com.qt.AcceptanceTestUtils;
import com.qt.contest.dto.ContestInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

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
        ContestInfo contestInfo = ContestInfo.builder()
                .name("contest1")
                .description("easy contests")
                .activeTime(LocalDateTime.now())
                .inActiveTime(LocalDateTime.now())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .freezeTime(LocalDateTime.now())
                .unFreezeTime(LocalDateTime.now())
                .build();

        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/contests")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(contestInfo), ContestInfo.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().valueMatches("location", "/contests/[1-9]+[0-9]*");

        contestId = AcceptanceTestUtils.extractDomainIdFromCreatedResourceAddress(responseSpec);
    }

    @Test
    @DisplayName("콘테스트 조회 테스트")
    void getContest() {
        webTestClient.get()
                .uri("/contests/" + contestId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("contest1");
    }
}
