package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z0-9]+$", message = "В виде покрытия могут использоваться английские, русские буквы и цифры")
    private String typeСoating;

    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z0-9]+$", message = "В цвете могут использоваться английские, русские буквы и цифры")
    private String colourrr;

    @OneToMany(mappedBy = "color", fetch = FetchType.EAGER)
    private Collection<Cars> tsvet;

    public Collection<Cars> getTsvet() {
        return tsvet;
    }
    public void setTsvet(Collection<Cars> tsvet) {
        this.tsvet = tsvet;
    }



    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getColourrr() {
        return colourrr;
    }

    public void setColourrr(String colourrr) {
        this.colourrr = colourrr;
    }

    public String getTypeСoating() {
        return typeСoating;
    }

    public void setTypeСoating(String typeСoating) {
        this.typeСoating = typeСoating;
    }

    public Color() {
    }

    public Color(String typeСoating, String colourrr, Collection<Cars> tsvet) {
        this.typeСoating = typeСoating;
        this.colourrr = colourrr;
        this.tsvet = tsvet;
    }
}
