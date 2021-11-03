package com.glicemap.builder;

import com.glicemap.dto.NotificationDTO;
import org.springframework.stereotype.Component;

@Component
public final class NotificationBuilder {
    private String id;
    private String text;
    private Boolean read;

    public NotificationBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public NotificationBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public NotificationBuilder setRead(Boolean read) {
        this.read = read;
        return this;
    }

    public NotificationDTO build() {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(id);
        notificationDTO.setText(text);
        notificationDTO.setRead(read);
        return notificationDTO;
    }
}
