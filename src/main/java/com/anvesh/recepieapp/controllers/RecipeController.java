package com.anvesh.recepieapp.controllers;


import com.anvesh.recepieapp.dataTransfers.RecipeCommand;
import com.anvesh.recepieapp.domain.Recipe;
import com.anvesh.recepieapp.exceptions.NotFoundException;
import com.anvesh.recepieapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @RequestMapping({"/recipe/show/{id}"})
    public String getRecipie(@PathVariable String id, Model model) {
        Recipe recipe = service.findById(Long.parseLong(id));
        model.addAttribute("recipe", recipe);
        return "/recipe/show";
    }


    @RequestMapping({"/recipe/new"})
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand recipe = service.saveReciepeCommand(command);
        return "redirect:/recipe/show/" + recipe.getId();

    }


    //    To autopopulate the values in view Page
    @RequestMapping({"/recipe/{id}/update"})
    public String Update(@PathVariable String id, Model model) {
        RecipeCommand command = service.commandFindyById(Long.valueOf(id));
        model.addAttribute("recipe", command);
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping({"recipe/{id}/delete"})
    public String delete(@PathVariable String id) {
        service.deletById(Long.valueOf(id));
        return "redirect:/index";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView exceptionHandler(Exception e) {
        log.debug("Not Found Exception");
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404Notfound");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }

    //    For numberFormat Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView exceptionHandlerNF(Exception e) {
        log.debug("Not Found Exception");
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404Notfound");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}

