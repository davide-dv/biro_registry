package it.biro.biro_registry.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private WebClient http;

    @GetMapping("/log")
    public String log(@RequestParam String message) {
        String m = "";
        call(http, "http://log/api/log/", "{\"msg\": \"" + message + "\"}")
                .subscribe(msg -> log.info("SENT: " + msg.toString()));
        return message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class LogReply {
        private String message;
    }

    private Mono<LogReply> call(WebClient http, String url, String body) {
        return http
                .post()
                .uri(url)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(LogReply.class);
    }
}
