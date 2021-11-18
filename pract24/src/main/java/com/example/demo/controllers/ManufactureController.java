
package com.example.demo.controllers;

import com.example.demo.DAO.ManufactureDAO;
import com.example.demo.models.Manufacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/home/manufactures")
public class ManufactureController {

    private final ManufactureDAO manufactureDAO;
    @Autowired
    public ManufactureController(ManufactureDAO manufactureDAO) {
        this.manufactureDAO = manufactureDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("manufactures", manufactureDAO.getManufactures());
        return "manufactureController/manufactures";
    }

    @GetMapping("/new")
    public String newManufacture(Model model) {
        model.addAttribute("manufacture",new Manufacture());
        return "manufactureController/newmanufacture";
    }

    @PostMapping()
    public String create(@ModelAttribute("manufacture") Manufacture manufacture) {
        manufactureDAO.save(manufacture);
        return "redirect:/home/manufactures";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("manufacture", manufactureDAO.show(id));
        return "manufactureController/show";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        manufactureDAO.delete(id);
        return "redirect:/home/manufactures";
    }

    @GetMapping("/filteredById")
    public String showManufacturesFilteredById(Model model) {
        model.addAttribute("manufactures", manufactureDAO.getManufactureById());
        return "manufactureController/manufactures";
    }
    @GetMapping("/filteredByName")
    public String showManufacturesFilteredByName(Model model) {
        model.addAttribute("manufactures", manufactureDAO.getManufactureByName());
        return "manufactureController/manufactures";
    }

    @GetMapping("/filteredByAddress")
    public String showManufacturesFilteredByAddress(Model model) {
        model.addAttribute("manufactures", manufactureDAO.getManufactureByAddress());
        return "manufactureController/manufactures";
    }

}