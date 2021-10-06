package com.glicemap.service;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.DatesWithMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasureService {
    @Autowired
    private DatesWithMeasuresBuilder datesWithMeasuresBuilder;

    @Autowired
    private DailyMeasuresBuilder dailyMeasuresBuilder;

    @Autowired
    private MeasureBuilder measureBuilder;

    public DatesWithMeasuresDTO getDaysWithMeasure(String documentNumber, String date){
        //aqui faz a busca no banco
        String month = date.split("-")[1];
        List<String> listDates = new ArrayList<>();
        listDates.add("2021-" + month + "-01");
        listDates.add("2021-" + month + "-03");
        listDates.add("2021-" + month + "-04");
        listDates.add("2021-" + month + "-07");
        listDates.add("2021-" + month + "-08");
        listDates.add("2021-" + month + "-11");
        listDates.add("2021-" + month + "-12");
        listDates.add("2021-" + month + "-15");
        listDates.add("2021-" + month + "-17");
        listDates.add("2021-" + month + "-21");
        listDates.add("2021-" + month + "-22");
        listDates.add("2021-" + month + "-23");
        listDates.add("2021-" + month + "-25");
        listDates.add("2021-" + month + "-28");
        listDates.add("2021-" + month + "-30");
        listDates.add("2021-" + month + "-31");

        return datesWithMeasuresBuilder.setDates(listDates).build();
    }

    public DailyMeasuresDTO getDailyMeasures(String documentNumber, String date){
        //Aqui faz a busca no banco de dados
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

        return dailyMeasuresBuilder.setMeasures(measures).build();
    }

    public Boolean postMeasure(PostMeasureDTO postMeasureDTO){
        //aqui faria a inserção no banco
        return Boolean.TRUE;
    }

}
