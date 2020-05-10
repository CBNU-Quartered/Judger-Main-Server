package com.qt.problem;

import com.qt.AcceptanceTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProblemAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    private String problemId;

    @BeforeEach
    @DisplayName("문제 저장 테스트")
    void setUp() {
        ByteArrayResource file = new ByteArrayResource(new byte[]{1, 2, 3}) {
            @Override
            public String getFilename() {
                return "test.pdf";
            }
        };

        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/problems")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().valueMatches("Location", "/problems/[1-9]+[0-9]*");

        problemId = AcceptanceTestUtils.extractDomainIdFromCreatedResourceAddress(responseSpec);
    }

    @Test
    @DisplayName("문제 정보 조회 테스트")
    void findAllFiles() {
        webTestClient.get()
                .uri("/problems/" + problemId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
    }
}
