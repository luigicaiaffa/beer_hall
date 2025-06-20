package org.project.java.beer_hall.controller;

import org.project.java.beer_hall.model.Beer;
import org.project.java.beer_hall.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("beers", beerService.findAll());
        return "beer/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("beer", beerService.getById(id));
        return "beer/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("beer", new Beer());
        return "beer/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("beer") Beer formBeer, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "beer/form";
        }

        beerService.create(formBeer);
        return "redirect:/beers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("edit", true);
        model.addAttribute("beer", beerService.getById(id));
        return "beer/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("beer") Beer formBeer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("beer", formBeer);
            return "beer/form";
        }

        beerService.update(formBeer);
        return "redirect:/beers/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {

        beerService.deleteById(id);
        return "redirect:/beers";
    }

}
