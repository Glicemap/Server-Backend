package com.glicemap.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "codigo_medico")
public class MedicInvite {
    @Id
    @Column(name = "codigo", nullable = false, length = 6)
    private String code;
    @Column(name = "status_codigo", nullable = false)
    private int status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "crm_medico")
    private Medic medic;

    @Column(name = "data_criacao")
    private Date createdDate;

    MedicInvite() {
    }

    public MedicInvite(String code, int status, Medic medic, Date createdDate) {
        this.code = code;
        this.status = status;
        this.medic = medic;
        this.createdDate = createdDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
