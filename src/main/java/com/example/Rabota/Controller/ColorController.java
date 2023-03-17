package com.example.Rabota.Controller;
import com.example.Rabota.Models.Color;
import com.example.Rabota.repo.ColorRepository;
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
public class ColorController {

    @Autowired
    private ColorRepository colorRepository;


    @GetMapping("/Color")
    public String GetRab(Model model)
    {
        Iterable<Color> color = colorRepository.findAll();
        model.addAttribute("Color", color);
        return "MainColor";
    }


    @GetMapping("/Add/Color")
    public String GetAddVin(Color color)
    {
        return "Add-Color";
    }

    @PostMapping("/Add/Color")
    public String blogPostAdd(@Valid Color color, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Color";}
        colorRepository.save(color);
        return "redirect:/Color";
    }

    @GetMapping("/Color/{id}/Edit")
    public String PostEdit(@PathVariable(value = "id") Long id, Model model)
    {
        Color color = colorRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid engine Id" + id));
        model.addAttribute("color",color);
        return "Edit-color";
    }

    @PostMapping("/Color/{id}/Edit")
    public String GameUpdate(@ModelAttribute("color") @Valid Color color, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-color";    colorRepository.save(color);    return "redirect:/Color";}

    @PostMapping("/Color/{id}/Remove")
    public String blogPostDelete(
            @PathVariable(value = "id") Long id, Model model) {
        Color color = colorRepository.findById(id).orElseThrow();
        colorRepository.delete(color);
        return "redirect:/Color";
    }
}
