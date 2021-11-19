package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDTO implements Serializable {

    private static final long serialVersionUID = -2350413444333075901L;

    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("read")
    private Boolean read;
}
