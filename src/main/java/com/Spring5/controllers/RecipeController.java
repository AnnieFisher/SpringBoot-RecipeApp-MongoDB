package com.Spring5.controllers;

import com.Spring5.commands.RecipeCommand;
import com.Spring5.exceptions.NotFoundException;
import com.Spring5.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;

@Slf4j
@Controller
public class RecipeController {

    private final String RECIPE_RECIPEFORM_URL = "recipe/recipeForm";
    private final RecipeService recipeService;

    private WebDataBinder webDataBinder;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/recipeDetails";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("/recipe/{id}/update")
    public String update(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id).block());
        return "recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute("recipe") RecipeCommand command){
        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "recipe/recipeForm";
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command).block();
        return "redirect:/recipe/" +savedCommand.getId() +"/show" ;
    }

    @GetMapping("recipe/{id}/delete")
    public String delete(@PathVariable String id){
        recipeService.deleteById(id).block();
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleNotFound(Exception e, Model model){

        log.error("Handling not found exception");
        log.error(e.getMessage());

        model.addAttribute("exception", e);

        return "404error";
    }
}

