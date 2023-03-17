package com.example.Rabota.Controller;


import com.example.Rabota.Models.KPP;
import com.example.Rabota.repo.KppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class KppController {

    @Autowired
    private KppRepository kppRepository;

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/KPP")
    public String GetRab(Model model)
    {
        Iterable<KPP> kpp = kppRepository.findAll();
        model.addAttribute("KPP", kpp);
        return "MainKPP";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Add/KPP")
    public String GetAddVin(KPP kpp)
    {
        return "Add-KPP";
    }

    @PostMapping("/Add/KPP")
    public String blogKPPAdd(@Valid KPP kpp, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-KPP";}
        kppRepository.save(kpp);
        return "redirect:/KPP";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/KPP/{id}/Edit")
    public String KPPEdit(@PathVariable(value = "id") Long id, Model model)
    {
        KPP kpp = kppRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid engine Id" + id));
        model.addAttribute("kpp",kpp);
        return "Edit-kpp";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/KPP/{id}/Edit")
    public String GameUpdate(@ModelAttribute("kpp") @Valid KPP kpp, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-kpp";    kppRepository.save(kpp);    return "redirect:/KPP";}
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/KPP/{id}/Remove")
    public String blogPostDelete(
            @PathVariable(value = "id") Long id, Model model) {
        KPP kpp = kppRepository.findById(id).orElseThrow();
        kppRepository.delete(kpp);
        return "redirect:/KPP";
    }
}
