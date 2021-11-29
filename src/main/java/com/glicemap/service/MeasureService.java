package com.glicemap.service;

import com.glicemap.builder.DailyMeasuresBuilder;
import com.glicemap.builder.DatesWithMeasuresBuilder;
import com.glicemap.builder.MeasureBuilder;
import com.glicemap.builder.PatientMeasuresInfoBuilder;
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

@SuppressWarnings("ALL")
@Service
public class MeasureService {
    final Logger logger = LoggerFactory.getLogger(MeasureService.class);

    @Autowired
    private DatesWithMeasuresBuilder datesWithMeasuresBuilder;

    @Autowired
    private DailyMeasuresBuilder dailyMeasuresBuilder;

    @Autowired
    private PatientMeasuresInfoBuilder patientMeasuresInfoBuilder;

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
            if (!listDates.contains(measure.getCreatedDate().toString())) {
                listDates.add(this.dateToString(measure.getCreatedDate()));
            }
        }

        return datesWithMeasuresBuilder.setDates(listDates).build();
    }

    public DailyMeasuresDTO getDailyMeasures(String documentNumber, String date) throws ParseException {
        User user = userService.getUser(documentNumber);

        List<Measure> measures = measureRepository.findByDate(user, this.stringToDate(date));

        List<MeasureDTO> measuresDTOS = measureBuilder.buildModelList(measures);

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
        User user = userService.getUser(documentNumber);

        Date dateFrom;
        Date dateTo;

        if (dateBegin.isEmpty()) {
            Calendar c = Calendar.getInstance();
            c.setTime(this.stringToDate(dateEnd));
            c.add(Calendar.MONTH, -1);
            dateFrom = new Date(c.getTime().getTime());
        } else {
            dateFrom = this.stringToDate(dateBegin);
        }

        if (dateEnd.isEmpty()) {
            dateTo = new Date(new java.util.Date(System.currentTimeMillis()).getTime());
        } else {
            dateTo = this.stringToDate(dateEnd);
        }

        List<Measure> measures = measureRepository.findByDateInterval(user, dateFrom, dateTo);

        return dailyMeasuresBuilder.buildModelList(measures);
    }

    public PercentagesDTO getMeasuresPercentageFromLastMonth(User user) {
        float countDaysWithMeasure = 0;
        float countDaysWithMeasureRight = 0;
        java.util.Date endDate = new java.util.Date(System.currentTimeMillis());

        // pega dia de um mes atras
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.MONTH, -1);
        java.util.Date startDate = c.getTime();

        //busca todos os dias
        List<Measure> measures = measureRepository.findByDateInterval(user, new Date(startDate.getTime()), new Date(endDate.getTime()));

        //conta os dias com medicao sem repetir
        List<String> listDates = new ArrayList<>();
        for (Measure measure : measures) {
            if (!listDates.contains(measure.getCreatedDate().toString())) {
                listDates.add(measure.getCreatedDate().toString());
                countDaysWithMeasure++;
            }
            if (measure.getSugarLevel() <= user.getSugarMax() && measure.getSugarLevel() >= user.getSugarMin()) {
                countDaysWithMeasureRight++;
            }
        }

        PercentagesDTO percentages = new PercentagesDTO();

        percentages.setPercentageTotal(Math.round((countDaysWithMeasure / 30) * 100));

        percentages.setPercentageRight(Math.round((countDaysWithMeasureRight / measures.size()) * 100));

        return percentages;
    }

    private Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateUtil = sdf.parse(dateString);
        return new Date(dateUtil.getTime());
    }

    private String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public PatientMeasuresInfoDTO getMeasuresInfo(String documentNumber, GetPatientDTO getPatientDTO) throws ParseException {
        User user = userService.getUser(documentNumber);
        Date dateFrom;
        Date dateTo;

        if (getPatientDTO.getFrom() == null) {
            Calendar c = Calendar.getInstance();
            c.setTime(new java.util.Date(System.currentTimeMillis()));
            c.add(Calendar.MONTH, -1);
            dateFrom = new Date(c.getTime().getTime());
        } else {
            dateFrom = this.stringToDate(getPatientDTO.getFrom());
        }

        if (getPatientDTO.getTo() == null) {
            dateTo = new Date(new java.util.Date(System.currentTimeMillis()).getTime());
        } else {
            dateTo = this.stringToDate(getPatientDTO.getTo());
        }

        List<Measure> measures = measureRepository.findByDateInterval(user, dateFrom, dateTo);

        List<DailyMeasuresDTO> dailyMeasureDTOList = dailyMeasuresBuilder.buildModelList(measures);

        return patientMeasuresInfoBuilder.setName(user.getFullName())
                .setMeasures(dailyMeasureDTOList)
                .setFrequencys(this.getFrequencyChart(measures))
                .setLow((this.getDaysWithFrequency(dailyMeasureDTOList, 0)))
                .setMidlow((this.getDaysWithFrequency(dailyMeasureDTOList, 1)))
                .setMidhigh((this.getDaysWithFrequency(dailyMeasureDTOList, 2)))
                .setHigh((this.getDaysWithFrequency(dailyMeasureDTOList, 3)))
                .build();
    }

    private List<String> getDaysWithFrequency(List<DailyMeasuresDTO> dailyMeasureList, int level) {
        int[][] levels = {{0, 1}, {2, 2}, {3, 3}, {4, 5000}};
        List<String> dates = new ArrayList<>();

        for (DailyMeasuresDTO dailyMeasure : dailyMeasureList) {
            if (dailyMeasure.getMeasures().size() >= levels[level][0] && dailyMeasure.getMeasures().size() <= levels[level][1]) {
                dates.add(dailyMeasure.getDate());
            }
        }

        return dates;
    }


    private List<String> getFrequencyChart(List<Measure> measures) {
        int[] frequencysInt = {0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (Measure measure : measures) {
            final int sugarLevel = measure.getSugarLevel();
            if (sugarLevel <= 50) {
                frequencysInt[0]++;
            } else if (sugarLevel <= 100) {
                frequencysInt[1]++;
            } else if (sugarLevel <= 150) {
                frequencysInt[2]++;
            } else if (sugarLevel <= 200) {
                frequencysInt[3]++;
            } else if (sugarLevel <= 250) {
                frequencysInt[4]++;
            } else if (sugarLevel <= 300) {
                frequencysInt[5]++;
            } else if (sugarLevel <= 350) {
                frequencysInt[6]++;
            } else if (sugarLevel <= 400) {
                frequencysInt[7]++;
            } else {
                frequencysInt[8]++;
            }
        }

        List<String> frequencysString = new ArrayList<>();

        for (int value : frequencysInt) {
            frequencysString.add(Integer.toString(value));
        }

        return frequencysString;
    }

}
