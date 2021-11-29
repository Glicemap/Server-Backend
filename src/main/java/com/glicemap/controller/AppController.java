package com.glicemap.controller;

import com.glicemap.dto.*;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.service.MeasureService;
import com.glicemap.service.ReportService;
import com.glicemap.service.UserService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/app")
public class AppController {

    final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private UserService userService;

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
        logger.info("AppController - /hello called! Name = [{}]", name);
        return new ResponseEntity<>(String.format("Olá, %s! Você está no App", name), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @ApiOperation(value = "Efetua login de usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna se o login foi correto ou não"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> userLogin(@RequestBody LoginDTO loginDTO) {
        logger.info("AppController - /login called! LoginDTO = [{}]", loginDTO);
        return new ResponseEntity<>(userService.login(loginDTO), HttpStatus.OK);
    }

    @Transactional
    @ApiOperation(value = "Efetua cadastro de usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cadastro efetuado"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<Boolean> userSignUp(@RequestBody UserDTO userDTO) throws BaseBusinessException, ParseException {
        logger.info("AppController - /sign-up called! UserDTO = [{}]", userDTO);
        return new ResponseEntity<>(userService.signUp(userDTO), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @ApiOperation(value = "Retorna um json com informações do médico e do usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna json com informações"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserMedicInfoDTO> getUserInfo(@RequestHeader("documentNumber") String documentNumber) {
        logger.info("AppController - get /info called! documentNumber = [{}]", documentNumber);
        return new ResponseEntity<>(userService.getUserMedicInfo(documentNumber), HttpStatus.OK);
    }

    @Transactional
    @ApiOperation(value = "Atualiza o Cadastro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atualização efetuada"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> updateInfo(@RequestBody UserDTO userDTO) throws BaseBusinessException, ParseException {
        logger.info("AppController - put /info called! UserDTO = [{}]", userDTO);
        return new ResponseEntity<>(userService.updateInfo(userDTO), HttpStatus.OK);
    }

    @Transactional
    @ApiOperation(value = "Desvincula médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Médico desvinculado"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/medic", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMedic(@RequestHeader String documentNumber) throws BaseBusinessException {
        logger.info("AppController - delete /medic called! documentNumber = [{}]", documentNumber);
        return new ResponseEntity<>(userService.deleteMedic(documentNumber), HttpStatus.OK);
    }

    @Transactional
    @ApiOperation(value = "Vincula médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Médico vinculado"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/medic", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addMedic(@RequestHeader String documentNumber, @RequestHeader String code) throws BaseBusinessException {
        logger.info("AppController - post /medic called! documentNumber = [{}], code = [{}]", documentNumber, code);
        return new ResponseEntity<>(userService.addMedic(documentNumber, code), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @ApiOperation(value = "Retorna um json com uma lista de datas onde houveram registros glicemicos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna a lista de datas"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/measures/month", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DatesWithMeasuresDTO> searchMeasuresMonth(@RequestHeader("documentNumber") String documentNumber,
                                                                    @RequestHeader("date") String date) throws ParseException {

        logger.info("AppController - /measures/month called! documentNumber = [{}], date = [{}]", documentNumber, date);
        return new ResponseEntity<>(measureService.getDaysWithMeasure(documentNumber, date), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @ApiOperation(value = "Retorna um json com uma lista dos registros glicemicos de um certo dia")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna a lista de registros"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/measures/day", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DailyMeasuresDTO> searchMeasuresDay(@RequestHeader("documentNumber") String documentNumber,
                                                              @RequestHeader("date") String date) throws ParseException {

        logger.info("AppController - /measures/day called! documentNumber = [{}], date = [{}]", documentNumber, date);
        return new ResponseEntity<>(measureService.getDailyMeasures(documentNumber, date), HttpStatus.OK);
    }

    @Transactional
    @ApiOperation(value = "Insere registro glicemico na base de dados")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Inseriu registro na base"),
            @ApiResponse(code = 503, message = "Não foi possivel inserir registro na base"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/measures", method = RequestMethod.POST)
    public ResponseEntity<Boolean> postMeasure(@RequestBody PostMeasureDTO postMeasureDTO) throws ParseException {
        logger.info("AppController - post /measures called! PostMeasureDTO = [{}]", postMeasureDTO);
        return new ResponseEntity<>(measureService.postMeasure(postMeasureDTO), HttpStatus.OK);
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

        logger.info("AppController - /report called! documentNumber = [{}], dateBegin = [{}], dateEnd = [{}]", documentNumber, dateBegin, dateEnd);

        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relatorio_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        reportService.export(response, measureService.getMeasuresFromInterval(documentNumber, dateBegin, dateEnd));
    }
}