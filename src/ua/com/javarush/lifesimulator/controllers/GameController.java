package ua.com.javarush.lifesimulator.controllers;

import ua.com.javarush.lifesimulator.items.animals.Animal;
import ua.com.javarush.lifesimulator.items.board.Cell;
import ua.com.javarush.lifesimulator.items.board.GameBoard;
import ua.com.javarush.lifesimulator.services.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ua.com.javarush.lifesimulator.constants.GameConstants.GAME_BOARD_HEIGHT;
import static ua.com.javarush.lifesimulator.constants.GameConstants.GAME_BOARD_WIDTH;

public class GameController {
    private GameBoard gameBoard;
    private final Utility utility = new Utility();
    private final GameEventsController gameEventsController = new GameEventsController();
    private final ConditionsChecker conditionsChecker = new ConditionsChecker(utility);
    private final ItemPlacer itemPlacer = new ItemPlacer();
    private final ItemCreator itemCreator = new ItemCreator(itemPlacer, gameEventsController, conditionsChecker, utility);
    private final WorldUpdater gameUpdater = new WorldUpdater(gameEventsController, itemCreator);
    private final ItemMover itemMover = new ItemMover(itemPlacer, conditionsChecker);
    private final ItemPrinter itemPrinter = new ItemPrinter(gameEventsController, utility);
    LifeHandler lifeProcessHandler = new LifeHandler(itemCreator, conditionsChecker, gameEventsController);

    public void createGameBoard() {
        gameBoard = itemCreator.createBoard();
    }

    public void createAnimals() {
        itemCreator.createAnimals(gameBoard);
    }

    public void createPlants() {
        itemCreator.createPlants(gameBoard);
    }

    public void printGame() {
        itemPrinter.printGameBoard(gameBoard);
        itemPrinter.dailyInformer();
    }

    public void updateGameEvents() {
        gameEventsController.updateDailyEvents();
    }

    public void updateGameWorld() {
        gameUpdater.dailyWorldUpdate(gameBoard);
    }

    public void executeDayPhases() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int y = 0; y < GAME_BOARD_HEIGHT; y++) {
            for (int x = 0; x < GAME_BOARD_WIDTH; x++) {
                Cell cell = gameBoard.getCell(y, x);
                List<Animal> animalList = cell.getAnimalList();

                if (animalList.isEmpty()) {
                    gameEventsController.countingLocationsWithoutAnimals();
                    continue;
                }

                Runnable runnable = () -> {
                    itemMover.moveAnimals(gameBoard, animalList);
                    lifeProcessHandler.eatPlants(animalList, cell.getPlantList());
                    lifeProcessHandler.eatAnimals(animalList);

                    if (!gameEventsController.isCataclysmCome()) {
                        lifeProcessHandler.reproduction(gameBoard, animalList);
                    }
                };
                executorService.execute(runnable);
            }
        }
    }

    public void compileWorldStatistics() {
        gameEventsController.countingDays();
        gameEventsController.setAllAnimalsNumber(utility.getAllAnimalsNumberInTheWorld(gameBoard));
    }

    public boolean isGameOver() {
        return conditionsChecker.isWorldAlive(gameEventsController.getNumberOfLocationsWithoutAnimals());
    }
}
