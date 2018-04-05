package com.Spring5.converters;

import com.Spring5.commands.IngredientCommand;
import com.Spring5.commands.NotesCommand;
import com.Spring5.commands.RecipeCommand;
import com.Spring5.model.Categories;
import com.Spring5.model.Difficulty;
import com.Spring5.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final String RECIPE_ID = "1";
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final String INGREDIENT_ID_1 = "3";
    public static final String INGREDIENT_ID_2 = "4";
    public static final String NOTES_ID = "9";

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        recipeCommand.setNotes(notes);

        List<String> catList = new ArrayList<>();
        List<String> enumNames = Stream.of(Categories.values()).map(Categories::name)
                .collect(Collectors.toList());

        String cat1 = valueOf(Categories.AMERICAN);
        String cat2 = valueOf(Categories.CHINESE);

        catList.add(cat1);
        catList.add(cat2);

        recipeCommand.setCategoryList(catList);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGREDIENT_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGREDIENT_ID_2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        Recipe recipe  = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategoryList().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}
