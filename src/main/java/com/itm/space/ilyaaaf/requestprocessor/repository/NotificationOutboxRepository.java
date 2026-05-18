package com.itm.space.ilyaaaf.requestprocessor.repository;

import com.itm.space.ilyaaaf.requestprocessor.model.entity.NotificationOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationOutboxRepository extends JpaRepository<NotificationOutbox, UUID> {
}
