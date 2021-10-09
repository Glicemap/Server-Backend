package com.glicemap.model;

import javax.persistence.*;

@Entity
// @Table(name = "user")
public class User {
    @Id
    private String documentNumber;

    private String name;

    private String email;

    private String password;

    private String birthdate;

    private int height;

    private float weight;

    private int sugarMin;

    private int sugarMax;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CRM_Medic", nullable = true)
    private Medic medic;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSugarMin() {
        return sugarMin;
    }

    public void setSugarMin(int sugarMin) {
        this.sugarMin = sugarMin;
    }

    public int getSugarMax() {
        return sugarMax;
    }

    public void setSugarMax(int sugarMax) {
        this.sugarMax = sugarMax;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }
}
