package com.glicemap.service;

import com.glicemap.builder.NotificationBuilder;
import com.glicemap.builder.NotificationsBuilder;
import com.glicemap.dto.NotificationDTO;
import com.glicemap.dto.NotificationsDTO;
import com.glicemap.dto.NotificationsIdsDTO;
import com.glicemap.model.Medic;
import com.glicemap.model.Notification;
import com.glicemap.model.User;
import com.glicemap.repository.NotificationRepository;
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
    private NotificationsBuilder notificationsBuilder;

    @Autowired
    private NotificationBuilder notificationBuilder;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MedicService medicService;

    public NotificationsDTO getNotifications(String CRM) {
        logger.info("NotificationService - getNotifications - Getting notifications from CRM [{}]", CRM);

        Medic medic = medicService.getMedic(CRM);
        List<Notification> notifications = notificationRepository.findAllByMedic(medic);

        List<NotificationDTO> listNotifications = new ArrayList<>();
        for (Notification notification : notifications){
            User user =  notification.getUser();
            String text = String.format("%s %s %s a você.", user.getName(), user.getLastName(), notification.isType() ? "vinculou-se" : "desvinculou-se");
            NotificationDTO notificationDTO = notificationBuilder.setRead(notification.isRead())
                                                                 .setText(text)
                                                                 .setId(Integer.toString(notification.getCode())).build();
            listNotifications.add(notificationDTO);
        }

        return notificationsBuilder.setNotifications(listNotifications).build();
    }

    public void save(Notification notification){
        logger.info("NotificationService - saveNotification - notification [{}]", notification);
        notificationRepository.save(notification);
    }

    public Boolean readNotifications(NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("NotificationService - readNotifications - Reading notifications ids [{}]", notificationsIdsDTO);

        for (String id : notificationsIdsDTO.getIds()){
            int code = Integer.parseInt(id);
            Notification notification = notificationRepository.findByCode(code);
            notification.setRead(true);
            this.save(notification);
        }

        return Boolean.TRUE;
    }

    public Boolean deleteNotifications(NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("NotificationService - deleteNotifications - Deleting notifications ids [{}]", notificationsIdsDTO);

        for (String id : notificationsIdsDTO.getIds()){
            int code = Integer.parseInt(id);
            Notification notification = notificationRepository.findByCode(code);
            notification.setStatus(false);
            this.save(notification);
        }

        return Boolean.TRUE;
    }
}

