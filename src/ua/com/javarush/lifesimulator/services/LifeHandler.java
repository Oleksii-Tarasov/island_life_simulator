package ua.com.javarush.lifesimulator.services;

import ua.com.javarush.lifesimulator.controllers.GameEventsController;
import ua.com.javarush.lifesimulator.items.BasicItem;
import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.items.plants.Plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LifeHandler {
    private final ItemCreator itemCreator;
    private final ConditionsChecker conditionsChecker;
    private final GameEventsController gameEventsController;

    public LifeHandler(ItemCreator itemCreator, ConditionsChecker conditionsChecker, GameEventsController gameEventsController) {
        this.itemCreator = itemCreator;
        this.conditionsChecker = conditionsChecker;
        this.gameEventsController = gameEventsController;
    }

    public void eatAnimals(List<Animal> animalList) {
        List<Animal> eatenAnimalList = Collections.synchronizedList(new ArrayList<>());

        for (Animal attackingAnimal : animalList) {
            String firstAnimal = attackingAnimal.getAnimalType();
            for (Animal animalToEat : animalList) {
                String secondAnimal = animalToEat.getAnimalType();

                if (!(conditionsChecker.isAnimalHungry(attackingAnimal)) ||
                        !(conditionsChecker.isHuntingConditionsGood(attackingAnimal, animalToEat, eatenAnimalList))) {
                    continue;
                }

                String animalsPair = firstAnimal + secondAnimal;
                boolean isAnimalEaten = conditionsChecker.willAnimalBeEaten(animalsPair);

                if (isAnimalEaten) {
                    saturationProcess(attackingAnimal, animalToEat);
                    eatenAnimalList.add(animalToEat);
                }
            }
        }
        eatenAnimalList.forEach(animal -> gameEventsController.countingDeadAnimals());
        animalList.removeAll(eatenAnimalList);
    }

    public void eatPlants(List<Animal> animalList, List<Plant> plantList) {
        if (plantList.isEmpty()) {
            return;
        }

        List<Plant> eatenPlantList = Collections.synchronizedList(new ArrayList<>());

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

    public void reproduction(GameBoard gameBoard, List<Animal> animalList) {
        Map<Animal, Long> numberOfEachAnimals = animalList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<Animal, Long> animals : numberOfEachAnimals.entrySet()) {
            long animalsNumber = animals.getValue();

            if (animalsNumber < 2) {
                continue;
            }

            Animal animal = animals.getKey();

            for (int i = 0; i < animalsNumber / 2; i++) {
                if (!conditionsChecker.canAddItemToCell(gameBoard, animal)) {
                    break;
                }

                itemCreator.createNewbornAnimal(gameBoard, animal);
                gameEventsController.countingNewbornAnimals();
            }
        }
    }
}
