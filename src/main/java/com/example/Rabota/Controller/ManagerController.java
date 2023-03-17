package com.example.Rabota.Controller;


import com.example.Rabota.Models.Manager;
import com.example.Rabota.repo.ManagerRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;

@PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/Manager")
    public String GetRab(Model model)
    {
        Iterable<Manager> manager = managerRepository.findAll();
        model.addAttribute("Manager", manager);
        return "MainManager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/Add/Manager")
    public String GetAddVin(Manager manager)
    {
        return "Add-Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Add/Manager")
    public String blogPostAdd(@Valid Manager manager, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Manager";}
        managerRepository.save(manager);
        return "redirect:/Manager";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping( path = "/Search/Manager")
    public String blogFilter(Manager manager)
    {
        return "Search-Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Search/Manager-result")
    public String blogResult(@RequestParam String surname, Model model)
    {
        List<Manager> manager = managerRepository.findBySurname(surname);
        model.addAttribute("result", manager);
        return "Search-Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Search/Manager")
    public String simpleSearch(@RequestParam String surname, Model model)
    {
        List<Manager> manager = managerRepository.findBySurnameContains(surname);
        model.addAttribute("result", manager);
        return "Search-Manager";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/blog/Manager/{id}/Edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model){
        Manager manager = managerRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid car Id" + id));
        model.addAttribute("Manager",manager);
        return "Edit-manager";

    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/blog/Manager/{id}/Edit")
    public String GameUpdate(@ModelAttribute("Manager") @Valid Manager manager, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-manager";    managerRepository.save(manager);    return "redirect:/Manager";}

    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/blog/Manager/{id}/Remove")
    public String blogManagerDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Manager manager = managerRepository.findById(id).orElseThrow();
        managerRepository.delete(manager);
        return "redirect:/Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/blog/Manager/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Manager> manager = managerRepository.findById(id);
        ArrayList<Manager> lis = new ArrayList<>();
        manager.ifPresent(lis::add);
        model.addAttribute("Manager", lis);
        return "blog-ManagerDetails";
    }
}
