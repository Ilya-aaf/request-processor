package com.itm.space.ilyaaaf.requestprocessor.strategy.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itm.space.ilyaaaf.requestprocessor.model.enums.NotificationType;
import com.itm.space.ilyaaaf.requestprocessor.repository.NotificationOutboxRepository;
import com.itm.space.ilyaaaf.requestprocessor.strategy.AbstractNotificationStrategy;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationStrategy extends AbstractNotificationStrategy {

    public SmsNotificationStrategy(NotificationOutboxRepository outboxRepository, ObjectMapper objectMapper) {
        super(outboxRepository, objectMapper);
    }

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }

    @Override
    protected String getTopic() {
        return "sms-events";
    }
}
