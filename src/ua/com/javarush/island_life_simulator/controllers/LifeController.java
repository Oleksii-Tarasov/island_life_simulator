package ua.com.javarush.island_life_simulator.controllers;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.services.*;

import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.ISLAND_WIDTH;

public class LifeController {
    private final GameField gameField = new GameField();
    private final ItemConditionsChecker itemConditionsChecker = new ItemConditionsChecker(gameField);
    private final ItemRemover itemRemover = new ItemRemover();
    private final ItemPrinter itemPrinter = new ItemPrinter(gameField);
    private final ItemPlacer itemPlacer = new ItemPlacer(gameField);
    private final ItemCreator itemCreator = new ItemCreator(itemPlacer, itemConditionsChecker);
    private final ItemMover itemMover = new ItemMover(gameField, itemConditionsChecker);
    private final LifeCycleExecutor lifeCycleExecutor = new LifeCycleExecutor(itemCreator, itemMover, itemRemover, itemConditionsChecker);

    public void startDayCycle() {

        /* для тестов симуляции жизненного цикла дня */

        gameField.createIsland();
        itemCreator.createAnimals();
        itemCreator.createPlants();
        itemPrinter.printGameField();

        executeDayPhase(DailyPhase.STARVATION);
        System.out.println(DailyPhase.STARVATION);
        itemPrinter.printGameField();

        executeDayPhase(DailyPhase.MOVE);
        System.out.println(DailyPhase.MOVE);
        itemPrinter.printGameField();

        executeDayPhase(DailyPhase.EAT);
        System.out.println(DailyPhase.EAT);
        itemPrinter.printGameField();

        executeDayPhase(DailyPhase.REPRODUCE);
        System.out.println(DailyPhase.REPRODUCE);
        itemPrinter.printGameField();
    }

    public void executeDayPhase(DailyPhase phase) {
        for (int y = 0; y < ISLAND_HEIGHT; y++) {
            for (int x = 0; x < ISLAND_WIDTH; x++) {
                Cell cell = gameField.getCellFromField(y, x);
                List<Animal> animalList = cell.getAnimalList();
                if (!animalList.isEmpty()) {
                    switch (phase) {
                        case STARVATION -> {
                            lifeCycleExecutor.reduceSaturation(animalList);
                            lifeCycleExecutor.starvingToDeath(animalList);
                        }
                        case MOVE -> {
                            lifeCycleExecutor.movingAnimals(animalList);
                            lifeCycleExecutor.resetWalkStatus(animalList);
                        }
                        case EAT -> {
                            lifeCycleExecutor.eatPlants(animalList, cell.getPlantList());
                            lifeCycleExecutor.eatAnimals(animalList);
                        }
                        case REPRODUCE -> lifeCycleExecutor.reproduction(cell);
                    }
                }
            }
        }
    }
}
