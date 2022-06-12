package ua.com.javarush.island_life_simulator.controllers;

import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.interfaces.Herbivores;
import ua.com.javarush.island_life_simulator.items.plants.Plant;
import ua.com.javarush.island_life_simulator.services.ItemCreator;
import ua.com.javarush.island_life_simulator.services.ItemMover;
import ua.com.javarush.island_life_simulator.services.ItemRemover;
import ua.com.javarush.island_life_simulator.services.ItemStatusChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class LifeCycleExecutor {
    private final ItemCreator itemCreator = new ItemCreator();
    private final ItemMover itemMover = new ItemMover();
    private final ItemRemover itemRemover = new ItemRemover();
    private final ItemStatusChecker itemStatusChecker = new ItemStatusChecker();

    public void reduceSaturation(List<Animal> animalListFromCell) {
        animalListFromCell.forEach(Animal::reduceSaturation);
    }

    public void starvingToDeath(List<Animal> animalListFromCell) {
        animalListFromCell.removeIf(animal -> animal.getCurrentSaturation() <= 0);
    }

    public void movingAnimals(List<Animal> animalListFromCell) {
        itemMover.moveItems(animalListFromCell);
    }

    public void resetWalkStatus(List<Animal> animalListFromCell) {
        animalListFromCell.forEach(animal -> animal.setAlreadyWalked(false));
    }

    public void reproduction(List<Animal> animalList, int y, int x) {
        Map<Class<? extends Animal>, Long> numberOfClassesInList = animalList.stream()
                .collect(groupingBy(Animal::getClass, Collectors.counting()));

        for (Map.Entry<Class<? extends Animal>, Long> animalClassFromMap : numberOfClassesInList.entrySet()) {
            Class<? extends Animal> animalClass = animalClassFromMap.getKey();
            long animalQuantity = animalClassFromMap.getValue();
            long animalPairQuantity = animalQuantity;

            for (int i = 0; i < animalPairQuantity / 2; i++) {
                if (itemStatusChecker.canReproduce(animalClass, animalQuantity)) {
                    itemCreator.createNewbornAnimal(animalClass, y, x);
                    animalQuantity++;
                }
            }
        }
    }

    public void eatOtherAnimals(List<Animal> animalList) {
        List<Animal> eatenAnimalList = new ArrayList<>();

        for (Animal attackingAnimal : animalList) {
            String firstAnimal = attackingAnimal.getClass().getSimpleName();
            for (Animal animalToEat : animalList) {
                String secondAnimal = animalToEat.getClass().getSimpleName();

                if (!itemStatusChecker.isHuntingConditionsGood(attackingAnimal, animalToEat, eatenAnimalList)) {
                    continue;
                }

                String animalsPair = firstAnimal + secondAnimal;
                boolean isAnimalEaten = itemStatusChecker.willAnimalBeEaten(animalsPair);

                if (isAnimalEaten) {
                    saturationProcess(attackingAnimal, animalToEat);
                    eatenAnimalList.add(animalToEat);
                }
            }
        }
        itemRemover.removeAnimals(animalList, eatenAnimalList);
    }

    public void eatPlants(List<Animal> animalList, List<Plant> plantList) {
        if (animalList.isEmpty() || plantList.isEmpty()) {
            return;
        }

        List<Plant> eatenPlantList = new ArrayList<>();

        for (Animal animal : animalList) {
            for (Plant plant : plantList) {
                if (animal instanceof Herbivores && itemStatusChecker.isAnimalHungry(animal)) {
                    double animalCurrentSaturation = animal.getCurrentSaturation();
                    animal.setCurrentSaturation(animalCurrentSaturation + plant.getWeight());
                    eatenPlantList.add(plant);
                }
            }
            itemRemover.removePlants(plantList, eatenPlantList);
        }
    }

    private void saturationProcess(Animal attackingAnimal, Animal animalToEat) {
        double animalWinnerFullSaturation = attackingAnimal.getFullSaturation();
        double animalWinnerCurrentSaturation = attackingAnimal.getCurrentSaturation();
        double weightOfTheEatenAnimal = animalToEat.getWeight();
        double saturationAfterEating = animalWinnerCurrentSaturation + weightOfTheEatenAnimal;

        if (saturationAfterEating > animalWinnerFullSaturation) {
            attackingAnimal.setCurrentSaturation(animalWinnerFullSaturation);
        }
        else {
            attackingAnimal.setCurrentSaturation(saturationAfterEating);
        }
    }
}
