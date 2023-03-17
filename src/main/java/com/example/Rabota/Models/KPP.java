package com.example.Rabota.Models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
@Entity
public class KPP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z]+$", message = "Поле вид кпп может состоять только из букв")
    private String typeKpp;

    @Pattern(regexp = "^[0-9.]+$", message = "Поле количество ступеней может состоять только из цифр")
    private String stupeniKpp;

    @OneToMany(mappedBy = "kpp", fetch = FetchType.EAGER)
    private Collection<Cars> stup;


    public Collection<Cars> getStup() {
        return stup;
    }

    public void setStup(Collection<Cars> stup) {
        this.stup = stup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeKpp() {
        return typeKpp;
    }

    public void setTypeKpp(String typeKpp) {
        this.typeKpp = typeKpp;
    }

    public String getStupeniKpp() {
        return stupeniKpp;
    }

    public void setStupeniKpp(String stupeniKpp) {
        this.stupeniKpp = stupeniKpp;
    }

    public KPP(String typeKpp, String stupeniKpp, Collection<Cars> stup) {
        this.typeKpp = typeKpp;
        this.stupeniKpp = stupeniKpp;
        this.stup = stup;
    }

    public KPP() {}

}
