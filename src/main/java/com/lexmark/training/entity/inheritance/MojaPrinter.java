package com.lexmark.training.entity.inheritance;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Moja")
public class MojaPrinter extends Printer{
    @Column(name="android", nullable = false)
    String androidVersion;

    public MojaPrinter() {
    }

    public MojaPrinter(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public MojaPrinter(String name, String modelName, String fwVersion, String androidVersion) {
        super(name, modelName, fwVersion);
        this.androidVersion = androidVersion;
    }

}
