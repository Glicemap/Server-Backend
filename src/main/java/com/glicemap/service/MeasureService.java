package com.glicemap.service;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.DatesWithMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.DatesWithMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import com.glicemap.dto.PostMeasureDTO;
import com.glicemap.indicator.SituationsIndicator;
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

    public DatesWithMeasuresDTO getDaysWithMeasure(String documentNumber, String date) {
        // aqui faz a busca no banco
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

    public DailyMeasuresDTO getDailyMeasures(String documentNumber, String date) {
        // Aqui faz a busca no banco de dados
        List<MeasureDTO> measures = new ArrayList<>();

        measures.add(measureBuilder.setInsulin("4").setSituation(SituationsIndicator.ANTES_CAFE.getString())
                .setObservations(null).setSugarLevel("299").build());

        measures.add(measureBuilder.setInsulin("1").setSituation(SituationsIndicator.DEPOIS_CAFE.getString())
                .setObservations(null).setSugarLevel("130").build());

        measures.add(measureBuilder.setInsulin("2").setSugarLevel("110")
                .setSituation(SituationsIndicator.ANTES_ALMOCO.getString())
                .setObservations("Medi depois de uma caminhada").build());

        measures.add(measureBuilder.setInsulin("3")
                .setSituation(SituationsIndicator.ANTES_LANCHE_TARDE.getString()).setObservations(null)
                .setSugarLevel("200").build());

        measures.add(measureBuilder.setInsulin("2")
                .setSituation(SituationsIndicator.DEPOIS_LANCHE_TARDE.getString()).setObservations(null)
                .setSugarLevel("135").build());

        measures.add(measureBuilder.setInsulin("2").setSituation(SituationsIndicator.ANTES_JANTAR.getString())
                .setObservations(null).setSugarLevel("140").build());

        measures.add(measureBuilder.setInsulin("0").setSituation(SituationsIndicator.DEPOIS_JANTAR.getString())
                .setObservations("Tomei um copo de iogurte antes de dormir").setSugarLevel("150")
                .build());

        measures.add(measureBuilder.setInsulin("1").setSituation(SituationsIndicator.ANTES_DORMIR.getString())
                .setObservations("Medi logo antes de dormir").setSugarLevel("140").build());

        return dailyMeasuresBuilder.setMeasures(measures).build();
    }

    public Boolean postMeasure(PostMeasureDTO postMeasureDTO) {
        // aqui faria a inserção no banco
        return Boolean.TRUE;
    }

    public List<DailyMeasuresDTO> getMeasuresFromInterval(String documentNumber, String dateBegin, String dateEnd) {
        List<DailyMeasuresDTO> dailyMeasures = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            dailyMeasures.add(dailyMeasureMocker(i));
        }

        return dailyMeasures;
    }

    private DailyMeasuresDTO dailyMeasureMocker(Integer option) {
        List<MeasureDTO> measures = new ArrayList<>();
        if (option == 1) {
            measures.add(measureBuilder.setInsulin("2").setSugarLevel("110")
                    .setSituation(SituationsIndicator.ANTES_ALMOCO.getString())
                    .setObservations("Medi depois de uma caminhada").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.DEPOIS_LANCHE_TARDE.getString())
                    .setObservations(null).setSugarLevel("200").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.DEPOIS_JANTAR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("140").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-05").build();
        } else if (option == 2) {
            measures.add(measureBuilder.setInsulin("5")
                    .setSituation(SituationsIndicator.ANTES_CAFE.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("104").build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("70")
                    .setSituation(SituationsIndicator.DEPOIS_ALMOCO.getString())
                    .setObservations("Comi bolacha").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.ANTES_LANCHE_TARDE.getString())
                    .setObservations(null).setSugarLevel("116").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.ANTES_DORMIR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("850").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-06").build();
        } else if (option == 3) {
            measures.add(measureBuilder.setInsulin("5")
                    .setSituation(SituationsIndicator.DEPOIS_CAFE.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("150").build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("182")
                    .setSituation(SituationsIndicator.ANTES_LANCHE_MANHA.getString())
                    .setObservations(null).build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("115")
                    .setSituation(SituationsIndicator.ANTES_ALMOCO.getString())
                    .setObservations("Comi bolacha").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.DEPOIS_ALMOCO.getString()).setObservations(null)
                    .setSugarLevel("130").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.ANTES_JANTAR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("170").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-07").build();
        } else if (option == 4) {
            measures.add(measureBuilder.setInsulin("5")
                    .setSituation(SituationsIndicator.ANTES_CAFE.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("110").build());

            measures.add(measureBuilder.setInsulin("1").setSugarLevel("80")
                    .setSituation(SituationsIndicator.ANTES_ALMOCO.getString())
                    .setObservations("Comi bolacha").build());

            measures.add(measureBuilder.setInsulin("1")
                    .setSituation(SituationsIndicator.ANTES_JANTAR.getString())
                    .setObservations("Medi logo antes de dormir").setSugarLevel("150").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.DEPOIS_JANTAR.getString()).setObservations(null)
                    .setSugarLevel("120").build());

            measures.add(measureBuilder.setInsulin("3")
                    .setSituation(SituationsIndicator.ANTES_DORMIR.getString()).setObservations(null)
                    .setSugarLevel("100").build());

            return dailyMeasuresBuilder.setMeasures(measures).setDate("2020-10-08").build();
        } else {
            return null;
        }

    }

}
