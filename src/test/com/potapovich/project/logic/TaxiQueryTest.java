package com.potapovich.project.logic;

import com.potapovich.project.entity.Distance;
import com.potapovich.project.entity.Point;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.logic.comparator.CompareDistance;
import com.potapovich.project.logic.comparator.SortData;
import com.potapovich.project.logic.specification.FindApropriateTaxi;
import com.potapovich.project.logic.specification.TaxiQuery;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TaxiQueryTest {


    @Test
    public void FindAppropriateTaxiTest() {

        List<Taxi> listOfTaxiActual = new ArrayList<>();
        listOfTaxiActual.add(new Taxi(new Point(2,4)));
        listOfTaxiActual.add(new Taxi(new Point(4,7)));
        listOfTaxiActual.add(new Taxi(new Point(1,5)));
        listOfTaxiActual.add(new Taxi(new Point(4,3)));
        listOfTaxiActual.add(new Taxi(new Point(10,15)));

        List<Taxi> listOfTaxiExpected = new ArrayList<>();
        listOfTaxiExpected.add(new Taxi(new Point(2,4)));
        listOfTaxiExpected.add(new Taxi(new Point(1,5)));
        listOfTaxiExpected.add(new Taxi(new Point(4,3)));

        Assert.assertEquals(new TaxiQuery().query(new FindApropriateTaxi(0,5,2,6),listOfTaxiActual),listOfTaxiExpected);
    }




}
