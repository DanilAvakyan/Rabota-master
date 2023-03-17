package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Заполните поле Наименование автосалона")
    private String nameSalon;

    @NotEmpty(message = "Заполните поле Адрес автосалона")
    private String adresSalon;

    @Pattern(regexp = "^[0-9]+$", message = "инн может состоять только из цифр")
    @Size(min=10, max=10, message ="ИНН - 10 ")
    private String InnSalon;

    @Pattern(regexp = "^[0-9]+$", message = "инн может состоять только из цифр")
    @Size(min=20, max=20, message ="Банковский счет - 20 ")
    private String nomBank;

    @OneToMany(mappedBy = "salon", fetch = FetchType.EAGER)
    private Collection<DKP> sal;

    public Collection<DKP> getSal() {
        return sal;
    }
    public void setSal(Collection<DKP> sal) {
        this.sal = sal;
    }

    public Salon(String nameSalon, String adresSalon, String innSalon, String nomBank) {
        this.nameSalon = nameSalon;
        this.adresSalon = adresSalon;
        this.InnSalon = innSalon;
        this.nomBank = nomBank;
    }

    public Salon() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSalon() {
        return nameSalon;
    }

    public void setNameSalon(String nameSalon) {
        this.nameSalon = nameSalon;
    }

    public String getAdresSalon() {
        return adresSalon;
    }

    public void setAdresSalon(String adresSalon) {
        this.adresSalon = adresSalon;
    }

    public String getInnSalon() {
        return InnSalon;
    }

    public void setInnSalon(String innSalon) {
        InnSalon = innSalon;
    }

    public String getNomBank() {
        return nomBank;
    }

    public void setNomBank(String nomBank) {
        this.nomBank = nomBank;
    }
}
