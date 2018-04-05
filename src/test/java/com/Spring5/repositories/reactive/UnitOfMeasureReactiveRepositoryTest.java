package com.Spring5.repositories.reactive;

import com.Spring5.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String CUP = "Cup";

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void saveUomTest() throws Exception {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(CUP);

        unitOfMeasureReactiveRepository.save(uom).block();
        String count = unitOfMeasureReactiveRepository.count().block().toString();

        assertEquals("1", count);
    }

    @Test
    public void findByDescriptionTest() throws Exception {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(CUP);

        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure savedUom = unitOfMeasureReactiveRepository.findByDescription(CUP).block();
        assertEquals(CUP, savedUom.getDescription());
    }
}