package com.itm.space.ilyaaaf.requestprocessor.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification_outbox")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class NotificationOutbox {

    @Id
    @Comment("Уникальный идентификатор события")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Comment("Дата и время создания записи")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Comment("Название топика Kafka")
    private String topic;

    @Column(nullable = false)
    @Comment("Ключ сообщения")
    private String key;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Comment("Тело сообщения")
    private String value;

    @Column(nullable = false)
    @Comment("Флаг отправки")
    private Boolean sent = false;

    @Column(nullable = false)
    @Comment("Количество попыток отправки")
    private Integer attempt = 1;


}
