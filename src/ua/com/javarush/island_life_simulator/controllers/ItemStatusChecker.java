package ua.com.javarush.island_life_simulator.controllers;

import ua.com.javarush.island_life_simulator.items.animals.Animal;

import java.util.Map;
import java.util.Random;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ONE_HUNDRED_PERCENT_CHANCE;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.chanceToEatSomeone;

public class ItemStatusChecker {
    public boolean isAnimalHungry(Animal animal) {
        return animal.getCurrentSaturation() < animal.getFullSaturation();
    }

    public boolean isEaten(String animalsPair) {
        boolean haveWinner = false;
        for (Map.Entry<String, Integer> fightingAnimals : chanceToEatSomeone.entrySet()) {
            if (fightingAnimals.getKey().equals(animalsPair)) {
                int chanceToEat = fightingAnimals.getValue();
                int realResult = new Random().nextInt(ONE_HUNDRED_PERCENT_CHANCE);
                System.out.println(animalsPair + " " + realResult);
                haveWinner = realResult > (ONE_HUNDRED_PERCENT_CHANCE - chanceToEat);
                break;
            }
        }
        return haveWinner;
    }
}
