package com.Spring5.converters;

import com.Spring5.commands.UnitOfMeasureCommand;
import com.Spring5.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure != null){
            final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
            uomCommand.setId(unitOfMeasure.getId());
            uomCommand.setDescription(unitOfMeasure.getDescription());
            return uomCommand;
        }
        return null;
    }
}
