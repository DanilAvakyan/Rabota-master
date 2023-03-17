package com.example.Rabota.Models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min=2, max=20, message ="Фамилия не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String surnameB;
    @Size(min=2, max=20, message ="Имя не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String nameB;
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String middlenameB;
    @Min(value = 1957, message = "Минимальный год рождения - 1957 (65 лет)")
    @Max(value = 2006, message = "Минимальный год рождения - 2006 (16 лет)")
    @Pattern(regexp = "^[0-9]+$", message = "Год рождения может состоять только из цифр")
    private String bthdayB;

    @NotEmpty(message = "Заполните поле Адрес регистрации")
    private String AdresRegB;
    @NotEmpty(message = "Заполните поле Email")
    @Email(message = "Электронная почта должна включать в себя символы @ и .")
    private String EmailB;

    @Pattern(regexp = "^[0-9]+$", message = "инн может состоять только из цифр")
    @Size(min=17, max=17, message ="ИНН - 17 ")
    private String INN;

    @Pattern(regexp = "^[0-9]+$", message = "Серия паспорта может состоять только из цифр")
    @Size(min=4, max=4, message ="Серия паспорта - 4")
    private String SeriaPass;

    @Pattern(regexp = "^[0-9]+$", message = "Серия паспорта может состоять только из цифр")
    @Size(min=6, max=6, message ="Номер пасспорта")
    private String NomPass;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Collection<DKP> buy;

    public Collection<DKP> getBuy() {
        return buy;
    }
    public void setBuy(Collection<DKP> buy) {
        this.buy = buy;
    }

    public Buyer() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurnameB() {
        return surnameB;
    }

    public void setSurnameB(String surnameB) {
        this.surnameB = surnameB;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getMiddlenameB() {
        return middlenameB;
    }

    public void setMiddlenameB(String middlenameB) {
        this.middlenameB = middlenameB;
    }

    public String getBthdayB() {
        return bthdayB;
    }

    public void setBthdayB(String bthdayB) {
        this.bthdayB = bthdayB;
    }

    public String getAdresRegB() {
        return AdresRegB;
    }

    public void setAdresRegB(String adresRegB) {
        AdresRegB = adresRegB;
    }

    public String getEmailB() {
        return EmailB;
    }

    public void setEmailB(String emailB) {
        EmailB = emailB;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public String getSeriaPass() {
        return SeriaPass;
    }

    public void setSeriaPass(String seriaPass) {
        SeriaPass = seriaPass;
    }

    public String getNomPass() {
        return NomPass;
    }

    public void setNomPass(String nomPass) {
        NomPass = nomPass;
    }

    public Buyer(String surnameB, String nameB, String middlenameB, String bthdayB, String adresRegB, String emailB, String INN, String seriaPass, String nomPass) {
        this.surnameB = surnameB;
        this.nameB = nameB;
        this.middlenameB = middlenameB;
        this.bthdayB = bthdayB;
        this.AdresRegB = adresRegB;
        this.EmailB = emailB;
        this.INN = INN;
        this.SeriaPass = seriaPass;
        this.NomPass = nomPass;
    }
}
