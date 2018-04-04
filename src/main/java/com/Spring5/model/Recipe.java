package com.Spring5.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

@Getter
@Setter
@Document
public class Recipe {

    @Id
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private List<String> categoryList = new ArrayList<>();
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;
    private Notes notes;
    private Set<Ingredient> ingredients = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
    public Recipe addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        return this;
    }
    public void addEnumCategoryToList(Categories cat){
        List<String> enumNames = Stream.of(Categories.values()).map(Categories::name)
                .collect(Collectors.toList());

        for(String category: enumNames){
            if(category.equalsIgnoreCase(valueOf(cat))){
                this.categoryList.add(category);
            }
        }
    }
}
