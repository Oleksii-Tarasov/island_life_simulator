package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.board.ItemPosition;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class LifeProcessHandler {
    private final ItemCreator itemCreator;
    private final ConditionsChecker conditionsChecker;
    private final GameEventsController gameEventsController;

    public LifeProcessHandler(ItemCreator itemCreator, ConditionsChecker conditionsChecker, GameEventsController gameEventsController) {
        this.itemCreator = itemCreator;
        this.conditionsChecker = conditionsChecker;
        this.gameEventsController = gameEventsController;
    }

    public void eatAnimals(List<Animal> animalList) {
        List<Animal> eatenAnimalList = new ArrayList<>();

        for (Animal attackingAnimal : animalList) {
            String firstAnimal = attackingAnimal.getClass().getSimpleName();
            for (Animal animalToEat : animalList) {
                String secondAnimal = animalToEat.getClass().getSimpleName();

                if (!(conditionsChecker.isAnimalHungry(attackingAnimal)) ||
                        !(conditionsChecker.isHuntingConditionsGood(attackingAnimal, animalToEat, eatenAnimalList))) {
                    continue;
                }

                String animalsPair = firstAnimal + secondAnimal;
                boolean isAnimalEaten = conditionsChecker.willAnimalBeEaten(animalsPair);

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
                if (!(conditionsChecker.isAnimalHungry(animal)) ||
                        !(conditionsChecker.canEatPlants(animal, plant, eatenPlantList))) {
                    continue;
                }

                saturationProcess(animal, plant);
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
        } else {
            animal.setCurrentSaturation(saturationAfterEating);
        }
    }

    public void reproduction(GameBoard gameBoard, Cell cell) {
        List<Animal> animalList = cell.getAnimalList();

        Map<Class<? extends Animal>, Long> numberOfAnimalClasses = animalList.stream()
                .collect(groupingBy(Animal::getClass, Collectors.counting()));

        for (Map.Entry<Class<? extends Animal>, Long> pair : numberOfAnimalClasses.entrySet()) {
            Class<? extends Animal> animalClass = pair.getKey();
            long animalQuantity = pair.getValue();

            long animalPairQuantity = animalQuantity;
            for (int i = 0; i < animalPairQuantity / 2; i++) {
                if (!conditionsChecker.canReproduce(animalClass, animalQuantity)) {
                    break;
                }
                ItemPosition itemPosition = cell.getCellPosition();
                itemCreator.createNewbornAnimal(animalClass, itemPosition, gameBoard);
                animalQuantity++;
            }
        }
    }
}
