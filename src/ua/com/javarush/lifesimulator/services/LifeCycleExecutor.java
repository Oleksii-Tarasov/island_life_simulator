package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.ItemPosition;
import ua.com.javarush.lifesimulator.items.Animal;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class LifeCycleExecutor {
    private final ItemCreator itemCreator;
    private final ItemMover itemMover;
    private final ItemConditionsChecker itemStatusChecker;
    private final GameEventsController gameEventsController;

    public LifeCycleExecutor(ItemCreator itemCreator, ItemMover itemMover, ItemConditionsChecker itemStatusChecker, GameEventsController gameEventsController) {
        this.itemCreator = itemCreator;
        this.itemMover = itemMover;
        this.itemStatusChecker = itemStatusChecker;
        this.gameEventsController = gameEventsController;
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
                if (!itemStatusChecker.canReproduce(animalClass, animalQuantity)) {
                    break;
                }
                ItemPosition itemPosition = cell.getCellPosition();
                itemCreator.createNewbornAnimal(animalClass, itemPosition);
                gameEventsController.countingNewbornAnimals();
                animalQuantity++;
            }
        }
    }

    public void eatAnimals(List<Animal> animalList) {
        List<Animal> eatenAnimalList = new ArrayList<>();

        for (Animal attackingAnimal : animalList) {
            String firstAnimal = attackingAnimal.getClass().getSimpleName();
            for (Animal animalToEat : animalList) {
                String secondAnimal = animalToEat.getClass().getSimpleName();

                if (!(itemStatusChecker.isHuntingConditionsGood(attackingAnimal, animalToEat, eatenAnimalList))
                        || !(itemStatusChecker.isAnimalHungry(attackingAnimal))) {
                    continue;
                }

                String animalsPair = firstAnimal + secondAnimal;
                boolean isAnimalEaten = itemStatusChecker.willAnimalBeEaten(animalsPair);

                if (isAnimalEaten) {
                    saturationProcess(attackingAnimal, animalToEat);
                    gameEventsController.countingDeadAnimals();
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
                if (!(itemStatusChecker.canEatPlants(animal, plant, eatenPlantList)) || !(itemStatusChecker.isAnimalHungry(animal))) {
                    continue;
                }

                saturationProcess(animal, plant);
                gameEventsController.countingDeadPlants();
                eatenPlantList.add(plant);
            }
            plantList.removeAll(eatenPlantList);
        }
    }

    private void saturationProcess(Animal animal, BasicItem item) {
        double animalFullSaturation = animal.getFullSaturation();
        double animalCurrentSaturation = animal.getCurrentSaturation();
        double saturationAfterEating = animalCurrentSaturation + item.getWeight();

        if (saturationAfterEating > animalFullSaturation) {
            animal.setCurrentSaturation(animalFullSaturation);
        }
        else {
            animal.setCurrentSaturation(saturationAfterEating);
        }
    }
}
