package com.itm.space.ilyaaaf.requestprocessor.controller.impl;

import com.itm.space.ilyaaaf.requestprocessor.controller.doc.NotificationController;
import com.itm.space.ilyaaaf.requestprocessor.model.request.NotificationRequest;
import com.itm.space.ilyaaaf.requestprocessor.service.doc.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<Void> sendNotification(NotificationRequest request) {
        log.info("Получен запрос на отправку уведомления: {}", request);
        notificationService.sendNotification(request);
        return ResponseEntity.accepted().build();
    }
}
