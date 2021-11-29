package com.glicemap.controller;

import com.glicemap.dto.*;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/web")
public class WebController {

    final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private MedicService medicService;

    @Autowired
    private MedicInviteService medicInviteService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MeasureService measureService;

    @Autowired
    private ReportService reportService;

    @ApiOperation(value = "Retorna um texto para teste da controller")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna texto de comprimento")
    })
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> index(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
        return new ResponseEntity<>(String.format("Olá, %s! Você está no Web", name), HttpStatus.OK);
    }

    @ApiOperation(value = "Efetua login de médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Resultado do login")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        logger.info("WebController - /login called! - Login [{}]", loginDTO);
        return new ResponseEntity<>(medicService.login(loginDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastra novo médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cadastrou novo médico"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<Boolean> signup(@RequestBody MedicDTO medicDTO) {
        logger.info("WebController - /sign-up called! - MedicDTO [{}]", medicDTO);
        return new ResponseEntity<>(medicService.signUp(medicDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Gera código convite do médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna código de convite")
    })
    @RequestMapping(value = "/new-code", method = RequestMethod.GET)
    public ResponseEntity<String> getNewCode(@RequestHeader String CRM) {
        logger.info("WebController - /new-code called! CRM [{}]", CRM);
        return new ResponseEntity<>(medicInviteService.generateCode(CRM), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista pacientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna lista de pacientes")
    })
    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public ResponseEntity<PatientsListDTO> getPatients(@RequestHeader String CRM, @RequestBody GetPatientsDTO getPatientsDTO) throws ParseException {
        logger.info("WebController - Post /patients called GetPatientsDTO [{}] CRM [{}]", getPatientsDTO, CRM);
        return new ResponseEntity<>(medicService.getPatients(CRM, getPatientsDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca medições de paciente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna as medições do paciente")
    })
    @RequestMapping(value = "/patients/{documentNumber}", method = RequestMethod.POST)
    public ResponseEntity<PatientMeasuresInfoDTO> getPatient(@PathVariable("documentNumber") String documentNumber, @RequestBody GetPatientDTO getPatientDTO) throws ParseException {
        logger.info("WebController - /patients/{} called GetPatientDTO [{}]", documentNumber, getPatientDTO);
        return new ResponseEntity<>(measureService.getMeasuresInfo(documentNumber, getPatientDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca notificações")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna lista de notificações")
    })
    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public ResponseEntity<NotificationsDTO> getNotifications(@RequestHeader String CRM) {
        logger.info("WebController - get /notifications called CRM [{}]", CRM);
        return new ResponseEntity<>(notificationService.getNotifications(CRM), HttpStatus.OK);
    }

    @ApiOperation(value = "Marca notificações como lidas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcou notificações como lidas")
    })
    @RequestMapping(value = "/notifications", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> readNotifications(@RequestBody NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("WebController - put /notifications called NotificationsIdsDTO [{}]", notificationsIdsDTO);
        return new ResponseEntity<>(notificationService.readNotifications(notificationsIdsDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta as notificações")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deletou as notificações")
    })
    @RequestMapping(value = "/notifications", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteNotifications(@RequestBody NotificationsIdsDTO notificationsIdsDTO) {
        logger.info("WebController - delete /notifications called NotificationsIdsDTO [{}]", notificationsIdsDTO);
        return new ResponseEntity<>(notificationService.deleteNotifications(notificationsIdsDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca dados do médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna dados do médico")
    })
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ResponseEntity<MedicDTO> getSettings(@RequestHeader String CRM) {
        logger.info("WebController - get /settings called CRM [{}]", CRM);
        return new ResponseEntity<>(medicService.getMedicDTO(CRM), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza dados do médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atualizou dados do médico")
    })
    @RequestMapping(value = "/settings", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> updateSettings(@RequestBody MedicDTO medicDTO) {
        logger.info("WebController - put /settings called MedicDTO [{}]", medicDTO);
        return new ResponseEntity<>(medicService.updateData(medicDTO), HttpStatus.OK);
    }


    @ApiOperation(value = "Atualiza senha do médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atualizou senha do médico")
    })
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> updatePassword(@RequestHeader String CRM, @RequestBody PasswordDTO passwordDTO) {
        logger.info("WebController - put /password called PasswordDTO [{}] CRM [{}]", passwordDTO, CRM);
        return new ResponseEntity<>(medicService.updatePassword(CRM, passwordDTO), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @ApiOperation(value = "Retorna um pdf no padrão das UBS do relatório glicemico no período informado")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Retorna o relatório em pdf"),
//            @ApiResponse(code = 500, message = "Houve uma exceção")
//    })
    @RequestMapping(value = "/report", method = RequestMethod.GET, produces = "application/pdf")
    public void exportReport(HttpServletResponse response,
                             @RequestHeader("documentNumber") String documentNumber,
                             @RequestHeader("dateBegin") String dateBegin,
                             @RequestHeader("dateEnd") String dateEnd) throws BaseBusinessException, ParseException {

        logger.info("WebController - /report called! documentNumber = [{}], dateBegin = [{}], dateEnd = [{}]", documentNumber, dateBegin, dateEnd);

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relatorio.pdf";

        response.setHeader(headerKey, headerValue);

        reportService.export(response, measureService.getMeasuresFromInterval(documentNumber, dateBegin, dateEnd));
    }
}