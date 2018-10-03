package com.potapovich.project.logic.comparator;


import com.potapovich.project.entity.Distance;

import java.util.Comparator;
import java.util.List;

public class SortData {

    private List<Distance> listOfDistance;

    public SortData(List<Distance> listOfDistance) {
        this.listOfDistance = listOfDistance;
    }

    /**
     * Sorts distances and returns a list of distances from smallest to largest
     * @return List<Distance>
     */
    public List<Distance> sortDistance(Comparator<Distance> comparator) {
        listOfDistance.sort(comparator);
        return listOfDistance;
    }
}
