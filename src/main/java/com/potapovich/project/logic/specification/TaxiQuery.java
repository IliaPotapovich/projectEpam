package com.potapovich.project.logic.specification;

import com.potapovich.project.entity.Taxi;
import com.potapovich.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TaxiQuery {

    /**
     * The main method of determining entry into certain limits and returning a list of suitable taxi
     * @return List<Taxi>
     */
    public List<Taxi> query(Specification specification, List<Taxi> listOfTaxi) {
        List<Taxi> result = new ArrayList<>();
            for (Taxi taxi : listOfTaxi) {
                if (specification.specified(taxi)) {
                    result.add(taxi);
                }
            }
        return result;
    }
}
