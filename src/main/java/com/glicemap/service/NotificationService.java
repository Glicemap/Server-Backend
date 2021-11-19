package com.glicemap.service;

import com.glicemap.builder.NotificationBuilder;
import com.glicemap.builder.NotificationsBuilder;
import com.glicemap.dto.NotificationDTO;
import com.glicemap.dto.NotificationsDTO;
import com.glicemap.dto.NotificationsIdsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationBuilder notificationBuilder;

    @Autowired
    private NotificationsBuilder notificationsBuilder;

    public NotificationsDTO getNotifications(String CRM) {
        logger.info("NotificationService - getNotifications - Getting notifications from CRM [{}]", CRM);

        List<NotificationDTO> listNotifications = new ArrayList<>();

        listNotifications.add(notificationBuilder.setId("1").setText("Marco Aurélio agora é seu paciente!").setRead(Boolean.FALSE).build());
        listNotifications.add(notificationBuilder.setId("2").setText("Brian Nunes agora é seu paciente!").setRead(Boolean.FALSE).build());
        listNotifications.add(notificationBuilder.setId("3").setText("Victor Padula agora é seu paciente!").setRead(Boolean.FALSE).build());
        listNotifications.add(notificationBuilder.setId("4").setText("Gustavo Trivaletto agora é seu paciente!").setRead(Boolean.FALSE).build());

        return notificationsBuilder.setNotifications(listNotifications).build();
    }

    public Boolean readNotifications(NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("NotificationService - readNotifications - Getting notifications ids [{}]", notificationsIdsDTO);
        return Boolean.TRUE;
    }

    public Boolean deleteNotifications(NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("NotificationService - deleteNotifications - Deleting notifications ids [{}]", notificationsIdsDTO);
        return Boolean.TRUE;
    }
}

