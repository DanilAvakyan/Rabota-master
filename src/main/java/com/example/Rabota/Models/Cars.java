package com.example.Rabota.Models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Table(name = "avto")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Заполните поле Марка")
    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z0-9]+$", message = " В марке используйте английские, русские буквы и цифры")
    private String carbrand;
    @NotEmpty(message = "Заполните поле Модель")
    private String carsmodel;
    @NotEmpty(message = "Заполните поле Год выпуска")
    @Pattern(regexp = "^[0-9]+$", message = "Год выпуска может состоять только из цифр")
    @Min(value = 1885, message = "Минимальный год выпуска - 1885")
    @Max(value = 2022, message = "Максимальный год выпуска - 2022")
    private String year_release;

    @Size(min=17, max=17, message ="VIN должен состоять из 17 символов")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Разрешены только латинские буквы и цифры")
    private String VIN;


    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Engine engine;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Color color;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private KPP kpp;


    public Cars(String carbrand, Engine engine1) {
    }

    public Cars() {
    }


    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Cars(String carsmodel, String horsepower, String colourrr) {

    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }






    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;}

    public String getCarbrand() {return carbrand;}

    public void setCarbrand(String carbrand) {this.carbrand = carbrand;}

    public String getCarsmodel() {return carsmodel;}

    public void setCarsmodel(String carsmodel) {this.carsmodel = carsmodel;}

    public String getYear_release() {return year_release;}

    public void setYear_release(String year_release) {this.year_release = year_release;}




    public Cars(String carbrand, String carsmodel, String year_release, String VIN, Engine engine, Color color, KPP kpp) {
        this.carbrand = carbrand;
        this.carsmodel = carsmodel;
        this.year_release = year_release;
        this.VIN = VIN;
        this.engine = engine;
        this.color = color;
        this.kpp = kpp;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public KPP getKpp() {
        return kpp;
    }

    public void setKpp(KPP kpp) {
        this.kpp = kpp;
    }

    public Cars(KPP kpp) {
        this.kpp = kpp;
    }
}
