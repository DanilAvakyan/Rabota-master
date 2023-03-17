package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class DKP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Заполните поле Дата составления")
    private String dataSostav;

    @NotEmpty(message = "Заполните поле Сумма покупки")
    private String SummaPokupki;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Buyer buyer;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Salon salon;

//    @ManyToOne(optional = true, cascade = CascadeType.ALL)
//    private Engine cars;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Manager manager;


    public DKP(String dataSostav, String summaPokupki, Buyer buyer, Salon salon, Manager manager) {
        this.dataSostav = dataSostav;
        this.SummaPokupki = summaPokupki;
        this.buyer = buyer;
        this.salon = salon;
       // this.cars = cars;
        this.manager = manager;
    }

    public DKP() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataSostav() {
        return dataSostav;
    }

    public void setDataSostav(String dataSostav) {
        this.dataSostav = dataSostav;
    }

    public String getSummaPokupki() {
        return SummaPokupki;
    }

    public void setSummaPokupki(String summaPokupki) {
        SummaPokupki = summaPokupki;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }




    public void setSalon(List<Salon> byNameSalon) {
    }

    public void setManager(List<Manager> bySurname) {
    }

    public void setBuyer(List<Buyer> bySurnameB) {
    }


}
