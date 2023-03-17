package com.example.Rabota.Controller;

import com.example.Rabota.Models.Insurance;
import com.example.Rabota.Models.TypeInsurance;
import com.example.Rabota.repo.InsuranceRepository;
import com.example.Rabota.repo.TypeInsuranceRepository;
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
public class TypeInsuranceController {

    @Autowired
    private TypeInsuranceRepository typeInsuranceRepository;

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/TypeInsurance")
    public String GetRab(Model model)
    {
        Iterable<TypeInsurance> typeInsurance = typeInsuranceRepository.findAll();
        model.addAttribute("TypeInsurance", typeInsurance);
        return "MainTypeInsurance";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Add/TypeInsurance")
    public String GetAddVin(TypeInsurance typeInsurance)
    {
        return "Add-TypeInsurance";
    }

    @PostMapping("/Add/TypeInsurance")
    public String blogKPPAdd(@Valid TypeInsurance typeInsurance, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-TypeInsurance";}
        typeInsuranceRepository.save(typeInsurance);
        return "redirect:/TypeInsurance";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/TypeInsurance/{id}/Edit")
    public String KPPEdit(@PathVariable(value = "id") Long id, Model model)
    {
        TypeInsurance typeInsurance = typeInsuranceRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid typeInsurance Id" + id));
        model.addAttribute("typeInsurance",typeInsurance);
        return "Edit-typeInsurance";
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/TypeInsurance/{id}/Edit")
    public String GameUpdate(@ModelAttribute("typeInsurance") @Valid TypeInsurance typeInsurance, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-typeInsurance";    typeInsuranceRepository.save(typeInsurance); return "redirect:/TypeInsurance";}
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/TypeInsurance/{id}/Remove")
    public String blogPostDelete(
            @PathVariable(value = "id") Long id, Model model) {
        TypeInsurance typeInsurance = typeInsuranceRepository.findById(id).orElseThrow();
        typeInsuranceRepository.delete(typeInsurance);
        return "redirect:/TypeInsurance";
    }
}
