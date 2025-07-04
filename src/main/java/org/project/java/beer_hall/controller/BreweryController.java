package org.project.java.beer_hall.controller;

import org.project.java.beer_hall.model.Brewery;
import org.project.java.beer_hall.service.BreweryService;
import org.project.java.beer_hall.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/breweries")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;

    @Autowired
    private NationService nationService;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String name,
            @RequestParam(required = false) String nation) {

        nation = (nation != null && nation.equals("0")) ? "" : nation;

        model.addAttribute("breweries", breweryService.findAllByParams(name, nation));
        model.addAttribute("nations", nationService.findAll());
        return "brewery/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("brewery", breweryService.getById(id));
        return "brewery/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("brewery", new Brewery());
        model.addAttribute("nations", nationService.findAll());
        return "brewery/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("brewery") Brewery formBrewery, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("nations", nationService.findAll());
            return "brewery/form";
        }

        breweryService.create(formBrewery);
        return "redirect:/breweries";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("edit", true);
        model.addAttribute("brewery", breweryService.getById(id));
        model.addAttribute("nations", nationService.findAll());
        return "brewery/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("brewery") Brewery formBrewery,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("brewery", formBrewery);
            model.addAttribute("nations", nationService.findAll());
            return "brewery/form";
        }

        breweryService.update(formBrewery);
        return "redirect:/breweries";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {

        breweryService.deleteById(id);
        return "redirect:/breweries";
    }
}
