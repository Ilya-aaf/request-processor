package com.itm.space.ilyaaaf.requestprocessor.model.dto;

import com.itm.space.ilyaaaf.requestprocessor.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    private NotificationType type;
    private String message;

}
