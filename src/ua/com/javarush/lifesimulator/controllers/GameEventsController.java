package ua.com.javarush.lifesimulator.controllers;

import static ua.com.javarush.lifesimulator.constants.GameSettings.CATACLYSM_DAY;

public class GameEventsController {
    private int daysNumber;
    private int newbornAnimalsNumber;
    private int deadAnimalsNumber;
    private int numberOfLocationsWithoutAnimals;
    private int allAnimalsNumber;

    public GameEventsController() {
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

    public void countingLocationsWithoutAnimals() {
        numberOfLocationsWithoutAnimals++;
    }

    public int getNumberOfLocationsWithoutAnimals() {
        return numberOfLocationsWithoutAnimals;
    }

    public boolean isCataclysmCome() {
        return getDaysNumber() >= CATACLYSM_DAY;
    }

    public int getAllAnimalsNumber() {
        return allAnimalsNumber;
    }

    public void setAllAnimalsNumber(int allAnimalsNumber) {
        this.allAnimalsNumber = allAnimalsNumber;
    }

    public void updateDailyEvents() {
        newbornAnimalsNumber = 0;
        deadAnimalsNumber = 0;
        numberOfLocationsWithoutAnimals = 0;
    }
}
