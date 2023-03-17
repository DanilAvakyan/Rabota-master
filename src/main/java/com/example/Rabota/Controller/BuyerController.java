
package com.example.Rabota.Controller;


        import com.example.Rabota.Models.Buyer;
        import com.example.Rabota.Models.Manager;
        import com.example.Rabota.repo.BuyerRepository;


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
public class BuyerController {
    @Autowired
    private BuyerRepository buyerRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/Buyer")
    public String GetRab(Model model)
    {
        Iterable<Buyer> buyer = buyerRepository.findAll();
        model.addAttribute("Buyer", buyer);
        return "MainBuyer";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/Add/Buyer")
    public String GetAddVin(Buyer buyer)
    {
        return "Add-Manager";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")

    @PostMapping("/Add/Buyer")
    public String blogBuyerAdd(@Valid Buyer buyer, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Buyer";}
        buyerRepository.save(buyer);
        return "redirect:/Buyer";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping( path = "/Search/Buyer")
    public String blogFilter(Buyer buyer)
    {
        return "Search-Buyer";
    }
   // @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")

//    @PostMapping("/Search/Buyer-result")
//    public String blogResult(@RequestParam String surnameB, Model model)
//    {
//        List<Buyer> buyer = buyerRepository.findBySurnameB(surnameB);
//        model.addAttribute("result", buyer);
//        return "Search-Buyer";
//    }
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
//    @PostMapping("/Search/Buyer")
//    public String simpleSearch(@RequestParam String surnameB, Model model)
//    {
//        List<Buyer> buyer = buyerRepository.findBySurnameBContains(surnameB);
//        model.addAttribute("result", buyer);
//        return "Search-Buyer";
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/blog/Buyer/{id}/Edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model){
        Buyer buyer = buyerRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid car Id" + id));
        model.addAttribute("Buyer",buyer);
        return "Edit-buyer";

    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/blog/Buyer/{id}/Edit")
    public String GameUpdate(@ModelAttribute("Buyer") @Valid Buyer buyer, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-buyer";    buyerRepository.save(buyer);    return "redirect:/Buyer";}

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/blog/Buyer/{id}/Remove")
    public String blogManagerDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow();
        buyerRepository.delete(buyer);
        return "redirect:/Buyer";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/blog/Buyer/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Buyer> buyer = buyerRepository.findById(id);
        ArrayList<Buyer> lis = new ArrayList<>();
        buyer.ifPresent(lis::add);
        model.addAttribute("Buyer", lis);
        return "blog-BuyerDetails";
    }
}
