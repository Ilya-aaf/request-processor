package com.itm.space.ilyaaaf.requestprocessor.scheduler;

import com.itm.space.ilyaaaf.requestprocessor.model.entity.NotificationOutbox;
import com.itm.space.ilyaaaf.requestprocessor.repository.NotificationOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationOutboxScheduler {

    private final NotificationOutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${outbox.batch-size}")
    private int batchSize;

    @Scheduled(fixedDelayString = "${outbox.delay-ms}")
    public void processOutbox() {

        List<NotificationOutbox> pendingRecords = outboxRepository
                .findBySentFalseOrderByCreatedAtAsc(PageRequest.of(0, batchSize));

        if (pendingRecords.isEmpty()) {
            return;
        }

        log.info("Найдено {} неотправленных сообщений в Outbox. Начинаем отправку...", pendingRecords.size());

        for (NotificationOutbox outbox : pendingRecords) {
            try {
                kafkaTemplate.send(outbox.getTopic(), outbox.getKey(), outbox.getValue()).get();
                outbox.setSent(true);
                log.info("Сообщение с Key: <{}> успешно отправлено в топик {}", outbox.getKey(), outbox.getTopic());

            } catch (Exception e) {
                outbox.setAttempt(outbox.getAttempt() + 1);
                log.error("Не удалось отправить сообщение с Key: <{}>. Попытка №{}",
                        outbox.getKey(), outbox.getAttempt(), e);
            }
        }

        outboxRepository.saveAll(pendingRecords);
    }
}
