package com.lexmark.training.entity.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("WC")
public class WCPrinter extends Printer {
    public WCPrinter() {
    }

    public WCPrinter(String name, String modelName, String fwVersion) {
        super(name, modelName, fwVersion);
    }
}
