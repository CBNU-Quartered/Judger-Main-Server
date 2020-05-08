package com.qt.contest;

import com.qt.domain.Contest;
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
public class ContestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    @DisplayName("콘테스트 등록 테스트")
    void setUp() {
        Contest contest = Contest.builder()
                .name("contest")
                .description("easy contests")
                .activeTime(LocalDateTime.now())
                .inActiveTime(LocalDateTime.now())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .freezeTime(LocalDateTime.now())
                .unFreezeTime(LocalDateTime.now())
                .build();

        webTestClient.post()
                .uri("/contests")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(contest), Contest.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().valueMatches("Location", "/contests/1");
    }

    @Test
    @DisplayName("콘테스트 조회 테스트")
    void getContest() {
    }
}
