package com.glicemap.builder;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.MeasureDTO;
import com.glicemap.model.Measure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
public class DailyMeasuresBuilder {
    @Autowired
    private MeasureBuilder measureBuilder;

    private List<MeasureDTO> measures;
    private String date;

    public DailyMeasuresBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public DailyMeasuresBuilder setMeasures(List<MeasureDTO> measures) {
        this.measures = measures;
        return this;
    }

    public DailyMeasuresDTO build() {
        DailyMeasuresDTO dailyMeasuresDTO = new DailyMeasuresDTO();
        dailyMeasuresDTO.setMeasures(this.measures);
        dailyMeasuresDTO.setDate(this.date);
        return dailyMeasuresDTO;
    }

    public List<DailyMeasuresDTO> buildModelList(List<Measure> measures) throws ParseException {
        HashMap<Date, List<MeasureDTO>> dailyMeasures = new HashMap<>();
        List<DailyMeasuresDTO> dailyMeasuresListDTO = new ArrayList<>();

        for(Measure measure : measures){
            dailyMeasures.putIfAbsent(measure.getCreatedDate(), new ArrayList<>());
            dailyMeasures.get(measure.getCreatedDate()).add(measureBuilder.buildModel(measure));
        }

        for (Date date : dailyMeasures.keySet()){
            DailyMeasuresDTO dailyMeasuresDTO = new DailyMeasuresDTO();
            dailyMeasuresDTO.setDate(this.dateToString(date));
            dailyMeasuresDTO.setMeasures(dailyMeasures.get(date));
            dailyMeasuresListDTO.add(dailyMeasuresDTO);
        }

        Collections.sort(dailyMeasuresListDTO);
        Collections.reverse(dailyMeasuresListDTO);

        return dailyMeasuresListDTO;
    }

    private String dateToString(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
}
