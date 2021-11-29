package com.glicemap.service;

import com.glicemap.model.Medic;
import com.glicemap.model.MedicInvite;
import com.glicemap.repository.MedicInviteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

@Service
public class MedicInviteService {

    final Logger logger = LoggerFactory.getLogger(MedicInviteService.class);

    @Autowired
    private MedicInviteRepository medicInviteRepository;

    @Autowired
    private MedicService medicService;

    public MedicInvite findActiveByMedic(Medic medic) {
        return medicInviteRepository.findActiveByMedic(medic);
    }

    public void save(MedicInvite medicInvite) {
        medicInviteRepository.save(medicInvite);
    }

    public MedicInvite findByCode(String code) {
        return medicInviteRepository.findByCode(code);
    }

    private String codeGenerator() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final char[] charsArray = chars.toCharArray();
        char[] charsCode = new char[6];
        Random random = new Random();
        for (int i = 0; i < charsCode.length; ++i) {
            charsCode[i] = charsArray[random.nextInt(charsArray.length)];
        }
        String code = String.valueOf(charsCode);
        logger.info("MedicService - generateCode - Code generated [{}]", code);
        return code;
    }

    public String generateCode(String CRM) {
        logger.info("MedicService - generateCode - Generating new code - CRM [{}]", CRM);

        // Invalida codigo atual se existir
        Medic medic = medicService.getMedic(CRM);
        MedicInvite currentInvite = this.findActiveByMedic(medic);
        logger.info("MedicService - generateCode - Current Invite [{}]", currentInvite);
        if (currentInvite != null) {
            currentInvite.setStatus(false);
            this.save(currentInvite);
        }

        // gera código até que seja válido
        MedicInvite invite;
        String code;
        do {
            code = codeGenerator();
            logger.info("MedicService - generateCode - Code generated [{}]", code);
            invite = this.findByCode(code);
            logger.info("MedicService - generateCode - Invite [{}]", invite);
        } while (invite != null);

        // salva código
        MedicInvite newInvite = new MedicInvite(code, true, medic, new Date((System.currentTimeMillis())));
        this.save(newInvite);

        return code;
    }

}

