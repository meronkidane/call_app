package com.streamhub.notificationservice.controller;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.notificationservice.dto.NotificationRequest;
import com.streamhub.notificationservice.service.NotificationFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {

    private final NotificationFacade notificationFacade;

    public NotificationController(NotificationFacade notificationFacade) {
        this.notificationFacade = notificationFacade;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> send(@Valid @RequestBody NotificationRequest request) {
        return notificationFacade.send(request);
    }
}
