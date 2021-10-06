package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostMeasureDTO implements Serializable {

    private static final long serialVersionUID = -1838808279217676231L;
    @JsonProperty("documentNumber")
    private String documentNumber;
    @JsonProperty("date")
    private String date;
    @JsonProperty("measure")
    private MeasureDTO measure;

}
