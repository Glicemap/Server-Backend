package com.glicemap.controller;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.DatesWithMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.DatesWithMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private DatesWithMeasuresBuilder datesWithMeasuresBuilder;

    @Autowired
    private DailyMeasuresBuilder dailyMeasuresBuilder;

    @Autowired
    private MeasureBuilder measureBuilder;

    @GetMapping("/hello")
    public ResponseEntity<String> index(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
        return new ResponseEntity<>(String.format("Olá, %s! Você está no App", name), HttpStatus.OK);
    }

    @GetMapping("/searchMeasures/month")
    public ResponseEntity<DatesWithMeasuresDTO> searchMeasuresMonth(@RequestHeader("documentNumber") String documentNumber, @RequestHeader("date") String date) {
        List<String> listDates = new ArrayList<>();
        listDates.add("2021-10-01");
        listDates.add("2021-10-03");
        listDates.add("2021-10-04");
        listDates.add("2021-10-07");
        listDates.add("2021-10-08");
        listDates.add("2021-10-11");
        listDates.add("2021-10-12");
        listDates.add("2021-10-15");
        listDates.add("2021-10-17");
        listDates.add("2021-10-21");
        listDates.add("2021-10-22");
        listDates.add("2021-10-23");
        listDates.add("2021-10-25");
        listDates.add("2021-10-28");
        listDates.add("2021-10-30");
        listDates.add("2021-10-31");

        DatesWithMeasuresDTO datesWithMeasuresDTO = datesWithMeasuresBuilder.setDates(listDates).build();

        return new ResponseEntity<>(datesWithMeasuresDTO, HttpStatus.OK);
    }

    @GetMapping("/searchMeasures/day")
    public ResponseEntity<DailyMeasuresDTO> searchMeasuresDay(@RequestHeader("documentNumber") String documentNumber, @RequestHeader("date") String date) {
        List<MeasureDTO> measures = new ArrayList<>();

        measures.add(measureBuilder.setInsulin("2")
                .setSugarLevel("110")
                .setSituation("Antes do almoço")
                .setObservations("Medi depois de uma caminhada")
                .build());

        measures.add(measureBuilder.setInsulin("3")
                .setSituation("Antes do lanche da tarde")
                .setObservations(null)
                .setSugarLevel("200")
                .build());

        measures.add(measureBuilder.setInsulin("1")
                .setSituation("Depois da janta")
                .setObservations("Medi logo antes de dormir")
                .setSugarLevel("140")
                .build());

        DailyMeasuresDTO dailyMeasuresDTO = dailyMeasuresBuilder.setMeasures(measures).build();

        return new ResponseEntity<>(dailyMeasuresDTO, HttpStatus.OK);
    }
}