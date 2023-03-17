package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Postavka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;


    @Pattern(regexp ="^[а-яА-ЯёЁ]+$", message = "Наименование должноссостоять только из кириллицы")
    private String strana;

    @NotEmpty(message = "Заполните поле компания")
    private String company;

    public Postavka() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStrana() {
        return strana;
    }

    public void setStrana(String strana) {
        this.strana = strana;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Postavka(String strana, String company) {
        this.strana = strana;
        this.company = company;
    }
}
