package com.glicemap.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Paciente")
public class User {
    @Id
    @Column(name = "cpf", nullable = false, length = 11)
    private String documentNumber;

    @Column(name = "nome", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "senha", nullable = false, length = 30)
    private String password;

    @Column(name = "data_nascimento", nullable = false)
    private Date birthdate;

    @Column(name = "altura")
    private int height;

    @Column(name = "peso")
    private float weight;

    @Column(name = "glicemia_min", nullable = false)
    private int sugarMin;

    @Column(name = "glicemia_max", nullable = false)
    private int sugarMax;

    @Column(name = "vinculo_medico")
    private Date medicJoin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crm_medic")
    private Medic medic;

    public User(String documentNumber, String name, String email, String password, Date birthdate, int height, float weight, int sugarMin, int sugarMax) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.height = height;
        this.weight = weight;
        this.sugarMin = sugarMin;
        this.sugarMax = sugarMax;
    }

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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

    public Date getMedicJoin() {
        return medicJoin;
    }

    public void setMedicJoin(Date medicJoin) {
        this.medicJoin = medicJoin;
    }
}
