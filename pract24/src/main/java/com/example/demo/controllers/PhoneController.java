package com.example.demo.controllers;

import com.example.demo.DAO.ManufactureDAO;
import com.example.demo.DAO.PhoneDAO;
import com.example.demo.EmailService;
import com.example.demo.models.Phone;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/phones")
public class PhoneController {

    private final PhoneDAO phoneDAO;
    private final ManufactureDAO manufactureDAO;
    private final EmailService emailService;

    @Autowired
    public PhoneController(PhoneDAO phoneDAO, ManufactureDAO manufactureDAO, EmailService emailService) {
        this.phoneDAO = phoneDAO;
        this.manufactureDAO=manufactureDAO;
        this.emailService = emailService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("phones", phoneDAO.getPhones());
        return "phoneController/phones";
    }

    @GetMapping("/new")
        public String newPhone(@ModelAttribute("phone") Phone phone) {

        return "phoneController/newphone";


    }

    @SneakyThrows
    @PostMapping()
    public String create(@RequestParam String name, @RequestParam int creationYear,@RequestParam long manufacture_id) {
        Phone phone = new Phone();
        phone.setName(name);
        phone.setCreationYear(creationYear);
        phone.setManufacture(manufactureDAO.show(manufacture_id));
        phoneDAO.save(phone);
        emailService.MailSender(phone);
        return "redirect:/home/phones";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("phone", phoneDAO.show(id));
        return "phoneController/show";
    }


    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        phoneDAO.delete(id);
        return "redirect:/home/phones";
    }
    @GetMapping("/{id}/manufacture")
    public String showManufacture(@PathVariable("id") Long id, Model model) {
        model.addAttribute("manufacture", phoneDAO.getManufactureByPhone(id));
        return "manufactureController/show";
    }

    @GetMapping("/filteredById")
    public String showPhonesFilteredById(Model model) {
        model.addAttribute("phones", phoneDAO.getPhoneById());
        return "phoneController/phones";
    }

    @GetMapping("/filteredByName")
    public String showPhonesFilteredByName(Model model) {
        model.addAttribute("phones", phoneDAO.getPhoneByName());
        return "phoneController/phones";
    }

    @GetMapping("/filteredByCreationYear")
    public String showPhonesFilteredByYear(Model model) {
        model.addAttribute("phones", phoneDAO.getPhoneByYear());
        return "phoneController/phones";
    }

}

