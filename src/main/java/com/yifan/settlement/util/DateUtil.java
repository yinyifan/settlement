package com.yifan.settlement.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DateUtil {

    public LocalDate convertDateStringToSettlementDateFormat(String date){
        DateTimeFormatter settlementDateFormat = DateTimeFormatter.ofPattern("ddMMyyyy");
        return LocalDate.parse(date,settlementDateFormat);

    }
}
