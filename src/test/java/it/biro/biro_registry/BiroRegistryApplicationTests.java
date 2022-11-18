package it.biro.biro_registry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
        ids = "it.biro:biro_log:+:8081",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@AutoConfigureJsonTesters
class BiroRegistryApplicationTests {

    WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void before() {
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Test
    void ok() {
        webTestClient
                .get()
                .uri("/api/log/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    void insert() {
        LogData log = new LogData(new Date(0), "service-origin", "json_formatted_data");
        webTestClient
                .post()
                .uri("/api/log/")
                .bodyValue(log)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .expectBody()
                .jsonPath("$.origin").isEqualTo("service-origin");
    }

}
