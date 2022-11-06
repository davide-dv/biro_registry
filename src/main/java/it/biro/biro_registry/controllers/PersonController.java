package it.biro.biro_registry.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private WebClient http;

    @GetMapping("/")
    public String getPerson() {
        return "PERSON JSON";
    }

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
