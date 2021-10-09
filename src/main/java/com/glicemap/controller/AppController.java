package com.glicemap.controller;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.DatesWithMeasuresDTO;
import com.glicemap.dto.PostMeasureDTO;
import com.glicemap.dto.UserMedicInfoDTO;
import com.glicemap.service.InformationService;
import com.glicemap.dto.UserDTO;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/app")
public class AppController {

    Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MeasureService measureService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private InformationService informationService;

    @ApiOperation(value = "Retorna um texto para teste da controller")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna texto de comprimento")
    })
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> index(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
        logger.info("AppController - /hello called! Name = [{}]", name);
        return new ResponseEntity<>(String.format("Olá, %s! Você está no App", name), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um json com uma lista de datas onde houveram registros glicemicos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna a lista de datas"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/searchMeasures/month", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DatesWithMeasuresDTO> searchMeasuresMonth(@RequestHeader("documentNumber") String documentNumber,
                                                                    @RequestHeader("date") String date) {

        logger.info("AppController - /searchMeasures/month called! documentNumber = [{}], date = [{}]", documentNumber, date);
        return new ResponseEntity<>(measureService.getDaysWithMeasure(documentNumber, date), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um json com uma lista dos registros glicemicos de um certo dia")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna a lista de registros"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/searchMeasures/day", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DailyMeasuresDTO> searchMeasuresDay(@RequestHeader("documentNumber") String documentNumber,
                                                              @RequestHeader("date") String date) {

        logger.info("AppController - /searchMeasures/day called! documentNumber = [{}], date = [{}]", documentNumber, date);
        return new ResponseEntity<>(measureService.getDailyMeasures(documentNumber, date), HttpStatus.OK);
    }

    @ApiOperation(value = "Insere registro glicemico na base de dados")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Inseriu registro na base"),
            @ApiResponse(code = 503, message = "Não foi possivel inserir registro na base"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/postMeasure", method = RequestMethod.POST)
    public ResponseEntity<Boolean> postMeasure(@RequestBody PostMeasureDTO postMeasureDTO) {
        logger.info("AppController - /postMeasure called! PostMeasureDTO = [{}]", postMeasureDTO);
        return new ResponseEntity<>(measureService.postMeasure(postMeasureDTO), HttpStatus.OK);
    }

    //TODO - Colocar Infos do Médico
    @ApiOperation(value = "Retorna um json com informações do médico e do usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retorna json com informações"),
            @ApiResponse(code = 500, message = "Houve uma exceção")
    })
    @RequestMapping(value = "/getInfo/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("documentNumber") String documentNumber) {
        logger.info("AppController - /getInfo/user called! documentNumber = [{}]", documentNumber);
        return new ResponseEntity<>(informationService.getUserMedicInfo(documentNumber), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um pdf no padrão das UBS do relatório glicemico no período informado")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Retorna o relatório em pdf"),
//            @ApiResponse(code = 500, message = "Houve uma exceção")
//    })
    @RequestMapping(value = "/exportReport", method = RequestMethod.GET, produces = "application/pdf")
    public void exportReport(HttpServletResponse response,
                             @RequestHeader("documentNumber") String documentNumber,
                             @RequestHeader("dateBegin") String dateBegin,
                             @RequestHeader("dateEnd") String dateEnd) throws BaseBusinessException {

        logger.info("AppController - /exportReport called! documentNumber = [{}], dateBegin = [{}], dateEnd = [{}]", documentNumber, dateBegin, dateEnd);

        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=relatorio_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        reportService.export(response, measureService.getMeasuresFromInterval(documentNumber, dateBegin, dateEnd));
    }
}