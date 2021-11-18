package com.glicemap.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "RegistroGlicemico")
public class Measure {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "codigo", nullable = false, unique = true)
    private int code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cpf_paciente")
    private User user;

    @Column(name = "data_registro")
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ocasiao")
    private Situation situation;

    @Column(name = "glicemia", nullable = false)
    private int sugarLevel;

    @Column(name = "insulina")
    private int insulin;

    @Column(name = "observacoes", length = 200)
    private String observations;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
