package com.itm.space.ilyaaaf.requestprocessor.strategy.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itm.space.ilyaaaf.requestprocessor.model.enums.NotificationType;
import com.itm.space.ilyaaaf.requestprocessor.repository.NotificationOutboxRepository;
import com.itm.space.ilyaaaf.requestprocessor.strategy.AbstractNotificationStrategy;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationStrategy extends AbstractNotificationStrategy {

    public EmailNotificationStrategy(NotificationOutboxRepository repo, ObjectMapper mapper) {
        super(repo, mapper);
    }

    @Override public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override protected String getTopic() {
        return "email-events"; }
}
