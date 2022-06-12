package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.interfaces.Carnivores;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ONE_HUNDRED_PERCENT_CHANCE;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.CHANCE_TO_EAT_SOMEONE;

public class ItemStatusChecker {
    public boolean isAnimalHungry(Animal animal) {
        return animal.getCurrentSaturation() < animal.getFullSaturation();
    }

    public boolean willAnimalBeEaten(String animalsPair) {
        boolean haveWinner = false;
        for (Map.Entry<String, Integer> fightingAnimals : CHANCE_TO_EAT_SOMEONE.entrySet()) {
            if (fightingAnimals.getKey().equals(animalsPair)) {
                int chanceToEat = fightingAnimals.getValue();
                int realResult = new Random().nextInt(ONE_HUNDRED_PERCENT_CHANCE);
                haveWinner = realResult > (ONE_HUNDRED_PERCENT_CHANCE - chanceToEat);
                break;
            }
        }
        return haveWinner;
    }

    public boolean canReproduce(Class<? extends Animal> animalClass, long quantity) {
        int maxAmountOnCell = 0;
        try {
            Field fieldMaxAmountOnCell = animalClass.getDeclaredField("MAX_AMOUNT_ON_CELL");
            fieldMaxAmountOnCell.setAccessible(true);
            maxAmountOnCell = (int) fieldMaxAmountOnCell.get(animalClass);
            System.out.println(animalClass.getSimpleName() + " " + maxAmountOnCell + " " + quantity);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e);
        }

        return quantity < maxAmountOnCell;
    }

    public boolean isHuntingConditionsGood(Animal attackingAnimal, Animal animalToEat, List<Animal> eatenAnimalList) {
        return ((attackingAnimal instanceof Carnivores)
                || !(eatenAnimalList.contains(animalToEat))
                || !(attackingAnimal.getClass().getSimpleName()).equals(animalToEat.getClass().getSimpleName()));
    }

    public boolean canAddAnimal(Animal animal) {
        int x = animal.getAnimalPosition().getX();
        int y = animal.getAnimalPosition().getY();
        int maxAmountOnCell = animal.getMaxAmountOnCell();

        Cell cell = GameField.islandField[y][x];
        List<Animal> animalList = cell.getAnimalList();

        Map<String, Long> animalMap = animalList.stream()
                .collect(groupingBy(Animal::toString, Collectors.counting()));

        Long currentAmount = animalMap.get(animal.toString());

        return currentAmount < maxAmountOnCell;
    }
}
