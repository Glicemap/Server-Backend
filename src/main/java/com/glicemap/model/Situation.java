package com.glicemap.model;

import com.glicemap.indicator.SituationsIndicator;

import javax.persistence.*;

@Entity
@Table(name = "ocasiao")
public class Situation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private int code;

    @Column(name = "ocasiao", nullable = false, length = 40)
    private String situation;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
