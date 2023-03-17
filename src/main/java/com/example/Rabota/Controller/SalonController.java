
package com.example.Rabota.Controller;


        import com.example.Rabota.Models.Salon;
        import com.example.Rabota.repo.SalonRepository;


        import com.example.Rabota.repo.SalonRepository;
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
public class SalonController {
    @Autowired
    private SalonRepository salonRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/Salon")
    public String GetRab(Model model)
    {
        Iterable<Salon> salon = salonRepository.findAll();
        model.addAttribute("Salon", salon);
        return "MainSalon";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/Add/Salon")
    public String GetAddSalon(Salon salon)
    {
        return "Add-Salon";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/Add/Salon")
    public String blogPostAdd(@Valid Salon salon, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Salon";}
        salonRepository.save(salon);
        return "redirect:/Salon";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping( path = "/Search/Salon")
    public String blogFilter(Salon salon)
    {
        return "Search-Salon";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/Search/Salon-result")
    public String blogResult(@RequestParam String nameSalon, Model model)
    {
        List<Salon> salon = salonRepository.findByNameSalon(nameSalon);
        model.addAttribute("result", salon);
        return "Search-Salon";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/Search/Salon")
    public String simpleSearch(@RequestParam String nameSalon, Model model)
    {
        List<Salon> salon = salonRepository.findByNameSalonContains(nameSalon);
        model.addAttribute("result", salon);
        return "Search-Salon";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/blog/Salon/{id}/Edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model){
        Salon salon = salonRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid car Id" + id));
        model.addAttribute("Salon",salon);
        return "Edit-Salon";

    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/blog/Salon/{id}/Edit")
    public String SalonUpdate(@ModelAttribute("Salon") @Valid Salon salon, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-Salon";    salonRepository.save(salon);    return "redirect:/Salon";}

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/blog/Salon/{id}/Remove")
    public String blogSalonDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Salon salon = salonRepository.findById(id).orElseThrow();
        salonRepository.delete(salon);
        return "redirect:/Salon";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/blog/Salon/{id}")
    public String SalonDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Salon> salon = salonRepository.findById(id);
        ArrayList<Salon> lis = new ArrayList<>();
        salon.ifPresent(lis::add);
        model.addAttribute("Salon", lis);
        return "blog-SalonDetails";
    }
}
