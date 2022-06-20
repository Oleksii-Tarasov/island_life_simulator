package ua.com.javarush.lifesimulator.controllers;

public class GameEventsController {
    private int daysNumber;
    private int animalsNumber;
    private int plantsNumber;
    private int newbornAnimalsNumber;
    private int deadAnimalsNumber;
    private int deadPlantsNumber;
    private int numberOfLocationsWithoutAnimals;

    public GameEventsController() {
        this.daysNumber = 1;
    }

    public void countingAnimals() {
        animalsNumber++;
    }

    public void countingPlants() {
        plantsNumber++;
    }

    public void countingNewbornAnimals() {
        newbornAnimalsNumber++;
    }

    public void countingDeadAnimals() {
        deadAnimalsNumber++;
    }

    public void countingDeadPlants() {
        deadPlantsNumber++;
    }

    public void countingDays() {
        daysNumber++;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    public int getAnimalsNumber() {
        return animalsNumber;
    }

    public int getPlantsNumber() {
        return plantsNumber;
    }

    public int getNewbornAnimalsNumber() {
        return newbornAnimalsNumber;
    }

    public int getDeadAnimalsNumber() {
        return deadAnimalsNumber;
    }

    public int getDeadPlantsNumber() {
        return deadPlantsNumber;
    }

    public void countingLocationsWithoutAnimals() {
        numberOfLocationsWithoutAnimals++;
    }

    public int getNumberOfLocationsWithoutAnimals() {
        return numberOfLocationsWithoutAnimals;
    }

    public void resetDailyEvents() {
        plantsNumber = 0;
        newbornAnimalsNumber = 0;
        deadAnimalsNumber = 0;
        deadPlantsNumber = 0;
        numberOfLocationsWithoutAnimals = 0;
    }
}
