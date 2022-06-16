package ua.com.javarush.island_life_simulator;

import ua.com.javarush.island_life_simulator.controllers.LifeController;

public class Main {
    public static void main(String[] args) {
        System.out.println("*** Island Life Simulator ***");
        LifeController lifeController = new LifeController();
        lifeController.startZeroDay();
        lifeController.startDailyCycle();
    }
}
