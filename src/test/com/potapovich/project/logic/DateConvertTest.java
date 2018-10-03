package com.potapovich.project.logic;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDateTime;

public class DateConvertTest {

    @Test
    public void dateToStringTest() {
        String date = "2000-11-17 19:55:00";
        Assert.assertEquals(DateConvert.dateToString(LocalDateTime.of(2000,11,17,19,55)),date);
    }
}
