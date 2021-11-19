package com.glicemap.model;

import javax.persistence.*;

@Entity
@Table(name = "notificacao")
public class Notification {
    @Id
    @Column(name = "codigo", nullable = false)
    private int code;

    @Column(name = "status_notificacao", nullable = false)
    private int status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "crm_medico")
    private Medic medic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cpf_paciente")
    private User user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
