package com.yifan.settlement.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DateUtilTest {
    private DateUtil dateUtil;

    @BeforeAll
    public void init(){
        dateUtil =new DateUtil();
    }
    @Test
    public void testConvertDateStringToSettlementDateFormat_validFormat_returnParsedDate(){
        LocalDate response = dateUtil.convertDateStringToSettlementDateFormat("01012020");
        Assertions.assertAll("valid localDate returned",
                ()-> Assertions.assertEquals(response.getYear(),2020),
                ()-> Assertions.assertEquals(response.getMonth(), Month.JANUARY),
                ()-> Assertions.assertEquals(response.getDayOfMonth(),1));
    }

    @Test
    public void testConvertDateStringToSettlementDateFormat_invalidDate_throwException(){
        Assertions.assertThrows(DateTimeParseException.class, ()->dateUtil.convertDateStringToSettlementDateFormat("32012020"));
        Assertions.assertThrows(DateTimeParseException.class, ()->dateUtil.convertDateStringToSettlementDateFormat("30132020"));
        Assertions.assertThrows(DateTimeParseException.class, ()->dateUtil.convertDateStringToSettlementDateFormat("3001999"));
        Assertions.assertThrows(DateTimeParseException.class, ()->dateUtil.convertDateStringToSettlementDateFormat("testInvalidDate"));
    }
}
