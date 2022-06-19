package ua.com.javarush.lifesimulator.controllers;

import ua.com.javarush.lifesimulator.configuration.AnimalConfiguration;
import ua.com.javarush.lifesimulator.field.Cell;
import ua.com.javarush.lifesimulator.field.GameField;
import ua.com.javarush.lifesimulator.items.Animal;
import ua.com.javarush.lifesimulator.services.*;

import java.util.List;

import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_FIELD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameSettings.GAME_FIELD_WIDTH;

public class LifeController {
    private final AnimalConfiguration animalConfiguration = new AnimalConfiguration();
    private final GameField gameField = new GameField();
    private final GameEventsController gameEventsController = new GameEventsController();
    private final ItemConditionsChecker itemConditionsChecker = new ItemConditionsChecker(gameField, animalConfiguration);
    private final ItemPrinter itemPrinter = new ItemPrinter(gameField, gameEventsController);
    private final ItemPlacer itemPlacer = new ItemPlacer(gameField);
    private final ItemCreator itemCreator = new ItemCreator(animalConfiguration, itemPlacer, itemConditionsChecker, gameEventsController);
    private final ItemMover itemMover = new ItemMover(gameField, itemConditionsChecker);
    private final ItemUpdater itemUpdater = new ItemUpdater(gameField, itemCreator, gameEventsController);
    private final LifeCycleExecutor lifeCycleExecutor = new LifeCycleExecutor(itemCreator, itemMover, itemConditionsChecker, gameEventsController);


    public void startZeroDay() {
        gameField.createIsland();
        itemCreator.createAnimals();
        itemCreator.createPlants();

        itemPrinter.zeroDayInformer();
        itemPrinter.printGameField();
    }

    public void startDailyCycle() {
        gameEventsController.resetDailyEvents();
        gameEventsController.countDaysNumber();
        itemUpdater.dailyWorldUpdate();
        executeDailyPhase();

        itemPrinter.printGameField();
        itemPrinter.dailyInformer();
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
    }
}
