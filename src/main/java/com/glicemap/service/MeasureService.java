package com.glicemap.service;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.DatesWithMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.dto.*;
import com.glicemap.model.Measure;
import com.glicemap.model.User;
import com.glicemap.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MeasureService {
    @Autowired
    private DatesWithMeasuresBuilder datesWithMeasuresBuilder;

    @Autowired
    private DailyMeasuresBuilder dailyMeasuresBuilder;

    @Autowired
    private MeasureBuilder measureBuilder;

    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SituationService situationService;

    public DatesWithMeasuresDTO getDaysWithMeasure(String documentNumber, String date) throws ParseException {
        List<Measure> measures = measureRepository.findByMonth(documentNumber, this.stringToDate(date));
        List<String> listDates = new ArrayList<>();

        for(Measure measure : measures){
            listDates.add(measure.getCreatedDate().toString());
        }

        return datesWithMeasuresBuilder.setDates(listDates).build();
    }

    public DailyMeasuresDTO getDailyMeasures(String documentNumber, String date) throws ParseException {
        List<Measure> measures = measureRepository.findByDate(documentNumber, this.stringToDate(date));

        List<MeasureDTO> measuresDTOS = new ArrayList<>();

        for (Measure measure : measures){
            MeasureDTO measureDTO = measureBuilder.setInsulin(Integer.toString(measure.getInsulin()))
                                                  .setObservations(measure.getObservations())
                                                  .setSituation(measure.getSituation().getSituation().getString())
                                                  .setSugarLevel(Integer.toString(measure.getSugarLevel()))
                                                  .build();
            measuresDTOS.add(measureDTO);
        }

        return dailyMeasuresBuilder.setMeasures(measuresDTOS).setDate(date).build();
    }

    public Boolean postMeasure(PostMeasureDTO postMeasureDTO) throws ParseException {
        User user = userService.getUser(postMeasureDTO.getDocumentNumber());

        Measure measure = new Measure();
        measure.setCreatedDate(this.stringToDate(postMeasureDTO.getDate()));
        measure.setUser(user);
        measure.setInsulin(Integer.parseInt(postMeasureDTO.getMeasure().getInsulin()));
        measure.setObservations(postMeasureDTO.getMeasure().getObservations());
        measure.setSituation(situationService.getSituationBySituation(postMeasureDTO.getMeasure().getSituation()));
        measure.setSugarLevel(Integer.parseInt(postMeasureDTO.getMeasure().getSugarLevel()));
        measureRepository.save(measure);

        return Boolean.TRUE;
    }

    public List<DailyMeasuresDTO> getMeasuresFromInterval(String documentNumber, String dateBegin, String dateEnd) throws ParseException {
        List<DailyMeasuresDTO> dailyMeasuresList = new ArrayList<>();
        List<java.util.Date> dates = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date endDate = sdf.parse(dateEnd);
        java.util.Date startDate = sdf.parse(dateBegin);

        while (!startDate.after(endDate)) {
            dates.add(startDate);
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            c.add(Calendar.DATE, 1);
            startDate = c.getTime();
        }

        for (java.util.Date date : dates){
            DailyMeasuresDTO dailyMeasures = new DailyMeasuresDTO();
            dailyMeasures.setDate(date.toString());

            List<Measure> measures = measureRepository.findByDate(documentNumber, new Date(date.getTime()));

            List<MeasureDTO> measuresDTO = new ArrayList<>();

            for (Measure measure : measures){
                MeasureDTO measureDTO = measureBuilder.setInsulin(Integer.toString(measure.getInsulin()))
                        .setObservations(measure.getObservations())
                        .setSituation(measure.getSituation().getSituation().getString())
                        .setSugarLevel(Integer.toString(measure.getSugarLevel()))
                        .build();

                measuresDTO.add(measureDTO);
            }

            dailyMeasures.setMeasures(measuresDTO);
        }

        return dailyMeasuresList;
    }

    private Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date dateUtil = sdf.parse(dateString);
        return new Date(dateUtil.getTime());
    }

    public PatientMeasuresInfoDTO getMeasuresInfo(GetPatientDTO getPatientDTO){
        return new PatientMeasuresInfoDTO();
    }

}
