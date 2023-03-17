

package com.example.Rabota.Controller;

import com.example.Rabota.Models.Post;
import com.example.Rabota.Models.Postavka;
import com.example.Rabota.repo.PostRepository;
import com.example.Rabota.repo.PostavkaRepository;
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
public class PostavkaController {
    @Autowired
    private PostavkaRepository postavkaRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/Postavka")
    public String GetRab(Model model)
    {
        Iterable<Postavka> postavka = postavkaRepository.findAll();
        model.addAttribute("postavka", postavka);
        return "MainPostavka";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/Add/Postavka")
    public String GetAddVin(Postavka postavka)
    {
        return "Add-Postavka";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Add/Postavka")
    public String blogPostavkaAdd(@Valid Postavka postavka, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Postavka";}
        postavkaRepository.save(postavka);
        return "redirect:/Postavka";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/Postavka/{id}/Edit")
    public String PostavkaEdit(@PathVariable(value = "id") Long id, Model model)
    {
        Postavka postavka = postavkaRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid postavka Id" + id));
        model.addAttribute("postavka",postavka);
        return "Edit-Postavka";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Postavka/{id}/Edit")
    public String GameUpdate(@ModelAttribute("postavka") @Valid Postavka postavka, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-Postavka";    postavkaRepository.save(postavka);    return "redirect:/Postavka";}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Postavka/{id}/Remove")
    public String blogPostavkaDelete(
            @PathVariable(value = "id") Long id, Model model) {
        Postavka postavka = postavkaRepository.findById(id).orElseThrow();
        postavkaRepository.delete(postavka);
        return "redirect:/Postavka";
    }
}
