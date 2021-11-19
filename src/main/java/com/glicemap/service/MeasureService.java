package com.glicemap.service;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.DatesWithMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.dto.*;
import com.glicemap.exception.BaseBusinessException;
import com.glicemap.model.Measure;
import com.glicemap.model.User;
import com.glicemap.repository.MeasureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(MeasureService.class);

    @Autowired
    private DatesWithMeasuresBuilder datesWithMeasuresBuilder;

    @Autowired
    private DailyMeasuresBuilder dailyMeasuresBuilder;

    @Autowired
    private MeasureBuilder measureBuilder;

    @Autowired
    private UserService userService;

    @Autowired
    private SituationService situationService;

    @Autowired
    private MeasureRepository measureRepository;

    public DatesWithMeasuresDTO getDaysWithMeasure(String documentNumber, String date) throws ParseException {
        User user = userService.getUser(documentNumber);
        List<Measure> measures = measureRepository.findByMonth(user, this.stringToDate(date));
        List<String> listDates = new ArrayList<>();

        for (Measure measure : measures) {
            listDates.add(measure.getCreatedDate().toString());
        }

        return datesWithMeasuresBuilder.setDates(listDates).build();
    }

    public DailyMeasuresDTO getDailyMeasures(String documentNumber, String date) throws ParseException {
        User user = userService.getUser(documentNumber);
        List<Measure> measures = measureRepository.findByDate(user, this.stringToDate(date));

        List<MeasureDTO> measuresDTOS = new ArrayList<>();

        for (Measure measure : measures) {
            MeasureDTO measureDTO = measureBuilder.setInsulin(Integer.toString(measure.getInsulin()))
                    .setObservations(measure.getObservations())
                    .setSituation(measure.getSituation().getSituation())
                    .setSugarLevel(Integer.toString(measure.getSugarLevel()))
                    .build();
            measuresDTOS.add(measureDTO);
        }

        return dailyMeasuresBuilder.setMeasures(measuresDTOS).setDate(date).build();
    }

    public Boolean postMeasure(PostMeasureDTO postMeasureDTO) throws ParseException {
        User user = userService.getUser(postMeasureDTO.getDocumentNumber());

        if (user == null) {
            logger.error("MeasureService - postMeasure Error - User doesn't exists - DocumentNumber [{}]", postMeasureDTO.getDocumentNumber());
            throw new BaseBusinessException("POST_MEASURE_ERROR_0001");
        }

        MeasureDTO measureDTO = postMeasureDTO.getMeasure();

        Measure measure = new Measure(user, //
                this.stringToDate(postMeasureDTO.getDate()), //
                situationService.getSituationBySituation(measureDTO.getSituation()), //
                Integer.parseInt(measureDTO.getSugarLevel()), //
                Integer.parseInt(measureDTO.getInsulin()), //
                measureDTO.getObservations()); //

        measureRepository.save(measure);

        return Boolean.TRUE;
    }

    public List<DailyMeasuresDTO> getMeasuresFromInterval(String documentNumber, String dateBegin, String dateEnd) throws ParseException {
        List<DailyMeasuresDTO> dailyMeasuresList = new ArrayList<>();
        List<java.util.Date> dates = new ArrayList<>();
        User user = userService.getUser(documentNumber);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date endDate = sdf.parse(dateEnd);
        java.util.Date startDate = sdf.parse(dateBegin);

        while (!startDate.after(endDate)) {
            dates.add(startDate);
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            c.add(Calendar.DATE, 1);
            startDate = c.getTime();
        }

        for (java.util.Date date : dates) {
            DailyMeasuresDTO dailyMeasures = new DailyMeasuresDTO();
            dailyMeasures.setDate(sdf.format(date));

            List<Measure> measures = measureRepository.findByDate(user, new Date(date.getTime()));

            List<MeasureDTO> measuresDTO = new ArrayList<>();

            for (Measure measure : measures) {
                MeasureDTO measureDTO = measureBuilder.setInsulin(Integer.toString(measure.getInsulin()))
                        .setObservations(measure.getObservations())
                        .setSituation(measure.getSituation().getSituation())
                        .setSugarLevel(Integer.toString(measure.getSugarLevel()))
                        .build();

                measuresDTO.add(measureDTO);
            }
            dailyMeasures.setMeasures(measuresDTO);
            dailyMeasuresList.add(dailyMeasures);
        }

        return dailyMeasuresList;
    }

    private Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateUtil = sdf.parse(dateString);
        return new Date(dateUtil.getTime());
    }

    public PatientMeasuresInfoDTO getMeasuresInfo(GetPatientDTO getPatientDTO) {
        return new PatientMeasuresInfoDTO();
    }

}
