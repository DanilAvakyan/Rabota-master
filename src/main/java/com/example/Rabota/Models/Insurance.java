package com.example.Rabota.Models;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Заполните поле 'Дата начала страхования'")
    private String startDate;

    @NotEmpty(message = "Заполните поле 'Дата окончания страхования'")
    private String endDate;

    @Pattern(regexp = "^[0-9]+$", message = "Сумма страхования может состоять только из цифр")
    private String summ;

    @ManyToOne(optional = true)
    private TypeInsurance typeInsurance;

    @ManyToOne(optional = true)
    private Buyer buyer;

//    @ManyToOne(optional = true)
//    private Cars cars;



    public Insurance() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public TypeInsurance getTypeInsurance() {
        return typeInsurance;
    }
    public void setTypeInsurance(TypeInsurance typeInsurance) {
        this.typeInsurance = typeInsurance;
    }

//    public Cars getTypeCars() {
//        return cars;
//    }
//    public void setCars(Cars cars) {
//        this.cars = cars;
//    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public Insurance(String startDate, String endDate, String summ, TypeInsurance typeInsurance, Buyer buyer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.summ = summ;
        this.typeInsurance = typeInsurance;
        this.buyer = buyer;
    }


}


