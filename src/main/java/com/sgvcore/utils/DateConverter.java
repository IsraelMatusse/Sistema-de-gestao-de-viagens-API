package com.sgvcore.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {
    public static int getYearsBetween(Date startDate, Date endDate){
        LocalDate stDateConverted = LocalDate.from(Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.of("Africa/Johannesburg")).toLocalDate());
        LocalDate enDateConverted = LocalDate.from(Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.of("Africa/Johannesburg")).toLocalDate());
        return Period.between(stDateConverted, enDateConverted).getYears();
    }
}
