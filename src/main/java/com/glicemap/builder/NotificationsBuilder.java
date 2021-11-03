package com.glicemap.builder;

import com.glicemap.dto.NotificationDTO;
import com.glicemap.dto.NotificationsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class NotificationsBuilder {
    private List<NotificationDTO> notifications;

    public NotificationsBuilder setNotifications(List<NotificationDTO> notifications) {
        this.notifications = notifications;
        return this;
    }

    public NotificationsDTO build() {
        NotificationsDTO notificationsDTO = new NotificationsDTO();
        notificationsDTO.setNotifications(notifications);
        return notificationsDTO;
    }
}
