package com.qt.problem;

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

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProblemControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    @DisplayName("문제 pdf 파일 업로드 테스트")
    void setUp() throws IOException {
        ByteArrayResource file = new ByteArrayResource(new byte[]{1, 2, 3}) {
            @Override
            public String getFilename() {
                return "test.pdf";
            }
        };

        webTestClient.post()
                .uri("/problems")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().valueMatches("Location", "/problems/1");
    }

    @Test
    @DisplayName("문제 pdf 파일 조회 테스트")
    void findAllFiles() {
        webTestClient.get()
                .uri("/problem/1")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
