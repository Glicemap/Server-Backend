package com.glicemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NotificationsIdsDTO implements Serializable {

    private static final long serialVersionUID = -1768383587000578325L;

    @JsonProperty("ids")
    private List<String> ids;
}
