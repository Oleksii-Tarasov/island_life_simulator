package ua.com.javarush.island_life_simulator.services;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.ItemPosition;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.items.animals.interfaces.Herbivores;
import ua.com.javarush.island_life_simulator.items.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class LifeCycleExecutor {
    private final ItemCreator itemCreator;
    private final ItemMover itemMover;
    private final ItemConditionsChecker itemStatusChecker;

    public LifeCycleExecutor(ItemCreator itemCreator, ItemMover itemMover, ItemConditionsChecker itemStatusChecker) {
        this.itemCreator = itemCreator;
        this.itemMover = itemMover;
        this.itemStatusChecker = itemStatusChecker;
    }

    public void movingAnimals(List<Animal> animalList) {
        itemMover.moveItems(animalList);
    }

    public void reproduction(Cell cell) {
        List<Animal> animalList = cell.getAnimalList();

        Map<Class<? extends Animal>, Long> numberOfAnimalClasses = animalList.stream()
                .collect(groupingBy(Animal::getClass, Collectors.counting()));

        for (Map.Entry<Class<? extends Animal>, Long> pair : numberOfAnimalClasses.entrySet()) {
            Class<? extends Animal> animalClass = pair.getKey();
            long animalQuantity = pair.getValue();

            long animalPairQuantity = animalQuantity;
            for (int i = 0; i < animalPairQuantity / 2; i++) {
                if (itemStatusChecker.canReproduce(animalClass, animalQuantity)) {
                    ItemPosition itemPosition = cell.getCellPosition();
                    itemCreator.createNewbornAnimal(animalClass, itemPosition);
                    animalQuantity++;
                }
                else {
                    break;
                }
            }
        }
    }

    public void eatAnimals(List<Animal> animalList) {
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
                    saturationProcessForCarnivores(attackingAnimal, animalToEat);
                    eatenAnimalList.add(animalToEat);
                }
            }
        }
        animalList.removeAll(eatenAnimalList);
    }

    public void eatPlants(List<Animal> animalList, List<Plant> plantList) {
        if (plantList.isEmpty()) {
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
            plantList.removeAll(eatenPlantList);
        }
    }

    private void saturationProcessForCarnivores(Animal attackingAnimal, Animal animalToEat) {
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
