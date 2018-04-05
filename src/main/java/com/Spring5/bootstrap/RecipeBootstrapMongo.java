package com.Spring5.bootstrap;

import com.Spring5.model.*;
import com.Spring5.repositories.RecipeRepository;
import com.Spring5.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@Slf4j
@Component
public class RecipeBootstrapMongo implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrapMongo(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUom();
        recipeRepository.saveAll(getRecipes());
    }

    private void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription(" ");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Pint");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setDescription("Dash");
        unitOfMeasureRepository.save(uom8);
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> tspUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!tspUomOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found-Teaspoon");
        }
        Optional<UnitOfMeasure> tbspUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tbspUomOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found-Tablespoon");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found-Cup");
        }
        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found-Dash");
        }
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription(" ");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found-emptyString");
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found-Pint");
        }
        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if (!ounceUomOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found-Ounce");
        }

        UnitOfMeasure tspUom = tspUomOptional.get();
        UnitOfMeasure tbspUom = tbspUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();

        Recipe guac = new Recipe();
        guac.setDescription("Perfect Guacamole");
        guac.setPrepTime(10);
        guac.setCookTime(0);
        guac.setDifficulty(Difficulty.EASY);
        List<String> catList = new ArrayList<>();
        catList.add(valueOf(Categories.AMERICAN));
        catList.add(valueOf(Categories.MEXICAN));
        guac.setCategoryList(catList);
        guac.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon.Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity— will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "\n" +
                "The trick to making perfect guacamole is using good, ripe avocados.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n" +
                "\n" +
                "Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n");

        guac.setNotes(guacNotes);

        guac.addIngredient(new Ingredient("avocados-ripe", new BigDecimal(2), eachUom));
        guac.addIngredient(new Ingredient("salt-kosher", new BigDecimal(.5), tspUom));
        guac.addIngredient(new Ingredient("lime juice or lemon juice-fresh", new BigDecimal(1), tbspUom));
        guac.addIngredient(new Ingredient("red onion-minced", new BigDecimal(.25), cupUom));
        guac.addIngredient(new Ingredient("serrano chiles-remove stems and seeds", new BigDecimal(2), eachUom));
        guac.addIngredient(new Ingredient("cilantro-finely chopped", new BigDecimal(2), tbspUom));
        guac.addIngredient(new Ingredient("black pepper-freshly grated", new BigDecimal(1), dashUom));
        guac.addIngredient(new Ingredient("ripe tomato-chopped, seeds and pulp removed", new BigDecimal(.5), eachUom));

        recipes.add(guac);

        Recipe chknTacos = new Recipe();
        chknTacos.setDescription("Spicy Grilled Chicken Tacos!");
        chknTacos.setPrepTime(20);
        chknTacos.setCookTime(15);
        chknTacos.setDifficulty(Difficulty.MODERATE);
        List<String> catList2 = new ArrayList<>();
        catList2.add(valueOf(Categories.AMERICAN));
        catList2.add(valueOf(Categories.MEXICAN));
        chknTacos.setCategoryList(catList2);
        chknTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes chknTacoNotes = new Notes();
        chknTacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");

        chknTacos.setNotes(chknTacoNotes);

        chknTacos.addIngredient(new Ingredient("Ancho chili powder", new BigDecimal(2), tbspUom));
        chknTacos.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), tspUom));
        chknTacos.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), tspUom));
        chknTacos.addIngredient(new Ingredient("sugar", new BigDecimal(1), tspUom));
        chknTacos.addIngredient(new Ingredient("salt", new BigDecimal(.5), tspUom));
        chknTacos.addIngredient(new Ingredient("garlic clove, finely chopped", new BigDecimal(1), eachUom));
        chknTacos.addIngredient(new Ingredient("orange zest, finely grated", new BigDecimal(1), tbspUom));
        chknTacos.addIngredient(new Ingredient("orange juice, fresh-squeezed", new BigDecimal(3), tbspUom));
        chknTacos.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tbspUom));
        chknTacos.addIngredient(new Ingredient("boneless chicken thighs-skinless", new BigDecimal(4), eachUom));
        chknTacos.addIngredient(new Ingredient("corn tortillas", new BigDecimal(8), eachUom));
        chknTacos.addIngredient(new Ingredient("baby arugula", new BigDecimal(3), ounceUom));
        chknTacos.addIngredient(new Ingredient("avocados-medium ripe-sliced", new BigDecimal(2), eachUom));
        chknTacos.addIngredient(new Ingredient("radish-thinly sliced", new BigDecimal(4), eachUom));
        chknTacos.addIngredient(new Ingredient("cherry tomatoes-halved", new BigDecimal(.5), pintUom));
        chknTacos.addIngredient(new Ingredient("red onion-thinly sliced", new BigDecimal(.25), eachUom));
        chknTacos.addIngredient(new Ingredient("cilantro-roughly chopped", new BigDecimal(2), eachUom));
        chknTacos.addIngredient(new Ingredient("sour cream-thinned with the milk", new BigDecimal(.5), cupUom));
        chknTacos.addIngredient(new Ingredient("milk", new BigDecimal(.25), cupUom));
        chknTacos.addIngredient(new Ingredient("lime", new BigDecimal(1), eachUom));

        recipes.add(chknTacos);

        return recipes;
    }
}
