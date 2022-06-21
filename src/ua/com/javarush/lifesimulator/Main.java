package ua.com.javarush.lifesimulator;

import ua.com.javarush.lifesimulator.controllers.LifeController;

public class Main {
    public static void main(String[] args) {
        System.out.println("*** Island Life Simulator ***");
        LifeController lifeController = new LifeController();
        lifeController.startZeroDay();
    }
}
