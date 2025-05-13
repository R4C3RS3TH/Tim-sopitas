package dbp.recuperation.hack1.event.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/orders")
    public ResponseEntity<String> postMethodName(@RequestBody String entity) {

        return null;
    }

}
