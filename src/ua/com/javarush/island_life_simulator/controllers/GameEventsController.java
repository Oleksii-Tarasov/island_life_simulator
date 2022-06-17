package ua.com.javarush.island_life_simulator.controllers;

public class GameEventsController {
    private int daysNumber;
    private int animalsNumber;
    private int plantsNumber;
    private int newbornAnimalsNumber;
    private int deadAnimalsNumber;
    private int deadPlantsNumber;

    public void countAnimals() {
        animalsNumber++;
    }

    public void countPlants() {
        plantsNumber++;
    }

    public void countNewbornAnimals() {
        newbornAnimalsNumber++;
    }

    public void countDeadAnimals() {
        deadAnimalsNumber++;
    }

    public void countDeadPlants() {
        deadPlantsNumber++;
    }

    public void setDaysNumber(int daysNumber) {
        this.daysNumber = daysNumber;
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

    public void resetDailyEvents() {
        plantsNumber = 0;
        newbornAnimalsNumber = 0;
        deadAnimalsNumber = 0;
        deadPlantsNumber = 0;
    }
}
