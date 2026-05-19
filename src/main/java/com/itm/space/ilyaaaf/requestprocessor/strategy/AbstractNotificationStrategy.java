package com.itm.space.ilyaaaf.requestprocessor.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itm.space.ilyaaaf.requestprocessor.model.entity.NotificationOutbox;
import com.itm.space.ilyaaaf.requestprocessor.model.request.NotificationRequest;
import com.itm.space.ilyaaaf.requestprocessor.repository.NotificationOutboxRepository;
import com.itm.space.ilyaaaf.requestprocessor.strategy.doc.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractNotificationStrategy implements NotificationStrategy {

    private final NotificationOutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void handle(NotificationRequest request) {
        UUID key = UUID.randomUUID();
        String topic = getTopic();
        String payload = convertToJson(request);

        NotificationOutbox outbox = buildOutboxEntity(topic, key, payload);

        outboxRepository.save(outbox);

        log.info("Подготовлено сообщение для отправки. Key: <{}>, Payload: <{}>, topic: <{}>", key, payload, topic);
    }

    protected abstract String getTopic();

    private NotificationOutbox buildOutboxEntity(String topic, UUID key, String payload) {
        return new NotificationOutbox()
                .setTopic(topic)
                .setKey(key.toString())
                .setValue(payload)
                .setSent(false)
                .setAttempt(1)
                .setCreatedAt(LocalDateTime.now());
    }

    private String convertToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new IllegalStateException("Ошибка конвертации тела сообщения в JSON", e);
        }
    }
}
