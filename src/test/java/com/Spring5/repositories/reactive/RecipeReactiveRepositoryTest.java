package com.Spring5.repositories.reactive;

import com.Spring5.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository reactiveRepository;


    @Before
    public void setUp() throws Exception {
        reactiveRepository.deleteAll().block();
    }

    @Test
    public void saveRecipeTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription("Gummy Bears");

        reactiveRepository.save(recipe).block();

        String count = reactiveRepository.count().block().toString();

        assertEquals("1", count);
    }
}