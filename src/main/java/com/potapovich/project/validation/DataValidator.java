package com.potapovich.project.validation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    private static final Logger LOGGER = LogManager.getLogger();

    private DataValidator() {
    }

    public static boolean validation(String pattern, String... checkedData) {
        boolean resultOfValidation = false;
        for (String checkedElement : checkedData) {
            if (checkedElement==null){
                LOGGER.log(Level.INFO, "Element of validation is null");
                break;
            }
            Pattern needed = Pattern.compile(pattern);
            Matcher match = needed.matcher(checkedElement);
            while (match.find()) {
                if (checkedElement.equals(match.group())){
                    resultOfValidation = true;
                }
                else {
                    LOGGER.log(Level.INFO, "Validation is fail: " + checkedElement);
                    return false;
                }
            }
        }
        return resultOfValidation;
    }
}
