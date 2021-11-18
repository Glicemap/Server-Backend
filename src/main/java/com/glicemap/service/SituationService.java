package com.glicemap.service;

import com.glicemap.builder.NotificationBuilder;
import com.glicemap.builder.NotificationsBuilder;
import com.glicemap.dto.NotificationDTO;
import com.glicemap.dto.NotificationsDTO;
import com.glicemap.dto.NotificationsIdsDTO;
import com.glicemap.indicator.SituationsIndicator;
import com.glicemap.model.Situation;
import com.glicemap.repository.SituationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SituationService {

    Logger logger = LoggerFactory.getLogger(SituationService.class);

    @Autowired
    private SituationRepository situationRepository;

    public String getSituationById(int id){
        logger.info("Situation Service - getSituationById - Getting situation from ID [{}]", id);

        Situation situation = situationRepository.findByCode(id);

        return situation.getSituation().toString();
    }

    public Situation getSituationBySituation(String situation){
        return situationRepository.findBySituation(situation);
    }
}

