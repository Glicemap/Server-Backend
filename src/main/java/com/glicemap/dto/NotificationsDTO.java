package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NotificationsDTO implements Serializable {

    private static final long serialVersionUID = 4049935626399669493L;

    @JsonProperty("notifications")
    private List<NotificationDTO> notifications;
}
