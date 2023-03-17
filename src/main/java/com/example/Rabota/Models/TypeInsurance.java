package com.example.Rabota.Models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class TypeInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Заполните поле Тип страхования")
    private String nameTypeInsurance;

    @NotEmpty(message = "Заполните поле Описание")
    private String descriptionTypeInsurance;

    @OneToMany(mappedBy = "typeInsurance", fetch = FetchType.EAGER)
    private Collection<Insurance> tenants;

    public Collection<Insurance> getTenants() {
        return tenants;
    }
    public void setTenants(Collection<Insurance> tenants) {
        this.tenants = tenants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNameTypeInsurance() {
        return nameTypeInsurance;
    }

    public void setNameTypeInsurance(String nameTypeInsurance) {
        this.nameTypeInsurance = nameTypeInsurance;
    }



    public String getDescriptionTypeInsurance() {
        return descriptionTypeInsurance;
    }

    public void setDescriptionTypeInsurance(String descriptionTypeInsurance) {
        this.descriptionTypeInsurance = descriptionTypeInsurance;
    }

    public TypeInsurance(String nameTypeInsurance, String descriptionTypeInsurance) {
        this.nameTypeInsurance = nameTypeInsurance;
        this.descriptionTypeInsurance = descriptionTypeInsurance;
    }

    public TypeInsurance() {}


}
