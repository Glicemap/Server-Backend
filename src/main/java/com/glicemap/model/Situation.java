package com.glicemap.model;

import com.glicemap.indicator.SituationsIndicator;

import javax.persistence.*;

@Entity
@Table(name = "Ocasiao")
public class Situation {
    @Id
    @Column(name = "codigo", nullable = false)
    private int code;

    @Column(name = "ocasiao", nullable = false, length = 20)
    private SituationsIndicator situation;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SituationsIndicator getSituation() {
        return situation;
    }

    public void setSituation(SituationsIndicator situation) {
        this.situation = situation;
    }
}
