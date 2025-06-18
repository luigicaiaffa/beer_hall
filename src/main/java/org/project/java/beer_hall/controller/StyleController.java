package org.project.java.beer_hall.controller;

import org.project.java.beer_hall.model.Style;
import org.project.java.beer_hall.service.StyleService;
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
@RequestMapping("/styles")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("styles", styleService.findAll());
        return "style/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("style", styleService.findById(id));
        return "style/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("style", new Style());
        return "style/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("style") Style formStyle, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "style/form";
        }

        styleService.create(formStyle);
        return "redirect:/styles";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("edit", true);
        model.addAttribute("style", styleService.findById(id));
        return "style/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("style") Style formStyle,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("style", formStyle);
            return "style/form";
        }

        styleService.update(formStyle);
        return "redirect:/styles/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {

        styleService.deleteById(id);
        return "redirect:/styles";
    }
}
