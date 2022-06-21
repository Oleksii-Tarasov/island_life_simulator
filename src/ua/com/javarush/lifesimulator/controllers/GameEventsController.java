package ua.com.javarush.lifesimulator.controllers;

import static ua.com.javarush.lifesimulator.constants.GameConstants.CATACLYSM_DAY;
import static ua.com.javarush.lifesimulator.constants.GameConstants.FIRST_DAY_IN_THE_WORLD;

public class GameEventsController {
    private int daysNumber;
    private int allAnimalsNumber;
    private int newbornAnimalsNumber;
    private int deadAnimalsNumber;
    private int numberOfLocationsWithoutAnimals;

    public GameEventsController() {
        this.daysNumber = FIRST_DAY_IN_THE_WORLD;
    }

    public void countingNewbornAnimals() {
        newbornAnimalsNumber++;
    }

    public void countingDeadAnimals() {
        deadAnimalsNumber++;
    }

    public void countingDays() {
        daysNumber++;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    public int getNewbornAnimalsNumber() {
        return newbornAnimalsNumber;
    }

    public int getDeadAnimalsNumber() {
        return deadAnimalsNumber;
    }

    public int getAllAnimalsNumber() {
        return allAnimalsNumber = allAnimalsNumber + newbornAnimalsNumber - deadAnimalsNumber;
    }

    public void countingLocationsWithoutAnimals() {
        numberOfLocationsWithoutAnimals++;
    }

    public int getNumberOfLocationsWithoutAnimals() {
        return numberOfLocationsWithoutAnimals;
    }

    public boolean isCataclysmCome() {
        return getDaysNumber() >= CATACLYSM_DAY;
    }

    public void updateDailyEvents() {
        newbornAnimalsNumber = 0;
        deadAnimalsNumber = 0;
        numberOfLocationsWithoutAnimals = 0;
    }
}
