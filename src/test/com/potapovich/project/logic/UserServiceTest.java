package com.potapovich.project.logic;

import com.potapovich.project.entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    static UserService userService = new UserService();
    static Route route = new Route();
    static Point location = new Point();
    static Point destination = new Point();
    static Distance distance = new Distance();
    static Customer customer = new Customer();

    @BeforeClass
    public static void setUp() {
        location = new Point(7, 3);
        destination = new Point(15, 20);
        distance = new Distance(4, 3);
        route.setDestination(destination);
        route.setLocation(location);
        customer = new Customer(route);
    }


    @AfterClass
    public static void tearDown() {
        userService = null;
        route = null;
        location = null;
        destination = null;
        distance = null;
    }


    @Test
    public void findAppropriateDistanceToTaxiTest() {

        List<Taxi> listOfAvailableTaxi = new ArrayList<>();
        listOfAvailableTaxi.add(new Taxi(1, new Point(2, 4)));
        listOfAvailableTaxi.add(new Taxi(2, new Point(4, 7)));
        listOfAvailableTaxi.add(new Taxi(3, new Point(1, 5)));
        listOfAvailableTaxi.add(new Taxi(4, new Point(4, 3)));
        listOfAvailableTaxi.add(new Taxi(5, new Point(10, 15)));

        Assert.assertEquals(userService.findAppropriateDistanceToTaxi(customer,listOfAvailableTaxi), distance);
    }

    @Test
    public void callTaxiTest() {

        List<Taxi> listOfAvailableTaxi = new ArrayList<>();
        listOfAvailableTaxi.add(new Taxi(1, new Point(2, 4)));
        listOfAvailableTaxi.add(new Taxi(2, new Point(4, 7)));
        listOfAvailableTaxi.add(new Taxi(3, new Point(1, 5)));
        listOfAvailableTaxi.add(new Taxi(4, new Point(4, 3)));
        listOfAvailableTaxi.add(new Taxi(5, new Point(10, 15)));

        List<Taxi> listOfExpectedTaxi = new ArrayList<>();
        listOfExpectedTaxi.add(new Taxi(1, new Point(2, 4)));
        listOfExpectedTaxi.add(new Taxi(2, new Point(4, 7)));
        listOfExpectedTaxi.add(new Taxi(4, new Point(4, 3)));

        Assert.assertEquals(userService.callTaxi(customer,listOfAvailableTaxi), listOfExpectedTaxi);
    }



}
