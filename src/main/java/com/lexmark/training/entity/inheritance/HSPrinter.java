package com.lexmark.training.entity.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HS")
public class HSPrinter extends Printer{
    public HSPrinter() {
    }

    public HSPrinter(String name, String modelName, String fwVersion) {
        super(name, modelName, fwVersion);
    }
}
