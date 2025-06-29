package org.project.java.beer_hall.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.project.java.beer_hall.model.Beer;
import org.project.java.beer_hall.service.BeerService;
import org.project.java.beer_hall.service.BreweryService;
import org.project.java.beer_hall.service.NationService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
    private BreweryService breweryService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private NationService nationService;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String name,
            @RequestParam(required = false) String nation, @RequestParam(required = false) String style,
            @RequestParam(required = false) String brewery) {

        nation = (nation != null && nation.equals("0")) ? "" : nation;
        style = (style != null && style.equals("0")) ? "" : style;
        brewery = (brewery != null && brewery.equals("0")) ? "" : brewery;

        model.addAttribute("beers", beerService.findAllByParams(name, nation, style, brewery));
        model.addAttribute("nations", nationService.findAll());
        model.addAttribute("styles", styleService.findAll());
        model.addAttribute("breweries", breweryService.findAll());
        return "beer/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("beer", beerService.getById(id));
        return "beer/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("breweries", breweryService.findAll());
        model.addAttribute("styles", styleService.findAll());
        model.addAttribute("beer", new Beer());
        return "beer/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("beer") Beer formBeer, BindingResult bindingResult,
            @RequestParam("imgFile") MultipartFile imgFile, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("breweries", breweryService.findAll());
            model.addAttribute("styles", styleService.findAll());
            return "beer/form";
        }

        if (imgFile != null && !imgFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/img/beers/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                imgFile.transferTo(filePath.toFile());
                formBeer.setImgUrl("/img/beers/" + fileName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        beerService.create(formBeer);
        return "redirect:/beers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("edit", true);
        model.addAttribute("breweries", breweryService.findAll());
        model.addAttribute("styles", styleService.findAll());
        model.addAttribute("beer", beerService.getById(id));
        return "beer/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("beer") Beer formBeer,
            BindingResult bindingResult, @RequestParam("imgFile") MultipartFile imgFile, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("breweries", breweryService.findAll());
            model.addAttribute("styles", styleService.findAll());
            model.addAttribute("beer", formBeer);
            return "beer/form";
        }

        Beer existingBeer = beerService.getById(id);

        if (imgFile != null && !imgFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();
                String uploadDir = "./uploads/img/beers/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                imgFile.transferTo(filePath.toFile());
                formBeer.setImgUrl("/img/beers/" + fileName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            formBeer.setImgUrl(existingBeer.getImgUrl());
        }

        formBeer.setId(id);
        beerService.update(formBeer);
        return "redirect:/beers/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {

        beerService.deleteById(id);
        return "redirect:/beers";
    }

}
