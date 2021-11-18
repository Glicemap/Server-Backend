package com.glicemap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medic {
    @Id
    @Column(name = "crm", nullable = false, length = 20)
    private String CRM;

    @Column(name = "nome", nullable = false, length = 60)
    private String name;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "senha", nullable = false, length = 30)
    private String password;

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
