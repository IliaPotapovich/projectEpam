package com.potapovich.project.logic.specification;

import com.potapovich.project.entity.Taxi;


public class FindApropriateTaxi implements Specification {

    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;

    public FindApropriateTaxi(int xStart, int xEnd, int yStart, int yEnd) {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
    }

    @Override
    public boolean specified(Taxi taxi) {

        System.out.println("AYYYYYYYYY   " +  taxi.isFree() +" " + taxi.getTaxiLocation().getX() + xStart + taxi.getTaxiLocation().getX() + xEnd +
                taxi.getTaxiLocation().getY() + yStart + taxi.getTaxiLocation().getY() + yEnd);

        return taxi.isFree() && taxi.getTaxiLocation().getX() >= xStart && taxi.getTaxiLocation().getX() <= xEnd &&
                taxi.getTaxiLocation().getY() >= yStart && taxi.getTaxiLocation().getY() <= yEnd;
    }
}
