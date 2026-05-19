package com.itm.space.ilyaaaf.requestprocessor.strategy.doc;

import com.itm.space.ilyaaaf.requestprocessor.model.enums.NotificationType;
import com.itm.space.ilyaaaf.requestprocessor.model.request.NotificationRequest;

public interface NotificationStrategy {

    NotificationType getType();

    void handle(NotificationRequest request);
}
