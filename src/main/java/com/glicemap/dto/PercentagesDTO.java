package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PercentagesDTO implements Serializable {

    private static final long serialVersionUID = -4271543935798313706L;

    @JsonProperty("percentageRight")
    private int percentageRight;

    @JsonProperty("percentageTotal")
    private int percentageTotal;
}
