package com.potapovich.project.logic.comparator;

import com.potapovich.project.entity.Distance;

import java.util.Comparator;

public class CompareDistance implements Comparator<Distance> {

    @Override
    public int compare(Distance distance1, Distance distance2) {

        if (distance1.getDistance() > distance2.getDistance()) {
            return 1;
        } else if (distance1.getDistance() < distance2.getDistance()) {
            return -1;
        } else {
            return 0;
        }
    }
}
