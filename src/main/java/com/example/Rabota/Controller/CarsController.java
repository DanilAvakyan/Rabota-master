package com.example.Rabota.Controller;
import com.example.Rabota.Models.*;

import com.example.Rabota.repo.CarsRepository;
import com.example.Rabota.repo.ColorRepository;
import com.example.Rabota.repo.EngineRepository;

import com.example.Rabota.repo.KppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CarsController {
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    public EngineRepository engineRepository;
    @Autowired
    public ColorRepository colorRepository;
    @Autowired
    public KppRepository kppRepository;



    @GetMapping("/")
    public String GetRab(Model model)
    {
        Iterable<Engine> engines = engineRepository.findAll();
        Iterable<Color> colors = colorRepository.findAll();
        Iterable<Cars> cars = carsRepository.findAll();
        Iterable<KPP> kpps = kppRepository.findAll();
        model.addAttribute("cars",cars);
        model.addAttribute("engine", engines);
        model.addAttribute("color", colors);
        model.addAttribute("kpp", kpps);
        return "Main";
    }


    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Add/Cars")
    public String GetAddCar(Cars cars, Model model)
    {
        Iterable<Engine> engines=engineRepository.findAll();
        ArrayList<Engine> engineArrayList=new ArrayList<>();

        Iterable<Color> colors=colorRepository.findAll();
        ArrayList<Color> colorsArrayList=new ArrayList<>();

        Iterable<KPP> kpps=kppRepository.findAll();
        ArrayList<KPP> kppsArrayList=new ArrayList<>();

        for (Engine engine: engines)
        {
            if (engine.getEngineCapacity()==null)
            {
                engineArrayList.add(engine);
            }
        }
        model.addAttribute("engine", engines);
        model.addAttribute("color", colors);
        model.addAttribute("kpps", kpps);
        return "Add-Cars";
    }


    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Add/Cars")
    public String blogPostAdd(@ModelAttribute("cars")@Valid Cars cars, BindingResult bindingResult, @RequestParam String colourrr, @RequestParam String horsepower, @RequestParam String typeKpp,  Model model)
    {

        cars.setEngine(engineRepository.findByHorsepower(horsepower));
        cars.setColor(colorRepository.findByColourrr(colourrr));
        cars.setKpp(kppRepository.findByTypeKpp(typeKpp));
        if(bindingResult.hasErrors())
        {
            return "Add-Cars";
        }
        carsRepository.save(cars);
        return "redirect:/";
    }


    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping( path = "/Search/Cars")
    public String blogFilter(Cars cars)
    {
        return "Search-Cars";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Search/Cars-result")
    public String blogResult(@RequestParam String carsmodel, Model model)
    {
        List<Cars> cars = carsRepository.findByCarsmodel(carsmodel);
        model.addAttribute("result", cars);
        return "Search-Cars";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Search/Cars")
    public String simpleSearch(@RequestParam String carsmodel, Model model)
    {
        List<Cars> cars = carsRepository.findByCarsmodelContains(carsmodel);
        model.addAttribute("result", cars);
        return "Search-Cars";
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping ("/blog/Cars/{id}/Edit")
    public  String productEdit(@PathVariable("Cars") long Cars, Model model)
    {
        Cars res = carsRepository.findById(Cars).orElseThrow();
        model.addAttribute("cars1",res);

        Iterable<Engine> engines = engineRepository.findAll();
        Iterable<Color> colors = colorRepository.findAll();
       Iterable<Cars> cars = carsRepository.findAll();
        Iterable<KPP> kpps = kppRepository.findAll();
        model.addAttribute("cars",cars);
        model.addAttribute("engine", engines);
        model.addAttribute("color", colors);
        model.addAttribute("kpp", kpps);

        return "Edit-cars";
    }

    @PostMapping ("/blog/Cars/{id}/Edit")
    public  String productUpdate(@ModelAttribute("cars") @Valid Cars cars1, BindingResult bindingResult,
                                 @PathVariable("Cars")long Cars,
                                 @RequestParam String colourrr,
                                 @RequestParam String horsepower,
                                 @RequestParam String typeKpp,
                                 Model model)
    {
        if(bindingResult.hasErrors()) {

            Iterable<Engine> engines = engineRepository.findAll();
            Iterable<Color> colors = colorRepository.findAll();
//        Iterable<Cars> cars = carsRepository.findAll();
            Iterable<KPP> kpps = kppRepository.findAll();
//        model.addAttribute("cars",cars);
            model.addAttribute("engine", engines);
            model.addAttribute("color", colors);
            model.addAttribute("kpp", kpps);

            return "Edit-cars";
        }
        cars1.setEngine(engineRepository.findByHorsepower(colourrr));
        cars1.setColor(colorRepository.findByColourrr(horsepower));
        cars1.setKpp(kppRepository.findByTypeKpp(typeKpp));
        carsRepository.save(cars1);
        return "redirect:/";
    }

//    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
//    @GetMapping("/blog/Cars/{id}/Edit")
//    public String CarEdit(@PathVariable(value = "id") Long id, Model model)
//    {
//        Iterable<Engine> engines = engineRepository.findAll();
//        Iterable<Color> colors = colorRepository.findAll();
//        Iterable<Cars> cars = carsRepository.findAll();
//        Iterable<KPP> kpps = kppRepository.findAll();
//        model.addAttribute("cars",cars);
//        model.addAttribute("engine", engines);
//        model.addAttribute("color", colors);
//        model.addAttribute("kpp", kpps);
//
//        Cars cars = carsRepository.findById(id).orElseThrow(()
//                ->new IllegalArgumentException("Invalid car Id" + id));
//        model.addAttribute("cars",cars);
//        return "Edit-cars";
//    }
//    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
//    @PostMapping("/blog/Cars/{id}/Edit")
//    public String GameUpdate(@ModelAttribute("cars") @Valid Cars cars, BindingResult bindingResult)
//    {if (bindingResult.hasErrors()) return "Edit-cars";    carsRepository.save(cars);    return "redirect:/";}

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/blog/Cars/{id}/Remove")
    public String blogCarDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Cars cars = carsRepository.findById(id).orElseThrow();
        carsRepository.delete(cars);
        return "redirect:/";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/blog/Cars/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Cars> cars = carsRepository.findById(id);
        ArrayList<Cars> lis = new ArrayList<>();
        cars.ifPresent(lis::add);
        model.addAttribute("Cars", lis);
        return "blog-CarsDetails";
    }
}