package com.potapovich.project.validation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DataValidatorTest {

    static String VALID_NAME = "[A-Za-zА-Яа-я]{3,15}";

    @Test
    public void validationTestTrue() {
        String firstActionData = "Hello";
        String secondActionData = "Action";
        Assert.assertEquals(DataValidator.validation(VALID_NAME, firstActionData, secondActionData),true);
    }

    @Test
    public void validationTestFalse() {
        String firstActionData = "Hello44";
        String secondActionData = "Action";
        Assert.assertEquals(DataValidator.validation(VALID_NAME, firstActionData, secondActionData),false);
    }
}
