package com.itm.space.ilyaaaf.requestprocessor.service.impl;

import com.itm.space.ilyaaaf.requestprocessor.model.enums.NotificationType;
import com.itm.space.ilyaaaf.requestprocessor.model.request.NotificationRequest;
import com.itm.space.ilyaaaf.requestprocessor.service.doc.NotificationService;
import com.itm.space.ilyaaaf.requestprocessor.strategy.doc.NotificationStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Map<NotificationType, NotificationStrategy> strategyMap = new HashMap<>();

    public NotificationServiceImpl(Set<NotificationStrategy> strategies) {
        strategies.forEach(strategy -> strategyMap.put(strategy.getType(), strategy));
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        NotificationStrategy strategy = strategyMap.get(request.getType());

        if (strategy == null) {
            throw new IllegalArgumentException("Не найдена стратегия для типа уведомления: " + request.getType());
        }

        strategy.handle(request);
    }
}
