package com.example.Rabota.Controller;

import com.example.Rabota.Models.*;
import com.example.Rabota.repo.BuyerRepository;
import com.example.Rabota.repo.CarsRepository;
import com.example.Rabota.repo.InsuranceRepository;
import com.example.Rabota.repo.TypeInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    public TypeInsuranceRepository typeInsuranceRepository;

    @Autowired
    public CarsRepository carsRepository;

    @Autowired
    public BuyerRepository buyerRepository;

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Insurance")
    public String GetRab(Model model) {
        Iterable<Insurance> insurances = insuranceRepository.findAll();
        model.addAttribute("insurances", insurances);
        Iterable<TypeInsurance> typeInsurances = typeInsuranceRepository.findAll();
        model.addAttribute("typeInsurance", typeInsurances);
//        Iterable<Cars> cars = carsRepository.findAll();
//        model.addAttribute("cars", typeInsurances);
        Iterable<Buyer> buyers = buyerRepository.findAll();
        model.addAttribute("buyer", buyers);

        return "MainInsurance";
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Add/Insurance")
    public String GetAddCar(Insurance insurance, Model model) {
        Iterable<TypeInsurance> typeInsurances = typeInsuranceRepository.findAll();
        //Iterable<Cars> cars = carsRepository.findAll();
        ArrayList<TypeInsurance> typeInsuranceArrayList = new ArrayList<>();
        //ArrayList<Cars> carsArrayList = new ArrayList<>();
        Iterable<Buyer> buyers = buyerRepository.findAll();
        ArrayList<Buyer> buyerArrayList = new ArrayList<>();

        for (TypeInsurance typeInsurance : typeInsurances) {
            if (typeInsurance.getNameTypeInsurance() == null) {
                typeInsuranceArrayList.add(typeInsurance);
            }
        }

        model.addAttribute("typeInsurance", typeInsurances);
        model.addAttribute("buyer", buyers);
       // model.addAttribute("cars", cars);
        return "Add-Insurance";


    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Add/Insurance")
    public String blogPostAdd(@ModelAttribute("insurance") @Valid Insurance insurance, BindingResult bindingResult, @RequestParam String nameTypeInsurance, @RequestParam String INN, Model model) {

        insurance.setTypeInsurance(typeInsuranceRepository.findByNameTypeInsurance(nameTypeInsurance));

        insurance.setBuyer(buyerRepository.findByINN(INN));

        //insurance.setCars(carsRepository.findByVIN(VIN));

        if (bindingResult.hasErrors()) {
            return "Add-Insurance";
        }
        insuranceRepository.save(insurance);
        return "redirect:/Insurance";
    }




/////////////////
//    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
//    @GetMapping("/Add/Insurance")
//    public String GetAddVin(Insurance insurance)
//    {
//        return "Add-Insurance";
//    }
//
//    @PostMapping("/Add/Insurance")
//    public String blogKPPAdd(@Valid Insurance insurance, BindingResult bindingResult)
//    {
//        if(bindingResult.hasErrors()){return "Add-Insurance";}
//        insuranceRepository.save(insurance);
//        return "redirect:/Insurance";
//    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Insurance/{id}/Edit")
    public String KPPEdit(@PathVariable(value = "id") Long id, Model model)
    {

        Iterable<Insurance> insurances = insuranceRepository.findAll();
        model.addAttribute("insurances", insurances);
        Iterable<TypeInsurance> typeInsurances = typeInsuranceRepository.findAll();
        //Iterable<Cars> cars = carsRepository.findAll();
        ArrayList<TypeInsurance> typeInsuranceArrayList = new ArrayList<>();
        //ArrayList<Cars> carsArrayList = new ArrayList<>();
        Iterable<Buyer> buyers = buyerRepository.findAll();
        ArrayList<Buyer> buyerArrayList = new ArrayList<>();

        for (TypeInsurance typeInsurance : typeInsurances) {
            if (typeInsurance.getNameTypeInsurance() == null) {
                typeInsuranceArrayList.add(typeInsurance);
            }
        }

        model.addAttribute("typeInsurance", typeInsurances);
        model.addAttribute("buyer", buyers);
        // model.addAttribute("cars", cars);


        return "Edit-insurance";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Insurance/{id}/Edit")
    public String GameUpdate(@ModelAttribute("insurance") @Valid Insurance insurance, BindingResult bindingResult, @RequestParam String nameTypeInsurance, @RequestParam String INN, Model model) {

        insurance.setTypeInsurance(typeInsuranceRepository.findByNameTypeInsurance(nameTypeInsurance));

        insurance.setBuyer(buyerRepository.findByINN(INN));

        //insurance.setCars(carsRepository.findByVIN(VIN));

        if (bindingResult.hasErrors()) {
            return "Add-Insurance";
        }
        insuranceRepository.save(insurance);
        return "redirect:/Insurance";
    }


        @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Insurance/{id}/Remove")
    public String blogPostDelete(
            @PathVariable(value = "id") Long id, Model model) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow();
        insuranceRepository.delete(insurance);
        return "redirect:/Insurance";
    }
}

