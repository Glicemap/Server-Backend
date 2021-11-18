package com.glicemap.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "CodigoMedico")
public class MedicInvite {
    @Id
    @Column(name = "codigo", nullable = false, length = 6)
    private String code;

    @Column(name = "status_codigo", nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "crm_medic")
    private Medic medic;

    @Column(name = "data_criacao")
    private Date createdDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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
