package com.itm.space.ilyaaaf.requestprocessor.strategy.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itm.space.ilyaaaf.requestprocessor.model.enums.NotificationType;
import com.itm.space.ilyaaaf.requestprocessor.repository.NotificationOutboxRepository;
import com.itm.space.ilyaaaf.requestprocessor.strategy.AbstractNotificationStrategy;
import org.springframework.stereotype.Component;

@Component
public class TelegramNotificationStrategy extends AbstractNotificationStrategy {

    public TelegramNotificationStrategy(NotificationOutboxRepository repo, ObjectMapper mapper) {
        super(repo, mapper);
    }

    @Override public NotificationType getType() {
        return NotificationType.TG_MESSAGE;
    }

    @Override protected String getTopic() {
        return "telegram-events";
    }
}
