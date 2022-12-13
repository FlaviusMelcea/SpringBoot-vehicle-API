package com.vehicle.app.vehicleApi.Enum;

import java.time.LocalDate;

public enum EmissionLevel {
    EURO4(LocalDate.parse("2010-01-01")),
    EURO5(LocalDate.parse("2016-03-04")),
    EURO6(LocalDate.parse("2016-03-04"));
    public final LocalDate localDate;


    EmissionLevel(LocalDate localDate) {
        this.localDate = localDate;
    }
}
