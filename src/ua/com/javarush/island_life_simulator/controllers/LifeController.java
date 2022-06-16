package ua.com.javarush.island_life_simulator.controllers;

import ua.com.javarush.island_life_simulator.field.Cell;
import ua.com.javarush.island_life_simulator.field.GameField;
import ua.com.javarush.island_life_simulator.items.animals.Animal;
import ua.com.javarush.island_life_simulator.services.*;

import java.util.List;

import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_HEIGHT;
import static ua.com.javarush.island_life_simulator.constants.GameSettings.GAME_FIELD_WIDTH;

public class LifeController {
    private final GameField gameField = new GameField();
    private final ItemConditionsChecker itemConditionsChecker = new ItemConditionsChecker(gameField);
    private final ItemPrinter itemPrinter = new ItemPrinter(gameField);
    private final ItemPlacer itemPlacer = new ItemPlacer(gameField);
    private final ItemCreator itemCreator = new ItemCreator(itemPlacer, itemConditionsChecker);
    private final ItemMover itemMover = new ItemMover(gameField, itemConditionsChecker);
    private final ItemUpdater itemUpdater = new ItemUpdater(gameField, itemCreator);
    private final LifeCycleExecutor lifeCycleExecutor = new LifeCycleExecutor(itemCreator, itemMover, itemConditionsChecker);

    public void startZeroDay() {
        gameField.createIsland();
        itemCreator.createAnimals();
        itemCreator.createPlants();
        itemPrinter.printGameField();
    }

    public void startDailyCycle() {
        itemUpdater.dailyWorldUpdate();
        executeDailyPhase();
    }

    private void executeDailyPhase() {
        for (int y = 0; y < GAME_FIELD_HEIGHT; y++) {
            for (int x = 0; x < GAME_FIELD_WIDTH; x++) {
                Cell cell = gameField.getCellFromField(y, x);
                List<Animal> animalList = cell.getAnimalList();

                if (animalList.isEmpty()) {
                    continue;
                }

                lifeCycleExecutor.movingAnimals(animalList);
                lifeCycleExecutor.eatPlants(animalList, cell.getPlantList());
                lifeCycleExecutor.eatAnimals(animalList);
                lifeCycleExecutor.reproduction(cell);
            }
        }
        itemPrinter.printGameField();
    }
}
