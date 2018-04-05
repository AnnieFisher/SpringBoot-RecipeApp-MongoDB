package com.Spring5.services;

import com.Spring5.commands.RecipeCommand;
import com.Spring5.model.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {

    Flux<Recipe> getRecipes();
    Mono<Recipe> findById(String id);
    Mono<RecipeCommand> findCommandById(String commandId);
    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand);
    Mono<Void> deleteById(String idToDelete);
}
