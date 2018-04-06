package com.Spring5.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private String id;
    private String recipeId;

    @NotBlank
    private String description;

    @DecimalMin("0.01")
    private BigDecimal amount;

    private UnitOfMeasureCommand uom;
}
