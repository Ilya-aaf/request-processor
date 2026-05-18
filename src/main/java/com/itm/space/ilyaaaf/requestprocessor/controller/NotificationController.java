package com.itm.space.ilyaaaf.requestprocessor.controller;

import com.itm.space.ilyaaaf.requestprocessor.constant.ApiConstant;
import com.itm.space.ilyaaaf.requestprocessor.model.dto.NotificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiConstant.BASE_NOTIFICATIONS_URL)
public interface NotificationController {

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody NotificationRequest request);

}
