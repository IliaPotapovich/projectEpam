package com.potapovich.project.logic;

import com.potapovich.project.entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class TaxiServiceTest {

    static TaxiService taxiService = new TaxiService();
    static Route route = new Route();
    static Point location = new Point();
    static Point destination = new Point();
    static Distance distance = new Distance();

    @BeforeClass
    public static void setUp() {
        location = new Point(7, 3);
        destination = new Point(15, 20);
        distance = new Distance(0, 24);
        route.setDestination(destination);
        route.setLocation(location);
    }


    @AfterClass
    public static void tearDown() {
        taxiService = null;
        route = null;
        location = null;
        destination = null;
        distance = null;
    }


    @Test
    public void findDistancesBetweenTaxiAndClientTest() {

        List<Taxi> listOfAvailableTaxi = new ArrayList<>();
        listOfAvailableTaxi.add(new Taxi(new Point(2, 4)));
        listOfAvailableTaxi.add(new Taxi(new Point(4, 7)));
        listOfAvailableTaxi.add(new Taxi(new Point(1, 5)));
        listOfAvailableTaxi.add(new Taxi(new Point(4, 3)));
        listOfAvailableTaxi.add(new Taxi(new Point(10, 15)));

        Customer customer = new Customer(route);

        List<Distance> listOfExpectedDistances = new ArrayList<>();
        listOfExpectedDistances.add(new Distance(0, 5.1));
        listOfExpectedDistances.add(new Distance(0, 5.0));
        listOfExpectedDistances.add(new Distance(0, 6.32));
        listOfExpectedDistances.add(new Distance(0, 3.0));
        listOfExpectedDistances.add(new Distance(0, 12.37));

        Assert.assertEquals(taxiService.findDistanceBetweenTaxiAndClient(listOfAvailableTaxi, customer), listOfExpectedDistances);
    }


    @Test
    public void findDistanceTest() {
        Distance expectedDistance = new Distance(0, 18.79);
        Assert.assertEquals(taxiService.findDistance(route), expectedDistance);
    }


    @Test
    public void calculateTimeByDistanceTest() {

        int expectedMin = 3;
        Assert.assertEquals(taxiService.calculateTimeByDistance(distance), expectedMin);
    }


    @Test
    public void calculateCostByDistanceTest() {
        int expectedCost = 2;
        Assert.assertEquals(taxiService.calculateCostByDistance(distance), expectedCost);
    }
}
