package com.Spring5.services;

import com.Spring5.commands.RecipeCommand;
import com.Spring5.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(String id);
    RecipeCommand findCommandById(String commandId);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    void deleteById(String idToDelete);
}
