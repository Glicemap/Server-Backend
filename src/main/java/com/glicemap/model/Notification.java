package com.glicemap.model;

import javax.persistence.*;

@Entity
@Table(name = "notificacao")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private int code;

    @Column(name = "status_notificacao", nullable = false)
    private boolean status;

    @Column(name = "lido", nullable = false)
    private boolean read; // true - lido / false - nao lido

    @Column(name = "tipo", nullable = false)
    private boolean type; // true - vinculou / false - desvinculou

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "crm_medico")
    private Medic medic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cpf_paciente")
    private User user;

    Notification(){

    }

    public Notification(Medic medic, User user, boolean type) {
        this.status = true;
        this.read = false;
        this.medic = medic;
        this.user = user;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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

    public boolean isStatus() {
        return status;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
