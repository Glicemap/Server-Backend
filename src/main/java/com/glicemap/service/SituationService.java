package com.glicemap.service;

import com.glicemap.model.Situation;
import com.glicemap.repository.SituationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SituationService {

    final Logger logger = LoggerFactory.getLogger(SituationService.class);

    @Autowired
    private SituationRepository situationRepository;

    public String getSituationById(int id) {
        logger.info("Situation Service - getSituationById - Getting situation from ID [{}]", id);

        Situation situation = situationRepository.findByCode(id);

        return situation.getSituation();
    }

    public Situation getSituationBySituation(String situation) {
        logger.info("Situation Service - getSituationBySituation - Getting situation from Situation [{}]", situation);
        Situation situationObject = situationRepository.findBySituation(situation);
        if (situationObject != null) {
            logger.info("Situation Service - getSituationBySituation - Situation found [{}]", situationObject.getSituation());
        }
        return situationObject;
    }
}

