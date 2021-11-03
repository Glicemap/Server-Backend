package com.glicemap.controller;

import com.glicemap.dto.*;
import com.glicemap.service.MedicService;
import com.glicemap.service.NotificationService;
import com.glicemap.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web")
public class WebController {

    Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private MedicService medicService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @ApiOperation(value = "Retorna um texto para teste da controller")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna texto de comprimento")
    })
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> index(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
        return new ResponseEntity<>(String.format("Olá, %s! Você está no Web", name), HttpStatus.OK);
    }

    @ApiOperation(value = "Gera código convite do médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna código de convite")
    })
    @RequestMapping(value = "/get-new-code", method = RequestMethod.GET)
    public ResponseEntity<String> getNewCode() {
        logger.info("WebController - /get-new-code called!");
        return new ResponseEntity<>(medicService.generateCode(), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista pacientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna lista de pacientes")
    })
    @RequestMapping(value = "/get-patients", method = RequestMethod.GET)
    public ResponseEntity<PatientsListDTO> getPatients(@RequestBody GetPatientsDTO getPatientsDTO) {
        logger.info("WebController - /get-patients called GetPatientsDTO [{}]", getPatientsDTO);
        return new ResponseEntity<>(medicService.getPatients(getPatientsDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca medições de paciente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna as medições do paciente")
    })
    @RequestMapping(value = "/get-patient", method = RequestMethod.GET)
    public ResponseEntity<PatientMeasuresInfoDTO> getPatients(@RequestBody GetPatientDTO getPatientDTO) {
        logger.info("WebController - /get-patient called GetPatientDTO [{}]", getPatientDTO);
        return new ResponseEntity<>(userService.getMeasuresInfo(getPatientDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca notificações")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna lista de notificações")
    })
    @RequestMapping(value = "/get-notifications", method = RequestMethod.GET)
    public ResponseEntity<NotificationsDTO> getNotifications(@RequestHeader String CRM) {
        logger.info("WebController - /get-notifications called CRM [{}]", CRM);
        return new ResponseEntity<>(notificationService.getNotifications(CRM), HttpStatus.OK);
    }

    @ApiOperation(value = "Marca notificações como lidas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcou notificações como lidas")
    })
    @RequestMapping(value = "/read-notifications", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> readNotifications(@RequestBody NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("WebController - /read-notifications called NotificationsIdsDTO [{}]", notificationsIdsDTO);
        return new ResponseEntity<>(notificationService.readNotifications(notificationsIdsDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta as notificações")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deletou as notificações")
    })
    @RequestMapping(value = "/delete-notifications", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteNotifications(@RequestBody NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("WebController - /delete-notifications called NotificationsIdsDTO [{}]", notificationsIdsDTO);
        return new ResponseEntity<>(notificationService.deleteNotifications(notificationsIdsDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca dados do médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna dados do médico")
    })
    @RequestMapping(value = "/get-settings", method = RequestMethod.GET)
    public ResponseEntity<MedicDTO> getSettings(@RequestHeader String CRM) {
        logger.info("WebController - /get-settings called CRM [{}]", CRM);
        return new ResponseEntity<>(medicService.getMedicDTO(CRM), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza dados do médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atualizou dados do médico")
    })
    @RequestMapping(value = "/update-settings", method = RequestMethod.POST)
    public ResponseEntity<Boolean> updateSettings(@RequestBody MedicDTO medicDTO) {
        logger.info("WebController - /update-settings called MedicDTO [{}]", medicDTO);
        return new ResponseEntity<>(medicService.updateData(medicDTO), HttpStatus.OK);
    }
}