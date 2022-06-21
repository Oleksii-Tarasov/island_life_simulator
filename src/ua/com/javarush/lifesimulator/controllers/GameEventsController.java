package ua.com.javarush.lifesimulator.controllers;

import static ua.com.javarush.lifesimulator.constants.GameSettings.CATACLYSM_DAY;

public class GameEventsController {
    private int daysNumber;
    private int animalsNumber;
    private int allAnimalsNumber;
    private int plantsNumber;
    private int allPlantsNumber;
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

    public int getAllAnimalsNumber() {
        return animalsNumber;
    }

    public int getAllPlantsNumber() {
        return plantsNumber;
    }

    public void countingLocationsWithoutAnimals() {
        numberOfLocationsWithoutAnimals++;
    }

    public int getNumberOfLocationsWithoutAnimals() {
        return numberOfLocationsWithoutAnimals;
    }

    public void setAllAnimalsNumber() {
        this.allAnimalsNumber = allAnimalsNumber + animalsNumber - deadAnimalsNumber;
    }

    public void setAllPlantsNumber() {
        this.allPlantsNumber = allPlantsNumber + plantsNumber - deadPlantsNumber;
    }

    public boolean isCataclysmCome() {
        return getDaysNumber() >= CATACLYSM_DAY;
    }

    public void updateDailyEvents() {
        setAllAnimalsNumber();
        setAllPlantsNumber();

        animalsNumber = 0;
        plantsNumber = 0;
        deadAnimalsNumber = 0;
        deadPlantsNumber = 0;
        numberOfLocationsWithoutAnimals = 0;
    }
}
