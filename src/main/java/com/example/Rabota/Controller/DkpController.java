package com.example.Rabota.Controller;

import com.example.Rabota.Models.*;

import com.example.Rabota.repo.*;

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
public class DkpController {
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    public BuyerRepository buyerRepository;
    @Autowired
    public SalonRepository salonRepository;
    @Autowired
    public ManagerRepository managerRepository;

    @Autowired
    private DkpRepository dkpRepository;


    @GetMapping("/Dkp")
    public String GetRab(Model model) {
//        Iterable<Cars> cars1 = carsRepository.findAll();
        Iterable<Buyer> buyers = buyerRepository.findAll();
        Iterable<Salon> salons = salonRepository.findAll();
        Iterable<Manager> managers = managerRepository.findAll();
        Iterable<DKP> dkp1 = dkpRepository.findAll();
       // model.addAttribute("cars1", cars1);
        model.addAttribute("buyers", buyers);
        model.addAttribute("salons", salons);
        model.addAttribute("managers", managers);
        model.addAttribute("dkp1", dkp1);
        return "MainDKP";
    }


    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Add/Dkp")
    public String GetAddCar (@ModelAttribute("dkp") DKP dkp, Model model) {
//
//
//        Iterable<Cars> cars1 = carsRepository.findAll();
//        ArrayList<Cars> cars1ArrayList = new ArrayList<>();
//        Iterable<Buyer> buyers = buyerRepository.findAll();
//        ArrayList<Buyer> buyersArrayList = new ArrayList<>();
//        Iterable<Salon> salons = salonRepository.findAll();
//        ArrayList<Salon> salonsArrayList = new ArrayList<>();
//        Iterable<Manager> managers = managerRepository.findAll();
//        ArrayList<Manager> managersArrayList = new ArrayList<>();
//        Iterable<DKP> dkp1 = dkpRepository.findAll();
//        ArrayList<DKP> dkp1ArrayList = new ArrayList<>();
//
//        for (Buyer buyer: buyers)
//        {
//            if (buyer.getSurnameB()==null)
//            {
//                buyersArrayList.add(buyer);
//            }
//        }
//        model.addAttribute("cars1", cars1);
//        model.addAttribute("buyers", buyers);
//        model.addAttribute("salons", salons);
//        model.addAttribute("managers", managers);
//        model.addAttribute("dkp1", dkp1);
       // Iterable<Cars> cars1 = carsRepository.findAll();
        Iterable<Buyer> buyers = buyerRepository.findAll();
        Iterable<Salon> salons = salonRepository.findAll();
        Iterable<Manager> managers = managerRepository.findAll();

       // model.addAttribute("cars1", cars1);
        model.addAttribute("buyers", buyers);
        model.addAttribute("salons", salons);
        model.addAttribute("managers", managers);

        return "Add-Dkp";
    }


//    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
//    @PostMapping("/Add/Dkp")
//    public String blogPostAdd(@ModelAttribute("dkp1") @Valid DKP dkp1, BindingResult bindingResult, @RequestParam String carsmodel, @RequestParam String nameSalon, @RequestParam String surname, @RequestParam String surnameB, Model model) {
//
//        dkp1.setCars(carsRepository.findByCarsmodel(carsmodel));
//        dkp1.setSalon(salonRepository.findByNameSalon(nameSalon));
//        dkp1.setManager(managerRepository.findBySurname(surname));
//        dkp1.setBuyer(buyerRepository.findBySurnameB(surnameB));
//        if (bindingResult.hasErrors()) {
//            return "Add-Dkp";
//        }
//        dkpRepository.save(dkp1);
//        return "redirect:/Dkp";
//    }

    @PostMapping("/Add/Dkp")// добавление в бд
    public String productAdd(@ModelAttribute("product") @Valid DKP dkp1,
                             BindingResult bindingResult,
                            // @RequestParam(defaultValue = "") String carsmodel,
                             @RequestParam(defaultValue = "") String nameSalon,
                             @RequestParam(defaultValue = "") String surname,
                             @RequestParam(defaultValue = "") String surnameB,
                             Model model)
    {
        if(bindingResult.hasErrors()) {
           // Iterable<Cars> cars1 = carsRepository.findAll();
            Iterable<Buyer> buyers = buyerRepository.findAll();
            Iterable<Salon> salons = salonRepository.findAll();
            Iterable<Manager> managers = managerRepository.findAll();

          //  model.addAttribute("cars1", cars1);
            model.addAttribute("buyers", buyers);
            model.addAttribute("salons", salons);
            model.addAttribute("managers", managers);

            return "Add-Dkp";
        }

      //  dkp1.setCars(carsRepository.findByCarsmodel(carsmodel));
        dkp1.setSalon(salonRepository.findByNameSalon(nameSalon));
        dkp1.setManager(managerRepository.findBySurname(surname));
        dkp1.setBuyer(buyerRepository.findBySurnameB(surnameB));
        dkpRepository.save(dkp1);
        return "redirect:/Dkp";
    }
}
