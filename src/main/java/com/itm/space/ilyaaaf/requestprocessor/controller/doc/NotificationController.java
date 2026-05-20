package com.itm.space.ilyaaaf.requestprocessor.controller.doc;

import com.itm.space.ilyaaaf.requestprocessor.constant.ApiConstant;
import com.itm.space.ilyaaaf.requestprocessor.model.request.NotificationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiConstant.BASE_NOTIFICATIONS_URL)
public interface NotificationController {

    @PostMapping
    ResponseEntity<Void> sendNotification(@Valid @RequestBody NotificationRequest request);

}

