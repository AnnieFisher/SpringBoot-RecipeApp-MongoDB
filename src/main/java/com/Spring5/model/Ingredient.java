package com.Spring5.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Ingredient {

    private String id;
    private String description;
    private BigDecimal amount;

    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

}
