package com.potapovich.project.logic.specification;

import com.potapovich.project.entity.Taxi;
import com.potapovich.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TaxiQuery {

    private static final Logger LOGGER = LogManager.getLogger();


    public List<Taxi> query(Specification specification, List<Taxi> listOfTaxi) {
        List<Taxi> result = new ArrayList<>();
        System.out.println("llllllliiiiiiiiiiiiiiiiistt  " + listOfTaxi);
        try {
            if (listOfTaxi.isEmpty()) {
                throw new DaoException("List of taxi is empty");
            }
            for (Taxi taxi : listOfTaxi) {
                if (specification.specified(taxi)) {
                    result.add(taxi);
                }
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "DaoException: " + e.getMessage());
        }
        System.out.println("result " + result);
        return result;
    }


}
