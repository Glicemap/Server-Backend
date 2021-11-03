package com.glicemap.builder;

import com.glicemap.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class PostMeasureBuilder {
    private String documentNumber;
    private String date;
    private MeasureDTO measure;

    public PostMeasureBuilder setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public PostMeasureBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public PostMeasureBuilder setMeasure(MeasureDTO measure) {
        this.measure = measure;
        return this;
    }

    public PostMeasureDTO build() {
        PostMeasureDTO postMeasureDTO = new PostMeasureDTO();
        postMeasureDTO.setDocumentNumber(documentNumber);
        postMeasureDTO.setDate(date);
        postMeasureDTO.setMeasure(measure);
        return postMeasureDTO;
    }
}
