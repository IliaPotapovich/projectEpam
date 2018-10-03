package com.potapovich.project.logic;

import com.potapovich.project.entity.Distance;
import com.potapovich.project.logic.comparator.CompareDistance;
import com.potapovich.project.logic.comparator.SortData;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.ArrayList;
import java.util.List;

public class SortDataTest {

    @Test
    public void CompareDistancesTest() {

        List<Distance> listOfDistanceActual = new ArrayList<>();
        listOfDistanceActual.add(new Distance(1,4));
        listOfDistanceActual.add(new Distance(2,3));
        listOfDistanceActual.add(new Distance(3,6));
        listOfDistanceActual.add(new Distance(4,5));

        List<Distance> listOfDistanceExpected = new ArrayList<>();
        listOfDistanceExpected.add(new Distance(2,3));
        listOfDistanceExpected.add(new Distance(1,4));
        listOfDistanceExpected.add(new Distance(4,5));
        listOfDistanceExpected.add(new Distance(3,6));

        Assert.assertEquals(new SortData(listOfDistanceActual).sortDistance(new CompareDistance()),listOfDistanceExpected);
    }

}
