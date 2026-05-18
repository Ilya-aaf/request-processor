package com.itm.space.ilyaaaf.requestprocessor.model.request;

import com.itm.space.ilyaaaf.requestprocessor.model.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Тип уведомления должен быть указан (SMS, EMAIL, PUSH, TG_MESSAGE)")
    private NotificationType type;

    @NotBlank(message = "Текст уведомления не может быть пустым")
    private String message;

}
