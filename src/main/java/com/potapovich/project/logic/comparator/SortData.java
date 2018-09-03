package com.potapovich.project.logic.comparator;


import com.potapovich.project.entity.Distance;

import com.potapovich.project.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortData {

    private static final Logger LOGGER = LogManager.getLogger();
    private List<Distance> listOfDistance = new ArrayList<>();


    public SortData(List<Distance> listOfDistance) {
        this.listOfDistance = listOfDistance;
    }

    public List<Distance> sortDistance(Comparator<Distance> comparator) {
        try {
            if (listOfDistance.isEmpty()) {
                throw new LogicException("Firstly you have to save distance into base");
            }
            listOfDistance.sort(comparator);
        } catch (LogicException e) {
            LOGGER.log(Level.ERROR, "DaoException " + e.getMessage());
        }
        return listOfDistance;
    }


}
