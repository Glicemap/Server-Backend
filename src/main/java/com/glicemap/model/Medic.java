package com.glicemap.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// @Table(name = "medic")
public class Medic {
    @Id
    private String CRM;

    private String name;

    private String email;

    private String password;

    private String birthdate;

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

}
