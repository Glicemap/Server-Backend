package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DatesWithMeasuresDTO implements Serializable {
    private static final long serialVersionUID = -6477215503283491105L;
    @JsonProperty("dates")
    private List<String> dates;
}
